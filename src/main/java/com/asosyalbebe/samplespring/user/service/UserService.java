package com.asosyalbebe.samplespring.user.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.asosyalbebe.samplespring.user.model.AclUrl;
import com.asosyalbebe.samplespring.user.model.User;

public interface UserService extends UserDetailsService {

    public List<User> getUsers(int firstResult, int maxResult);

    public List<AclUrl> getAllAppUrls();
}
