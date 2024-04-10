package pe.gob.mtc.licencias.userservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.UserEntity;

@Repository
public interface IUserRepository extends CrudRepository<UserEntity, String> {
}
