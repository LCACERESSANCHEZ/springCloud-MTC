package pe.gob.mtc.licencias.commonmodelsmtc.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer")
public class CustomerEntity extends GenericEntity<CustomerEntity>  implements Serializable {

    @Serial
    private static final long serialVersionUID = 129348938L;

    @Id
    private String id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastname;

    @NonNull
    private String documentNumber;

    @NonNull
    private Date dateOfBirth; //"1990-01-01"

    @NonNull
    private Address address;

}
