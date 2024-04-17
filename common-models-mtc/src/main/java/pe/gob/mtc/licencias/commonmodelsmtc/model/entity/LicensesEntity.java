package pe.gob.mtc.licencias.commonmodelsmtc.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "licenses")
public class LicensesEntity extends GenericEntity<LicensesEntity> implements Serializable {

    @Serial
    private static final long serialVersionUID = 129348938L;

    @Id
    private String id;

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
