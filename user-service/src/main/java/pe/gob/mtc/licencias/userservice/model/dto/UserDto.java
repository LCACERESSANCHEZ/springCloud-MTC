package pe.gob.mtc.licencias.userservice.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NonNull
    private String name;

    @NonNull
    private String lastname;

    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String[] roles;
}
