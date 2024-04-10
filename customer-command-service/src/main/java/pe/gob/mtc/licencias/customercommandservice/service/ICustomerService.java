package pe.gob.mtc.licencias.customercommandservice.service;

import pe.gob.mtc.licencias.customercommandservice.model.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {

    CustomerDto save(CustomerDto userDto);

    List<CustomerDto> findAll ();
    List<CustomerDto> findByDocumentNumber (String documentNumber);

}
