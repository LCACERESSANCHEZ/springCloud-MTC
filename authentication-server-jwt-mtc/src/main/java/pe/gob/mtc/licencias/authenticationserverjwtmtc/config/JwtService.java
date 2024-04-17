package pe.gob.mtc.licencias.authenticationserverjwtmtc.config;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pe.gob.mtc.licencias.authenticationserverjwtmtc.model.entity.UserEntity;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {


    @Value("${mtc.security.key:mitocode}")
    private String mtcKey;


    public String generateToken(UserDetails userDetails) {

        UserEntity userEntity = (UserEntity) userDetails;


        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("email", userEntity.getEmail());
        customClaims.put("lastname", userEntity.getLastname());
//        customClaims.put("password", userEntity.getPassword());
        customClaims.put("authorities", userEntity.getRoles());


        return Jwts.builder()
                .setSubject(userEntity.getUsername())
                .setIssuedAt(new Date())
                .signWith(getSignKey())
                .setExpiration(new Date(System.currentTimeMillis() + (300 * 1000)))
                .addClaims(customClaims)
                .compact();
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(Base64.getEncoder().encode(mtcKey.getBytes()));
    }

}
