package pe.gob.mtc.licencias.authenticationserverjwtmtc.service;



import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.gob.mtc.licencias.authenticationserverjwtmtc.config.JwtService;
import pe.gob.mtc.licencias.authenticationserverjwtmtc.model.entity.UserEntity;
import pe.gob.mtc.licencias.authenticationserverjwtmtc.model.request.AuthenticationRequest;
import pe.gob.mtc.licencias.authenticationserverjwtmtc.model.request.RegisterRequest;
import pe.gob.mtc.licencias.authenticationserverjwtmtc.service.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public String authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        UserEntity userEntity = userRepository.findByUsername(request.username()).orElse(null);

        if(userEntity == null){
            throw new UsernameNotFoundException("Usuario no registrado en BD");
        }

        return jwtService.generateToken(userEntity);

    }


    public String register(RegisterRequest request) {

        UserEntity userEntity = UserEntity.builder()
                .username(request.username())
                .name(request.name())
                .lastname(request.lastname())
                .email(request.email())
                .roles(request.roles())
                .password(passwordEncoder.encode(request.password()))
                .build();

        userRepository.save(userEntity);


        return jwtService.generateToken(userEntity);

    }


}
