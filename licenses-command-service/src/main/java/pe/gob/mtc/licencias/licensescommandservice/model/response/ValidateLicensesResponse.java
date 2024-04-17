package pe.gob.mtc.licencias.licensescommandservice.model.response;

import lombok.*;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.LicenseStatus;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidateLicensesResponse implements Serializable {
    private String licenseNumber;
    private LicenseStatus licenseStatus;
    private String category;
    private String dateOfIssue; //fecha de emision
    private String expirationDate;

}
