package pe.gob.mtc.licencias.licensescommandservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.LicenseStatus;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.LicensesEntity;
import pe.gob.mtc.licencias.licensescommandservice.model.dto.LicensesDto;
import pe.gob.mtc.licencias.licensescommandservice.model.request.LicensesRequest;
import pe.gob.mtc.licencias.licensescommandservice.model.response.ValidateLicensesResponse;
import pe.gob.mtc.licencias.licensescommandservice.repository.ILicensesRepository;
import pe.gob.mtc.licencias.licensescommandservice.service.ILicensesService;
import pe.gob.mtc.licencias.licensescommandservice.util.KafkaUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LicensesServiceImpl implements ILicensesService {

    private final ILicensesRepository repository;
    private final KafkaUtil kafkaUtil;

    @Override
    public LicensesDto issueLicense(LicensesDto licensesDto) {
        // En el controlador o servicio
        Optional<LicensesEntity> optLicensesEntity = repository.findByLicenseNumber(licensesDto.getLicenseNumber());
        if (optLicensesEntity.isPresent()) {
            throw new RuntimeException("Ya existe licencias con el número de licencia proporcionado: " + licensesDto.getLicenseNumber());
        }
        String licenseStatus = licensesDto.getLicenseStatus().getStatus().toUpperCase();
        if (isValidLicenseStatus(licenseStatus)) {
            LicensesEntity licensesEntity = LicensesEntity.builder()
                    .personDocumentNumber(licensesDto.getPersonDocumentNumber())
                    .licenseNumber(licensesDto.getLicenseNumber())
                    .licenseOfType(licensesDto.getLicenseOfType())
                    .restrictions(licensesDto.getRestrictions())
                    .licenseStatus(LicenseStatus.valueOf(licenseStatus))
                    .issuedBy(licensesDto.getIssuedBy())
                    .category(licensesDto.getCategory())
                    .dateOfIssue(licensesDto.getDateOfIssue())
                    .expirationDate(licensesDto.getExpirationDate())
                    .build();
            repository.save(licensesEntity);
            kafkaUtil.sendMessage(licensesEntity);
        } else {
            throw new IllegalArgumentException("Estado de licencia no válido: " + licenseStatus);
        }
        return licensesDto;
    }

    @Override
    public void updateLicense(String licenseNumber, LicensesRequest licensesRequest) {
        Optional<LicensesEntity> optLicensesEntity = repository.findByLicenseNumber(licenseNumber);

        if (!optLicensesEntity.isPresent()) {
            throw new RuntimeException("No se encontraron licencias con el número de licencia proporcionado: " + licenseNumber);
        }
        LicensesEntity licensesEntity =  optLicensesEntity.get();
        licensesEntity.setLicenseOfType(licensesRequest.getLicenseOfType());
        licensesEntity.setRestrictions(licensesRequest.getRestrictions());
        licensesEntity.setLicenseStatus(licensesRequest.getLicenseStatus());
        licensesEntity.setIssuedBy(licensesRequest.getIssuedBy());
        licensesEntity.setDateOfIssue(licensesRequest.getDateOfIssue());
        licensesEntity.setExpirationDate(licensesRequest.getExpirationDate());
        repository.save(licensesEntity);
        kafkaUtil.sendMessage(licensesEntity);
    }

    public boolean isValidLicenseStatus(String statusString) {
        try {
            LicenseStatus.valueOf(statusString);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }


}
