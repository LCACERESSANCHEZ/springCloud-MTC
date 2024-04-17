package pe.gob.mtc.licencias.licensescommandservice.model.request;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.LicenseStatus;

import java.io.Serializable;

@Data
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class LicensesRequest implements Serializable {

    @NonNull
    private String licenseOfType;
    @NonNull
    private String restrictions;
    @NonNull
    private LicenseStatus licenseStatus;
    @NonNull
    private String issuedBy;
    @NonNull
    private String dateOfIssue; //fecha de emision
    @NonNull
    private String expirationDate;

}
