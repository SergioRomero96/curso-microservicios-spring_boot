package com.sergio.app.oauth.services;

import com.sergio.app.commons.users.models.entity.User;

public interface IUserService {
	public User findByUsername(String username);
	
	public User update(User user, Long id);
}
