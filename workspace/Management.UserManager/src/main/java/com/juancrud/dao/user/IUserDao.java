package com.juancrud.dao.user;

import com.juancrud.entity.User;
import com.juancrud.framework.dao.interfaces.IGenericDao;

public interface IUserDao extends IGenericDao<User, Long> {

	public User findByUserName(String username);

}
