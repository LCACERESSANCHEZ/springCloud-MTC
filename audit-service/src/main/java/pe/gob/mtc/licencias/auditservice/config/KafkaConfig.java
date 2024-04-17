package pe.gob.mtc.licencias.auditservice.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import pe.gob.mtc.licencias.auditservice.repository.ICustomerRepository;
import pe.gob.mtc.licencias.auditservice.repository.ILicensesRepository;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.CustomerEntity;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.GenericEntity;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.LicensesEntity;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private final ICustomerRepository iCustomerRepository;
    private final ILicensesRepository iLicensesRepository;

    @Value("${kafka.mtc.server:127.0.0.1}")
    private String kafkaServer;
    @Value("${kafka.mtc.port:9092}")
    private String kafkaPort;
    @Value("${kafka.mtc.topic:topic-mtc-consumer}")
    private String topicName;

    @Bean
    public ConsumerFactory<String, GenericEntity<? extends GenericEntity>> consumerFactory() {
        Map<String, Object> kafkaProperties = new HashMap<>();
        kafkaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer + ":" + kafkaPort);
        kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, topicName);

        kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        kafkaProperties.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
        kafkaProperties.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());

        kafkaProperties.put(JsonDeserializer.KEY_DEFAULT_TYPE, "pe.gob.mtc.licencias.auditservice.config.KafkaConfig");
        kafkaProperties.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "pe.gob.mtc.licencias.auditservice.config.KafkaConfig");
        kafkaProperties.put(JsonDeserializer.TRUSTED_PACKAGES, "pe.gob.mtc.licencias.*");

        return new DefaultKafkaConsumerFactory<>(kafkaProperties);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, GenericEntity<? extends GenericEntity>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, GenericEntity<? extends GenericEntity>> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @KafkaListener(topics = "topic-mtc-consumer")
    public void listenTopic(GenericEntity<? extends GenericEntity> obj) {

        if (obj instanceof CustomerEntity customerEntity) {
            log.info("Almacenando customer Entity");
            iCustomerRepository.save(customerEntity);
        } else if (obj instanceof LicensesEntity licensesEntity) {
            log.info("Almacenando Licenses Entity");
            iLicensesRepository.save(licensesEntity);
        }/* else if (obj instanceof AuditInfo auditInfo) {
            // Validación o sanitización de datos
            log.info("Almacenando Audit Info");
            auditInfoRepository.save(auditInfo);
        }*/

    }

}
