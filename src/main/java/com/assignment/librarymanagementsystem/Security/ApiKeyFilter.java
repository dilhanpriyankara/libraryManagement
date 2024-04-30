package com.assignment.librarymanagementsystem.Security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApiKeyFilter extends OncePerRequestFilter {

	private final ClientAuthenticationHelper authServiceHelper;

	public ApiKeyFilter(ClientAuthenticationHelper authServiceHelper) {
		this.authServiceHelper = authServiceHelper;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String reqApiKey = request.getHeader("api-key");

		List<String> allowedPaths = Arrays.asList("/actuator", "/h2-console");
		String requestedPath = request.getRequestURI();
		if (allowedPaths.stream().anyMatch(requestedPath::startsWith)) {
			filterChain.doFilter(request, response);
			return;
		}

		if (reqApiKey == null) {
			getErrorMessageForInvalidApiKey(response);
			return;
		}
		boolean isApiKeyValid = authServiceHelper.validateApiKey(reqApiKey);
		if (!isApiKeyValid) {
			getErrorMessageForInvalidApiKey(response);
			return;
		}
		var authenticationToken = new UsernamePasswordAuthenticationToken(reqApiKey, reqApiKey,
				Collections.emptyList());
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
	}

	private void getErrorMessageForInvalidApiKey(HttpServletResponse response) throws IOException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().write("{\"error\": \"Invalid API Key\"}");
	}

}
