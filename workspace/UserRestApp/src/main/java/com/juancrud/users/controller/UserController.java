package com.juancrud.users.controller;

import java.lang.reflect.Type;
import java.util.Collection;

import javax.validation.Valid;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juancrud.entity.User;
import com.juancrud.entity.UserPermission;
import com.juancrud.entity.enums.UserStatus;
import com.juancrud.framework.model.ResponseModel;
import com.juancrud.framework.service.interfaces.IServiceResponse;
import com.juancrud.service.user.IUserService;
import com.juancrud.users.model.AuthenticateUserModel;
import com.juancrud.users.model.ChangePasswordModel;
import com.juancrud.users.model.UserModel;
import com.juancrud.users.model.UserPermissionModel;

@Controller
@RequestMapping("/users")
public class UserController extends GenericController {
	
	private static final String TempPassword = "P@ssw0rd";

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel saveUser(@Valid @RequestBody UserModel userModel) {
		User user = mapper.map(userModel, User.class);
		IServiceResponse response = userService.saveUser(user);
		ResponseModel responseModel = mapper.map(response, ResponseModel.class);
		
		if(responseModel.isSuccess()) {
			UserModel newUserModel = mapper.map(user, UserModel.class);
			responseModel.setEntity(newUserModel);
		}
		
        return responseModel;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel deleteUser(@PathVariable("id") long userId) {
		IServiceResponse response = userService.deleteUser(userId);
		return mapper.map(response, ResponseModel.class);
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserModel getUser(@PathVariable("id") long userId) {
		User user = userService.getUser(userId);
		Type targetType = new TypeToken<UserModel>() {}.getType();
		return mapper.map(user, targetType);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<UserModel> getUsers() {
		Collection<User> users = userService.getAllUsers();
		Type targetType = new TypeToken<Collection<UserModel>>() {}.getType();
		return mapper.map(users, targetType);
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel changeUserPassword(@Valid @RequestBody ChangePasswordModel model){
		User user = userService.getUser(model.getId());
		user.setPassword(model.getPassword());
		user.setStatus(UserStatus.active);
		
		IServiceResponse response = userService.saveUser(user);
		ResponseModel responseModel = mapper.map(response, ResponseModel.class);
		
		if(responseModel.isSuccess()) {
			UserModel newUserModel = mapper.map(user, UserModel.class);
			responseModel.setEntity(newUserModel);
		}
		
		return responseModel;
	}
	
	@RequestMapping(value = "/reset/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel resetUser(@PathVariable("id") long userId){
		User user = userService.getUser(userId);
		user.setPassword(TempPassword);
		user.setStatus(UserStatus.reset);
		
		IServiceResponse response = userService.saveUser(user);
		return mapper.map(response, ResponseModel.class);
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel authenticateUser(@Valid @RequestBody AuthenticateUserModel model){
		User user = userService.getUser(model.getUserName());
		
		IServiceResponse response = userService.authenticateUser(user, model.getPassword());
		ResponseModel responseModel = mapper.map(response, ResponseModel.class);
		
		if(responseModel.isSuccess()) {
			UserModel newUserModel = mapper.map(user, UserModel.class);
			responseModel.setEntity(newUserModel);
		}
		
		return responseModel;
	}
	
	@RequestMapping(value = "/savePermission", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel saveUserPermission(@Valid @RequestBody UserPermissionModel userPermissionModel) {
		UserPermission userPermission = mapper.map(userPermissionModel, UserPermission.class);
		IServiceResponse response = userService.saveUserPermission(userPermission);
		ResponseModel responseModel = mapper.map(response, ResponseModel.class);
		
		if(responseModel.isSuccess()) {
			UserPermissionModel newUserPermissionModel = mapper.map(userPermission, UserPermissionModel.class);
			responseModel.setEntity(newUserPermissionModel);
		}
		
        return responseModel;
	}
	
	@RequestMapping(value = "/deletePermission/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel deleteUserPermission(@PathVariable("id") long userPermissionId) {
		IServiceResponse response = userService.deleteUserPermission(userPermissionId);
		return mapper.map(response, ResponseModel.class);
	}
	
	@RequestMapping(value = "/getPermission/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserPermissionModel getUserPermission(@PathVariable("id") long userPermissionId) {
		UserPermission userPermission = userService.getUserPermission(userPermissionId);
		Type targetType = new TypeToken<UserPermissionModel>() {}.getType();
		return mapper.map(userPermission, targetType);
	}
	
	@RequestMapping(value = "/getAllPermissions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<UserPermissionModel> getUserPermissions() {
		Collection<UserPermission> userPermissions = userService.getAllUserPermissions();
		Type targetType = new TypeToken<Collection<UserPermissionModel>>() {}.getType();
		return mapper.map(userPermissions, targetType);
	}
	
	@RequestMapping(value = "/linkPermission/{userId}/{permissionId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel linkPermission(@PathVariable("userId") long userId, @PathVariable("permissionId") long userPermissionId) {
		IServiceResponse response = userService.linkUserPermission(userId, userPermissionId);
		return mapper.map(response, ResponseModel.class);
	}
	
	@RequestMapping(value = "/unlinkPermission/{userId}/{permissionId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseModel unlinkPermission(@PathVariable("userId") long userId, @PathVariable("permissionId") long userPermissionId) {
		IServiceResponse response = userService.unlinkUserPermission(userId, userPermissionId);
		return mapper.map(response, ResponseModel.class);
	}
	
	@RequestMapping(value = "/getPermissionsByUser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<UserPermissionModel> getPermissionsByUser(@PathVariable("id") long userId) {
		User user = userService.getUser(userId);
		Type targetType = new TypeToken<Collection<UserPermissionModel>>() {}.getType();
		return mapper.map(user.getUserPermissions(), targetType);
	}
	
	@RequestMapping(value = "/getUsersByPermission/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Collection<UserModel> getUsersByPermission(@PathVariable("id") long userPermissionId) {
		UserPermission userPermission = userService.getUserPermission(userPermissionId);
		Type targetType = new TypeToken<Collection<UserModel>>() {}.getType();
		return mapper.map(userPermission.getUsers(), targetType);
	}

}
