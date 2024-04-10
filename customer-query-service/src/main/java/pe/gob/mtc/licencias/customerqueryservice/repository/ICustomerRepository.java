package pe.gob.mtc.licencias.customerqueryservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepository extends CrudRepository<CustomerEntity, String>{

    Optional<List<CustomerEntity>> findByDocumentNumber(String documentNumber);

}
