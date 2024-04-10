package pe.gob.mtc.licencias.userservice.model.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String[] roles;

}
