package pe.gob.mtc.licencias.customercommandservice.model.dto;

import lombok.*;
import pe.gob.mtc.licencias.commonmodelsmtc.model.entity.Address;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NonNull
    private String firstName;
    @NonNull
    private String lastname;
    @NonNull
    private String documentNumber;
    @NonNull
    private String dateOfBirth; //"1990-01-01"
    @NonNull
    private Address address;

}
