package co.simplon.game.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.auth0.jwt.algorithms.Algorithm;

@Configuration
public class SecurityConfig {

    @Value("${gameStation.auth.rounds}")
    private int rounds;
    @Value("${gameStation.auth.issuer}")
    private String issuer;
    @Value("${gameStation.auth.secret}")
    private String secret;
    @Value("${gameStation.auth.tokenExpiration}")
    private long tokenExpiration;

    @Bean
    public AuthHelper authHelper() {
	Algorithm algorithm = Algorithm.HMAC256(secret);
	PasswordEncoder encoder = new BCryptPasswordEncoder(
		rounds);

	return new AuthHelper.Builder().algorithm(algorithm)
		.passwordEncoder(encoder).issuer(issuer)
		.expiration(tokenExpiration).build();
    }

}
