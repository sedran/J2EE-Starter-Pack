package com.asosyalbebe.samplespring.user.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.asosyalbebe.samplespring.user.dao.UserDao;
import com.asosyalbebe.samplespring.user.model.AclUrl;
import com.asosyalbebe.samplespring.user.model.User;
import com.asosyalbebe.samplespring.utils.Logger;

public class UserServiceImpl implements UserService {
    protected Logger log = Logger.getLogger(getClass());

    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    @Autowired
    private UserDao userDao;

    @PostConstruct
    public void postConstruct() {
	log.debug("123456 hash is {0}", bcryptPasswordEncoder.encode("123456"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	// login can be done via either email or username
	User user = userDao.findUserByUsername(username);
	if (user == null) {
	    user = userDao.findUserByEmail(username);
	}

	if (user == null) {
	    throw new UsernameNotFoundException("error.usernameNotFound");
	}

	return user;
    }

    @Override
    public List<AclUrl> getAllAppUrls() {
	return userDao.findAllUrls();
    }

    @Override
    public List<User> getUsers(int firstResult, int maxResult) {
	return userDao.findUsers(firstResult, maxResult);
    }

}
