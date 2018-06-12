package com.juancrud.dao.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.juancrud.entity.UserPermission;
import com.juancrud.framework.dao.GenericDao;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserPermissionDao extends GenericDao<UserPermission, Long> implements IUserPermissionDao {
	
}
