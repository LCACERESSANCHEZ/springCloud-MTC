package pe.gob.mtc.licencias.licensescommandservice.service;

import pe.gob.mtc.licencias.licensescommandservice.model.dto.LicensesDto;
import pe.gob.mtc.licencias.licensescommandservice.model.request.LicensesRequest;

public interface ILicensesService {

    LicensesDto issueLicense(LicensesDto licensesDto);
    void updateLicense(String licenseNumber, LicensesRequest licensesRequest);

}
