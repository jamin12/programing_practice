package com.example.fcmpractice.security.utils;

import java.security.Key;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.example.fcmpractice.controller.member.dto.MemberAdepter;
import com.example.fcmpractice.domain.jwt.domain.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider implements InitializingBean {
	private static final String AUTHORITIES_KEY = "auth";

	private final String secret;
	private Key key;

	public TokenProvider(@Value("${jwt.secret}") String secret) {
		this.secret = secret;
	}

	@Override
	public void afterPropertiesSet() {
		byte[] keyBytes = Base64.getDecoder().decode(secret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	/**token 생성 algorithm */
	public String generateToken(Authentication authentication, Jwt jwt) {
		String authorities = authentication.getAuthorities().stream()
			.map(GrantedAuthority::getAuthority)
			.collect(Collectors.joining(","));

		return Jwts.builder()
			.setSubject(authentication.getName())
			.claim(AUTHORITIES_KEY, authorities)
			.claim("jti", jwt.getId())
			.claim("token_type", jwt.getTokenType())
			.signWith(key, SignatureAlgorithm.HS512)
			.setExpiration(Date.from(jwt.getExpirationDate().atZone(ZoneId.systemDefault()).toInstant()))
			.compact();
	}

	/**인증 정보 조회 */
	public Authentication getAuthentication(String token) {
		Claims claims = Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody();
		Collection<? extends GrantedAuthority> authorities =
			Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
				.map(SimpleGrantedAuthority::new)
				.toList();

		User user = new User(claims.getSubject(), "", authorities);
		MemberAdepter principal = new MemberAdepter(user);
		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}

	/**token 유효성 검증 */
	public Claims validateToken(String token) {
		try {
			return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			throw new JwtException("잘못된 JWT 서명입니다.", e);
		} catch (ExpiredJwtException e) {
			throw new JwtException("만료된 JWT 토큰입니다.", e);
		} catch (UnsupportedJwtException e) {
			throw new JwtException("지원하지 않는 JWT 토큰입니다.", e);
		} catch (IllegalArgumentException e) {
			throw new JwtException("JWT 토큰이 잘못되었습니다.", e);
		}
	}

}
