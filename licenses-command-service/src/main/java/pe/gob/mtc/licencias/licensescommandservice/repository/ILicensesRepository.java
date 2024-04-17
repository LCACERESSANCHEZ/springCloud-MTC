package pe.gob.mtc.licencias.licensescommandservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.LicensesEntity;

import java.util.Optional;

@Repository
public interface ILicensesRepository extends CrudRepository<LicensesEntity, String> {
    Optional<LicensesEntity> findByLicenseNumber(String licenseNumber);

}