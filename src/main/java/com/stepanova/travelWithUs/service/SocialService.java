package com.stepanova.travelWithUs.service;

import com.stepanova.travelWithUs.model.SocialAccount;

public interface SocialService {
	String getAuthorizeUrl();

	SocialAccount getSocialAccount(String authToken);
}
