package pe.gob.mtc.licencias.licensesqueryservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
