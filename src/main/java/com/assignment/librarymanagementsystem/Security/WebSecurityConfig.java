package com.assignment.librarymanagementsystem.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

	private final ClientAuthenticationHelper authServiceHelper;

	public WebSecurityConfig(ClientAuthenticationHelper authServiceHelper) {
		this.authServiceHelper = authServiceHelper;
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		// add the ApiKeyFilter to the security chain
		http.addFilterBefore(new ApiKeyFilter(authServiceHelper), AnonymousAuthenticationFilter.class);

		// configure the security chain to authenticate all endpoints
		http.authorizeHttpRequests(
				requests -> requests.requestMatchers("/h2-console/**").permitAll().requestMatchers("/actuator/**")
						.permitAll().requestMatchers("/api/**").authenticated().anyRequest().denyAll());

		http.csrf().disable();

		// since this is an API app, configure it to be stateless
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}

}