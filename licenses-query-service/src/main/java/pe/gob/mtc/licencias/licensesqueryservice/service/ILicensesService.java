package pe.gob.mtc.licencias.licensesqueryservice.service;

import pe.gob.mtc.licencias.licensesqueryservice.model.response.ValidateLicensesResponse;

import java.util.List;

public interface ILicensesService {

    List<ValidateLicensesResponse> validateLicense (String documentNumber);
    List<ValidateLicensesResponse> listLicensesByLicenseStatus (String licenseStatus);
    List<ValidateLicensesResponse> listLicenseByCategory (String licenseStatus);
    ValidateLicensesResponse listLicenseByLicenseNumber (String licenseNumber);

}
