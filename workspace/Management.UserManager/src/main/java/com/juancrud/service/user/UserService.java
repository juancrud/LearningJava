package com.juancrud.service.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juancrud.dao.user.IUserDao;
import com.juancrud.dao.user.IUserPermissionDao;
import com.juancrud.entity.User;
import com.juancrud.entity.UserPermission;
import com.juancrud.entity.enums.UserStatus;
import com.juancrud.framework.service.ServiceResponse;
import com.juancrud.framework.service.ServiceResponseMessage;
import com.juancrud.framework.service.enums.ResponseType;
import com.juancrud.framework.service.interfaces.IServiceResponse;
import com.juancrud.users.service.ServiceErrorCodes;
import com.juancrud.utilities.encrypter.IEncrypter;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IUserPermissionDao userPermissionDao;

	@Autowired
	private IEncrypter encrypter;

	// User methods
	public IServiceResponse saveUser(User user) {
		boolean isNew = user.getId() == 0;
		ResponseType responseType = isNew ? ResponseType.CREATE : ResponseType.UPDATE;
		IServiceResponse serviceResponse = new ServiceResponse(responseType);

		if (user.getPassword() != null && !user.getPassword().equals("")) {
			String encryptedPassword = encrypter.encrypt(user.getPassword());
			user.setPassword(encryptedPassword);
		}

		if (isNew) {
			userDao.create(user);
		} else {
			userDao.update(user);
		}

		return serviceResponse;
	}

	public IServiceResponse deleteUser(long userId) {
		IServiceResponse serviceResponse = new ServiceResponse(ResponseType.DELETE);
		User user = userDao.find(userId);

		serviceResponse.getMessages().addAll(validateDeleteUser(user));
		if (serviceResponse.isSuccess()) {
			userDao.delete(user);
		}

		return serviceResponse;
	}

	public User getUser(long userId) {
		return userDao.find(userId);
	}

	public User getUser(String userName) {
		return userDao.findByUserName(userName);
	}

	public Collection<User> getAllUsers() {
		return userDao.findAll();
	}

	public IServiceResponse authenticateUser(User user, String password) {
		IServiceResponse serviceResponse = new ServiceResponse(ResponseType.READ);

		int errorCode = -1;
		if (user.getStatus() == UserStatus.deleted || user.getStatus() == UserStatus.disabled) {
			errorCode = ServiceErrorCodes.USER_NOT_FOUND;
		} 
		else if (user.getStatus() == UserStatus.blocked) {
			errorCode = ServiceErrorCodes.USER_BLOCKED;
		}
		else if (!encrypter.validate(password, user.getPassword())) {
			errorCode = ServiceErrorCodes.AUTHENTICATION_ERROR;
		}
		else if(user.getStatus() == UserStatus.incomplete || user.getStatus() == UserStatus.reset) {
			errorCode = ServiceErrorCodes.USER_NEEDS_PASSWORD_CHANGE;
		}

		if (errorCode != -1) {
			serviceResponse.getMessages().add(new ServiceResponseMessage(errorCode));
		}

		return serviceResponse;
	}

	private Collection<ServiceResponseMessage> validateDeleteUser(User user) {
		Collection<ServiceResponseMessage> messages = new ArrayList<ServiceResponseMessage>();

		return messages;
	}

	// UserPermission methods
	public IServiceResponse saveUserPermission(UserPermission userPermission) {
		boolean isNew = userPermission.getId() == 0;
		ResponseType responseType = isNew ? ResponseType.CREATE : ResponseType.UPDATE;
		IServiceResponse serviceResponse = new ServiceResponse(responseType);

		if (isNew) {
			userPermissionDao.create(userPermission);
		} else {
			userPermissionDao.update(userPermission);
		}

		return serviceResponse;
	}

	public IServiceResponse deleteUserPermission(long userPermissionId) {
		IServiceResponse serviceResponse = new ServiceResponse(ResponseType.DELETE);
		UserPermission userPermission = userPermissionDao.find(userPermissionId);

		serviceResponse.getMessages().addAll(validateDeleteUserPermission(userPermission));
		if (serviceResponse.isSuccess()) {
			userPermissionDao.delete(userPermission);
		}

		return serviceResponse;
	}

	public UserPermission getUserPermission(long userPermissionId) {
		return userPermissionDao.find(userPermissionId);
	}

	public Collection<UserPermission> getAllUserPermissions() {
		return userPermissionDao.findAll();
	}

	private Collection<ServiceResponseMessage> validateDeleteUserPermission(UserPermission userPermission) {
		Collection<ServiceResponseMessage> messages = new ArrayList<ServiceResponseMessage>();

		// Check if UserPermission is already used
		// Collection<User> users =
		// userDao.findUsersByUserPermission(userPermission);
		// if(users.size() > 0) {
		// messages.add(new ServiceResponseMessage(""));
		// }

		return messages;
	}

	public IServiceResponse linkUserPermission(long userId, long userPermissionId) {
		IServiceResponse serviceResponse = new ServiceResponse(ResponseType.UPDATE);
		
		User user = userDao.find(userId);
		UserPermission userPermission = userPermissionDao.find(userPermissionId);
		
		int errorCode = -1;
		if (user == null) {
			errorCode = ServiceErrorCodes.USER_NOT_FOUND;
		}
		else if(userPermission == null) {
			errorCode = ServiceErrorCodes.USER_PERMISSION_NOT_FOUND;
		}
		
		if(errorCode == -1){
			user.getUserPermissions().add(userPermission);
		}
		else {
			serviceResponse.getMessages().add(new ServiceResponseMessage(errorCode));
		}

		return serviceResponse;
	}
	
	public IServiceResponse unlinkUserPermission(long userId, long userPermissionId) {
		IServiceResponse serviceResponse = new ServiceResponse(ResponseType.DELETE);
		
		User user = userDao.find(userId);
		UserPermission userPermission = userPermissionDao.find(userPermissionId);
		
		int errorCode = -1;
		if (user == null) {
			errorCode = ServiceErrorCodes.USER_NOT_FOUND;
		}
		else if(userPermission == null) {
			errorCode = ServiceErrorCodes.USER_PERMISSION_NOT_FOUND;
		}
		
		if(errorCode == -1){
			user.getUserPermissions().remove(userPermission);
		}
		else {
			serviceResponse.getMessages().add(new ServiceResponseMessage(errorCode));
		}

		return serviceResponse;
	}


}
