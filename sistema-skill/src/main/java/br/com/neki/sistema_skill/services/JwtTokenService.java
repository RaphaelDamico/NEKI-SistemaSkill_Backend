package br.com.neki.sistema_skill.services;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.neki.sistema_skill.entities.User;

@Service
public class JwtTokenService {

	@Value("${SECRET.KEY}")
	private String SECRET_KEY;

	@Value("${ISSUER}")
	private String ISSUER;

	public String generateToken(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
			return JWT.create().withIssuer(ISSUER).withIssuedAt(new Date()).withExpiresAt(expirationDate())
					.withSubject(user.getUsername()).sign(algorithm);
		} catch (JWTCreationException exception) {
			throw new JWTCreationException("Error generating token", exception);
		}
	}

	public String getSubjectFromToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
			return JWT.require(algorithm).withIssuer(ISSUER).build().verify(token).getSubject();
		} catch (JWTVerificationException exception) {
			throw new JWTVerificationException("Invalid or expired token");
		}
	}

	private Instant expirationDate() {
		return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(2).toInstant();
	}
}
