package pe.gob.mtc.licencias.cloudgateway.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http) {
        return http.authorizeExchange(auth -> auth
                        .pathMatchers(HttpMethod.GET, "/api/user-service/**").hasAnyRole("ADMINISTRATOR", "COORDINATOR")
                        .pathMatchers(HttpMethod.GET,"/api/customer-query-service/**").hasAnyRole("ADMINISTRATOR", "COORDINATOR", "RECEPTIONIST")
                        .pathMatchers(HttpMethod.POST,"/api/customer-command-service/**").hasAnyRole("ADMINISTRATOR", "COORDINATOR", "RECEPTIONIST")
                        .pathMatchers(HttpMethod.GET,"/api/licenses-query-service/licenses/validateLicense/**").hasAnyRole("ADMINISTRATOR", "COORDINATOR", "RECEPTIONIST", "CUSTOMER")
                        .pathMatchers(HttpMethod.GET,"/api/licenses-query-service/licenses/listLicenseByLicenseNumber/**").hasAnyRole("ADMINISTRATOR", "COORDINATOR", "RECEPTIONIST", "CUSTOMER")
                        .pathMatchers(HttpMethod.GET,"/api/licenses-query-service/**").hasAnyRole("ADMINISTRATOR", "COORDINATOR", "RECEPTIONIST")
                        .pathMatchers(HttpMethod.POST,"/api/licenses-command-service/**").hasAnyRole("ADMINISTRATOR", "COORDINATOR", "RECEPTIONIST")
                        .pathMatchers(HttpMethod.PATCH,"/api/licenses-command-service/**").hasAnyRole("ADMINISTRATOR", "COORDINATOR")
                        //.pathMatchers( "/api/product-service/**").permitAll()
                        //.pathMatchers(HttpMethod.POST, "/api/user-service/**").hasAnyRole("ADMIN", "SUPERVISOR")
                        .anyExchange().authenticated()
                )
                .addFilterAt(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }

}

