package com.asosyalbebe.samplespring.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.asosyalbebe.samplespring.user.model.AclUrl;
import com.asosyalbebe.samplespring.user.model.User;
import com.asosyalbebe.samplespring.utils.dao.BaseDaoImpl;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Override
    public User findUserByUsername(String username) {
	return findEntityByPropertyUnique(User.class, "username", username);
    }

    @Override
    public User findUserByEmail(String email) {
	return findEntityByPropertyUnique(User.class, "email", email);
    }

    @Override
    public User findUserById(Long id) {
	return (User) findById(User.class, id);
    }

    @Override
    public void saveUser(User user) {
	save(user);
    }

    @Override
    public void updateUser(User user) {
	update(user);
    }

    @Override
    public List<AclUrl> findAllUrls() {
	return findAllEntities(AclUrl.class);
    }

}
