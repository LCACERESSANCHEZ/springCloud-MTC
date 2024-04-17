package pe.gob.mtc.licencias.licensesqueryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.LicensesEntity;
import pe.gob.mtc.licencias.licensesqueryservice.model.response.ValidateLicensesResponse;
import pe.gob.mtc.licencias.licensesqueryservice.repository.ILicensesRepository;
import pe.gob.mtc.licencias.licensesqueryservice.service.ILicensesService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LicensesServiceImpl implements ILicensesService {

    private final ILicensesRepository repository;

    @Override
    public List<ValidateLicensesResponse> validateLicense(String documentNumber) {
        Optional<List<LicensesEntity>> optListLicensesEntity = repository.findByPersonDocumentNumber(documentNumber);
        return optListLicensesEntity.map(licensesEntity -> licensesEntity.stream()
                .map(entity -> {
                    return ValidateLicensesResponse.builder()
                            .licenseNumber(entity.getLicenseNumber())
                            .licenseStatus(entity.getLicenseStatus())
                            .category(entity.getCategory())
                            .dateOfIssue(entity.getDateOfIssue())
                            .expirationDate(entity.getExpirationDate())
                            .build();
                })
                .toList()).orElse(Collections.emptyList());
    }

    @Override
    public List<ValidateLicensesResponse> listLicensesByLicenseStatus(String licenseStatus) {
        Optional<List<LicensesEntity>> optListLicensesEntity = repository.findByLicenseStatus(licenseStatus);
        return optListLicensesEntity.map(licensesEntity -> licensesEntity.stream()
                .map(entity -> {
                    return ValidateLicensesResponse.builder()
                            .licenseNumber(entity.getLicenseNumber())
                            .licenseStatus(entity.getLicenseStatus())
                            .category(entity.getCategory())
                            .dateOfIssue(entity.getDateOfIssue())
                            .expirationDate(entity.getExpirationDate())
                            .build();
                })
                .toList()).orElse(Collections.emptyList());
    }

    @Override
    public List<ValidateLicensesResponse> listLicenseByCategory(String category) {
        Optional<List<LicensesEntity>> optListLicensesEntity = repository.findByCategory(category);
        return optListLicensesEntity.map(licensesEntity -> licensesEntity.stream()
                .map(entity -> {
                    return ValidateLicensesResponse.builder()
                            .licenseNumber(entity.getLicenseNumber())
                            .licenseStatus(entity.getLicenseStatus())
                            .category(entity.getCategory())
                            .dateOfIssue(entity.getDateOfIssue())
                            .expirationDate(entity.getExpirationDate())
                            .build();
                })
                .toList()).orElse(Collections.emptyList());
    }

    @Override
    public ValidateLicensesResponse listLicenseByLicenseNumber(String licenseNumber) {
        Optional<LicensesEntity> optLicensesEntity = repository.findByLicenseNumber(licenseNumber);

        if (optLicensesEntity.isPresent()) {
            LicensesEntity licenseEntity = optLicensesEntity.get();
            return ValidateLicensesResponse.builder()
                    .licenseNumber(licenseEntity.getLicenseNumber())
                    .licenseStatus(licenseEntity.getLicenseStatus())
                    .category(licenseEntity.getCategory())
                    .dateOfIssue(licenseEntity.getDateOfIssue())
                    .expirationDate(licenseEntity.getExpirationDate())
                    .build();
        } else {
            return null;
        }
    }


}
