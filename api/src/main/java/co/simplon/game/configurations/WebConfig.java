package co.simplon.game.configurations;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebConfig implements WebMvcConfigurer {

    @Value("${gameStation.cors.allowedOrigins}")
    private String[] allowedOrigins;

    @Value("${gameStation.auth.secret}")
    private String secret;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http)
	    throws Exception {
	http.cors().and().csrf().disable()
		.authorizeRequests()
		.requestMatchers("/ws/**", "/sign-in",
			"/sign-up", "/game/**")
		.permitAll().anyRequest().authenticated()
		.and().oauth2ResourceServer().jwt();
	return http.build();
    }

    @Bean
    JwtAuthenticationConverter authentificationConverter() {
	JwtGrantedAuthoritiesConverter autoritiesConverter = new JwtGrantedAuthoritiesConverter();
	autoritiesConverter
		.setAuthoritiesClaimName("roles");
	autoritiesConverter.setAuthorityPrefix("ROLE_");
	JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
	authenticationConverter
		.setJwtGrantedAuthoritiesConverter(
			autoritiesConverter);
	return authenticationConverter;
    }

    @Bean
    JwtDecoder jwtdecoder() throws Exception {
	SecretKey secretKey = new SecretKeySpec(
		secret.getBytes(), "Hmacsha256");
	return NimbusJwtDecoder.withSecretKey(secretKey)
		.build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
	registry.addMapping("/**")
		.allowedOrigins(allowedOrigins)
		.allowedMethods("POST", "GET", "PUT",
			"PATCH", "DELETE");
    }
}
