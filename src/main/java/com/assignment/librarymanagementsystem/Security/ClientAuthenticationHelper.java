package com.assignment.librarymanagementsystem.Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ClientAuthenticationHelper {

	@Value("${api.key}")
	private String apiKey;

	public boolean validateApiKey(String requestApiKey) {
		boolean isPresent = false;
		if (requestApiKey.equals(apiKey)) {
			isPresent = true;
		}
		return isPresent;
	}

}
