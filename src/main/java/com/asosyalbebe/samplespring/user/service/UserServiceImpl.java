package com.asosyalbebe.samplespring.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.asosyalbebe.samplespring.user.model.AppUrl;
import com.asosyalbebe.samplespring.user.model.User;

public class UserServiceImpl implements UserService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = new User();
		user.setCreateDate(new Date());
		user.setEmail("serdarkuzucu13@gmail.com");
		user.setId(1L);
		user.setName("Serdar");
		user.setSurname("Kuzucu");
		user.setUsername(username);
		user.setPassword("123456");
		return user;
	}

	@Override
	public List<AppUrl> getAllAppUrls() {

		return null;
	}

}
