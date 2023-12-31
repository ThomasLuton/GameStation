package co.simplon.game.utils;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class AuthHelper {

    private final String issuer;
    private final long expiration;
    private final Algorithm algorithm;
    private final PasswordEncoder encoder;

    public AuthHelper(Builder builder) {
	this.issuer = builder.issuer;
	this.algorithm = builder.algorithm;
	this.encoder = builder.passwordEncoder;
	this.expiration = builder.expiration;
    }

    public String encode(String password) {
	return encoder.encode(password);
    }

    public Boolean matches(String candidate, String hash) {
	return encoder.matches(candidate, hash);
    }

    public String createJWT(List<String> roles,
	    String name) {
	Instant now = Instant.now();
	Instant expirationTime = now
		.plusSeconds(expiration);
	String[] rolesAsArray = roles
		.toArray(new String[roles.size()]);
	return JWT.create().withIssuer(issuer)
		.withSubject(name).withIssuedAt(now)
		.withExpiresAt(expirationTime)
		.withArrayClaim("roles", rolesAsArray)
		.sign(algorithm);
    }

    public Map<String, Object> getPrincipalInfo(
	    JwtAuthenticationToken principal) {

	Collection<String> authorities = principal
		.getAuthorities().stream()
		.map(GrantedAuthority::getAuthority)
		.collect(Collectors.toList());

	Map<String, Object> info = new HashMap<>();
	info.put("name", principal.getName());
	info.put("authorities", authorities);
	info.put("tokenAttributes",
		principal.getTokenAttributes());

	return info;
    }

    public static class Builder {
	private String issuer;
	private long expiration;
	private Algorithm algorithm;
	private PasswordEncoder passwordEncoder;

	public Builder() {

	}

	public Builder issuer(String issuer) {
	    this.issuer = issuer;
	    return this;
	}

	public Builder expiration(long expiration) {
	    this.expiration = expiration;
	    return this;
	}

	public Builder algorithm(Algorithm algorithm) {
	    this.algorithm = algorithm;
	    return this;
	}

	public Builder passwordEncoder(
		PasswordEncoder passwordEncoder) {
	    this.passwordEncoder = passwordEncoder;
	    return this;
	}

	public AuthHelper build() {
	    return new AuthHelper(this);
	}
    }

}
