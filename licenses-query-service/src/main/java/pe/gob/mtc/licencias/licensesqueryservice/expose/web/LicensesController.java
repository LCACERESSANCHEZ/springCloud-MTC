package pe.gob.mtc.licencias.licensesqueryservice.expose.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.mtc.licencias.licensesqueryservice.model.response.ValidateLicensesResponse;
import pe.gob.mtc.licencias.licensesqueryservice.service.ILicensesService;
import pe.gob.mtc.licencias.licensesqueryservice.util.Constants;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(Constants.URL_LICENSES)
@RequiredArgsConstructor
public class LicensesController {

    private final ILicensesService service;

    @GetMapping("/validateLicense/{documentNumber}")
    public ResponseEntity<List<ValidateLicensesResponse>> validateLicense(
            @PathVariable(name = "documentNumber", required = true) String documentNumber){
        List<ValidateLicensesResponse> lstValidateLicense = service.validateLicense(documentNumber);
        return new ResponseEntity<>(lstValidateLicense, HttpStatus.OK);
    }


    @GetMapping("/listLicensesByLicenseStatus")
    public ResponseEntity<List<ValidateLicensesResponse>> listLicensesByLicenseStatus(@RequestParam String licenseStatus){
        List<ValidateLicensesResponse> lstValidateLicensesResponse = service.listLicensesByLicenseStatus(licenseStatus);
        return new ResponseEntity<>(lstValidateLicensesResponse, HttpStatus.OK);
    }

    @GetMapping("/listLicenseByCategory")
    public ResponseEntity<List<ValidateLicensesResponse>> listLicenseByCategory(@RequestParam String category){
        List<ValidateLicensesResponse> lstValidateLicensesResponse = service.listLicenseByCategory(category);
        return new ResponseEntity<>(lstValidateLicensesResponse, HttpStatus.OK);
    }

    @GetMapping("/listLicenseByLicenseNumber")
    public ResponseEntity<ValidateLicensesResponse> listLicenseByLicenseNumber(@RequestParam String licenseNumber){
        ValidateLicensesResponse lstValidateLicensesResponse = service.listLicenseByLicenseNumber(licenseNumber);
        return new ResponseEntity<>(lstValidateLicensesResponse, HttpStatus.OK);
    }
}
