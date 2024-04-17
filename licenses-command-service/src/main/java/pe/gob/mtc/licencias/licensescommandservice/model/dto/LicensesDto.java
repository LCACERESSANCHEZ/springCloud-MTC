package pe.gob.mtc.licencias.licensescommandservice.model.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.LicenseStatus;

import java.io.Serializable;

@Data
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class LicensesDto implements Serializable {

    @NonNull
    private String personDocumentNumber;
    @NonNull
    private String licenseNumber;
    @NonNull
    private String licenseOfType;
    @NonNull
    private String restrictions;
    @NonNull
    private LicenseStatus licenseStatus;
    @NonNull
    private String issuedBy;
    //@NonNull
    //private List<LicensesCategory> licensesCategory;
    @NonNull
    private String category;
    @NonNull
    private String dateOfIssue; //fecha de emision
    @NonNull
    private String expirationDate;

}
