package com.asosyalbebe.samplespring.user.dao;

import java.util.List;

import com.asosyalbebe.samplespring.user.model.AclUrl;
import com.asosyalbebe.samplespring.user.model.User;
import com.asosyalbebe.samplespring.utils.dao.BaseDao;

public interface UserDao extends BaseDao {
    public User findUserByUsername(String username);

    public User findUserByEmail(String email);

    public User findUserById(Long id);

    public void saveUser(User user);

    public void updateUser(User user);

    public List<AclUrl> findAllUrls();
}
