package com.asosyalbebe.samplespring.user.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.asosyalbebe.samplespring.user.model.AclUrl;

public interface UserService extends UserDetailsService {
	public List<AclUrl> getAllAppUrls();
}
