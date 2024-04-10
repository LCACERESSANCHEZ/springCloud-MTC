package pe.gob.mtc.licencias.customerqueryservice.service;


import pe.gob.mtc.licencias.customerqueryservice.model.dto.CustomerDto;

import java.util.List;

public interface ICustomerService {
    List<CustomerDto> findAll ();
    List<CustomerDto> findByDocumentNumber (String documentNumber);
}
