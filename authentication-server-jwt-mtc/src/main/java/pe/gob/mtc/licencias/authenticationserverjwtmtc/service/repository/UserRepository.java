package pe.gob.mtc.licencias.authenticationserverjwtmtc.service.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.gob.mtc.licencias.authenticationserverjwtmtc.model.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

    Optional<UserEntity> findByUsername(String username);

}
