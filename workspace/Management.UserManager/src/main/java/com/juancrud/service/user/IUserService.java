package com.juancrud.service.user;

import java.util.Collection;

import com.juancrud.entity.User;
import com.juancrud.entity.UserPermission;
import com.juancrud.framework.service.interfaces.IServiceResponse;

public interface IUserService {

	// User methods
	IServiceResponse saveUser(User user);

	IServiceResponse deleteUser(long userId);

	User getUser(long userId);
	
	User getUser(String userName);

	Collection<User> getAllUsers();
	
	IServiceResponse authenticateUser(User user, String password);
	

	// UserPermission methods
	IServiceResponse saveUserPermission(UserPermission userPermission);

	IServiceResponse deleteUserPermission(long userPermissionId);

	UserPermission getUserPermission(long userPermissionId);

	Collection<UserPermission> getAllUserPermissions();
	
	IServiceResponse linkUserPermission(long userId, long userPermissionId);

	IServiceResponse unlinkUserPermission(long userId, long userPermissionId);

}
