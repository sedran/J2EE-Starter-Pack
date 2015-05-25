package com.asosyalbebe.samplespring.user.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.asosyalbebe.samplespring.user.model.AclUrl;
import com.asosyalbebe.samplespring.user.model.User;
import com.asosyalbebe.samplespring.utils.dao.BaseDaoImpl;

@Repository
@SuppressWarnings("unchecked")
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

    @Override
    public List<User> findUsers(int firstResult, int maxResult) {
	Criteria criteria = currentSession().createCriteria(User.class);
	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	criteria.setFirstResult(firstResult);
	criteria.setMaxResults(maxResult);
	return criteria.list();
    }

}
