package com.example.fcmpractice.security.filter;

import java.io.IOException;
import java.util.UUID;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.fcmpractice.domain.jwt.domain.Jwt;
import com.example.fcmpractice.domain.jwt.domain.TokenType;
import com.example.fcmpractice.domain.jwt.repository.JwtRepository;
import com.example.fcmpractice.security.utils.TokenProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	public static final String AUTHORIZATION_HEADER = "Authorization";
	private final TokenProvider tokenProvider;
	private final JwtRepository jwtRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String jwt = resolveToken(request);

		if (StringUtils.hasText(jwt)) {
			Claims claims = tokenProvider.validateToken(jwt);
			Jwt foundJwt = jwtRepository.findById(UUID.fromString(claims.get("jti").toString()))
				.orElseThrow(() -> new JwtException("Not found jwt."));

			TokenType tokenType = TokenType.valueOf(foundJwt.getTokenType().name());

			if (TokenType.REFRESH.equals(tokenType) && !isAllowedWithRefreshToken(requestURI)) {
				throw new JwtException("지원하지 않는 토큰입니다.");
			}

			Authentication authentication = tokenProvider.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	/**
	 * 토큰 정보 추출
	 *
	 * @param request http request
	 * @return 토큰 정보 반환
	 */
	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	private boolean isAllowedWithRefreshToken(String requestURI) {
		return "/regenerate".equals(requestURI);
	}
}
