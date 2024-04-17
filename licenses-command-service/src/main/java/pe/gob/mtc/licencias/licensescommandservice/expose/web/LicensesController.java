package pe.gob.mtc.licencias.licensescommandservice.expose.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.mtc.licencias.licensescommandservice.model.dto.LicensesDto;
import pe.gob.mtc.licencias.licensescommandservice.model.request.LicensesRequest;
import pe.gob.mtc.licencias.licensescommandservice.service.ILicensesService;
import pe.gob.mtc.licencias.licensescommandservice.util.Constants;


@Slf4j
@RestController
@RequestMapping(Constants.URL_LICENSES)
@RequiredArgsConstructor
public class LicensesController {

    private final ILicensesService service;

    @PostMapping("/issueLicense")//EMITIR LICENCIA
    public ResponseEntity<LicensesDto> issueLicense(@Valid @RequestBody LicensesDto licensesDto){

        try {
            log.info(licensesDto.toString());
            return new ResponseEntity<>(service.issueLicense(licensesDto), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PatchMapping ("/updateLicense/{licenseNumber}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> updateLicense(@PathVariable("licenseNumber") String licenseNumber,
                                                @RequestBody LicensesRequest licensesRequest) {
        service.updateLicense(licenseNumber, licensesRequest);
        return ResponseEntity.noContent().build();
    }

}
