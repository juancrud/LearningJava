package com.juancrud.dao.user;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.juancrud.entity.User;
import com.juancrud.framework.dao.GenericDao;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserDao extends GenericDao<User, Long> implements IUserDao {

	public User findByUserName(String userName) {
		TypedQuery<User> query = this.getEntityManager().createQuery("SELECT u FROM User u WHERE u.userName=:userName", User.class);
		query.setParameter("userName", userName);
		return query.getSingleResult();
	}

}
