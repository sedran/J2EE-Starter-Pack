package com.asosyalbebe.samplespring.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.asosyalbebe.samplespring.user.model.AppUrl;
import com.asosyalbebe.samplespring.user.model.Privilege;
import com.asosyalbebe.samplespring.user.model.User;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder bcryptPasswordEncoder;

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
		
		user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));

		Privilege privilege = new Privilege();
		privilege.setName("PRV_HOMEPAGE");
		user.getAuthorities().add(privilege);
		return user;
	}

	@Override
	public List<AppUrl> getAllAppUrls() {
		Set<Privilege> privileges = new HashSet<Privilege>();
		Privilege privilege = new Privilege();
		privilege.setName("PRV_HOMEPAGE");
		privileges.add(privilege);

		List<AppUrl> list = new ArrayList<AppUrl>();
		list.add(new AppUrl(1L, "/", false, privileges));
		return list;
	}

}
