package pe.gob.mtc.licencias.customercommandservice.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.Address;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.CustomerEntity;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.UserEntity;
import pe.gob.mtc.licencias.customercommandservice.model.dto.CustomerDto;
import pe.gob.mtc.licencias.customercommandservice.repository.ICustomerRepository;
import pe.gob.mtc.licencias.customercommandservice.service.ICustomerService;
import pe.gob.mtc.licencias.customercommandservice.util.KafkaUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository repository;
    private final KafkaUtil kafkaUtil;

    @Override
    public CustomerDto save(CustomerDto userDto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateOfBirthUser = sdf.parse(userDto.getDateOfBirth());//"1995-01-01"
            CustomerEntity customerEntity = CustomerEntity.builder()
                    .firstName(userDto.getFirstName())
                    .lastname(userDto.getLastname())
                    .documentNumber(userDto.getDocumentNumber())
                    .dateOfBirth(dateOfBirthUser)
                    .address(Address.builder()
                            .street(userDto.getAddress().getStreet())
                            .district(userDto.getAddress().getDistrict())
                            .department(userDto.getAddress().getDepartment())
                            .build())
                    .build();
            repository.save(customerEntity);
            kafkaUtil.sendMessage(customerEntity);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return userDto;
    }

    @Override
    public List<CustomerDto> findAll() {
        Iterable<CustomerEntity> iUserEntity = repository.findAll();
        return StreamSupport.stream(iUserEntity.spliterator(), false)
                .map(user -> {
                    return CustomerDto.builder()
                            .firstName(user.getFirstName())
                            .lastname(user.getLastname())
                            .documentNumber(user.getDocumentNumber())
                            .dateOfBirth(user.getDateOfBirth().toString())
                            .address(Address.builder()
                                    .street(user.getAddress().getStreet())
                                    .district(user.getAddress().getDistrict())
                                    .department(user.getAddress().getDepartment())
                                    .build())
                            .build();
                })
                .toList();
    }

    @Override
    public List<CustomerDto> findByDocumentNumber(String documentNumber) {
        Optional<List<CustomerEntity>> optLstCustomer = repository.findByDocumentNumber(documentNumber);
        return optLstCustomer.map(customerEntities -> customerEntities.stream()
                .map(customerEntity -> {
                    return CustomerDto.builder()
                            .firstName(customerEntity.getFirstName())
                            .lastname(customerEntity.getLastname())
                            .documentNumber(customerEntity.getDocumentNumber())
                            .dateOfBirth(customerEntity.getDateOfBirth().toString())
                            .address(Address.builder()
                                    .street(customerEntity.getAddress().getStreet())
                                    .district(customerEntity.getAddress().getDistrict())
                                    .department(customerEntity.getAddress().getDepartment())
                                    .build())
                            .build();
                })
                .toList()).orElse(Collections.emptyList());
    }
}
