package pe.gob.mtc.licencias.customercommandservice.util;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.GenericEntity;

@Component
@RequiredArgsConstructor
public class KafkaUtil {

    private final KafkaTemplate<String, GenericEntity> kafkaTemplate;

    @Value("${kafka.mtc.topic:topic-mtc-consumer}")
    private String topicName;

    public void sendMessage(GenericEntity obj) {
        kafkaTemplate.send(topicName, obj);
    }

}
