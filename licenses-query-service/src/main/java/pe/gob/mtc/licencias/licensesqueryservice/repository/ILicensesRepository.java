package pe.gob.mtc.licencias.licensesqueryservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.LicensesEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILicensesRepository extends CrudRepository<LicensesEntity, String> {
    Optional<LicensesEntity> findByLicenseNumber(String licenseNumber);
    Optional<List<LicensesEntity>> findByPersonDocumentNumber(String documentNumber);
    Optional<List<LicensesEntity>> findByLicenseStatus(String licenseStatus);
    Optional<List<LicensesEntity>> findByCategory(String category);
}

