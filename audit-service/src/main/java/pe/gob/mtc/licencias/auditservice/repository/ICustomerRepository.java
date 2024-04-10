package pe.gob.mtc.licencias.auditservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.CustomerEntity;
@Repository
public interface ICustomerRepository extends CrudRepository<CustomerEntity, String>{

}
