package com.example.fcmpractice.security.application;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fcmpractice.common.TimeUtil;
import com.example.fcmpractice.domain.jwt.domain.Jwt;
import com.example.fcmpractice.domain.jwt.domain.TokenType;
import com.example.fcmpractice.domain.jwt.repository.JwtRepository;
import com.example.fcmpractice.security.dto.JwtRequest;
import com.example.fcmpractice.security.dto.JwtResponse;
import com.example.fcmpractice.security.dto.MemberSecurityRequest;
import com.example.fcmpractice.security.utils.TokenProvider;

@Service
@Transactional
public class AuthService {

	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final long accessTokenTime;
	private final long refreshTokenTime;
	private final JwtRepository jwtRepository;

	public AuthService(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder,
		@Value("${jwt.access-token-time}") long accessTokenTime,
		@Value("${jwt.refresh-token-time}") long refreshTokenTime, JwtRepository jwtRepository) {
		this.tokenProvider = tokenProvider;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.accessTokenTime = accessTokenTime;
		this.refreshTokenTime = refreshTokenTime;
		this.jwtRepository = jwtRepository;
	}

	public JwtResponse generateToken(MemberSecurityRequest memberSecurityRequest) {
		Authentication authentication = getAuthentication(memberSecurityRequest);

		return generateToken(authentication);
	}

	public JwtResponse regenerateToken(JwtRequest jwtRequest) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		deleteJwt(jwtRequest.getAccessTokenId());
		deleteJwt(jwtRequest.getRefreshTokenId());

		return generateToken(authentication);
	}

	public void deleteToken(JwtRequest jwtRequest) {
		deleteJwt(jwtRequest.getAccessTokenId());
		deleteJwt(jwtRequest.getRefreshTokenId());
	}

	private Authentication getAuthentication(MemberSecurityRequest memberSecurityRequest) {
		UsernamePasswordAuthenticationToken authenticationToken =
			new UsernamePasswordAuthenticationToken(memberSecurityRequest.getMemberId(),
				memberSecurityRequest.getPassword());

		//authenticationManagerBuilder가 호출되면서 CustomUserDetailService가 로드됨.
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		return authentication;
	}

	private JwtResponse generateToken(Authentication authentication) {
		LocalDateTime expirationDateAccess = TimeUtil.now().plusSeconds(getTokenTime(TokenType.ACCESS));
		LocalDateTime expirationDateRefresh = TimeUtil.now().plusSeconds(getTokenTime(TokenType.REFRESH));

		Jwt access = createJwt(TokenType.ACCESS, expirationDateAccess);
		Jwt refresh = createJwt(TokenType.REFRESH, expirationDateRefresh);

		String accessToken = tokenProvider.generateToken(authentication, access);
		String refreshToken = tokenProvider.generateToken(authentication, refresh);

		access.updateMemberId(authentication.getName());
		refresh.updateMemberId(authentication.getName());

		return new JwtResponse(access.getId(), accessToken, refresh.getId(), refreshToken);
	}

	private long getTokenTime(TokenType tokenType) {
		return switch (tokenType) {
			case ACCESS -> accessTokenTime;
			case REFRESH -> refreshTokenTime;
		};
	}

	private Jwt createJwt(TokenType tokenType, LocalDateTime expirationDate) {
		Jwt jwt = new Jwt(null, tokenType, expirationDate);

		return jwtRepository.save(jwt);
	}

	private void deleteJwt(UUID tokenId) {
		Jwt jwt = jwtRepository.findById(tokenId)
			.orElseThrow(() -> new NoSuchElementException("no such token"));
		jwtRepository.delete(jwt);
	}

}
