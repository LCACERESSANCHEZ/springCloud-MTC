package pe.gob.mtc.licencias.commonmodelsmtc.model.entity;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @NonNull
    private String street;

    @NonNull
    private String district;

    @NonNull
    private String department;

}
