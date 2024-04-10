package pe.gob.mtc.licencias.customerqueryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.Address;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.CustomerEntity;
import pe.gob.mtc.licencias.customerqueryservice.model.dto.CustomerDto;
import pe.gob.mtc.licencias.customerqueryservice.repository.ICustomerRepository;
import pe.gob.mtc.licencias.customerqueryservice.service.ICustomerService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository repository;

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
