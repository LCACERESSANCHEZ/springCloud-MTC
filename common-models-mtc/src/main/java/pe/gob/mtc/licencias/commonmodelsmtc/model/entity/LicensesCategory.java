package pe.gob.mtc.licencias.commonmodelsmtc.model.entity;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Data
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class LicensesCategory implements Serializable{

    @NonNull
    private String category;
    @NonNull
    private String dateOfIssue; //fecha de emision
    @NonNull
    private String expirationDate;

}
