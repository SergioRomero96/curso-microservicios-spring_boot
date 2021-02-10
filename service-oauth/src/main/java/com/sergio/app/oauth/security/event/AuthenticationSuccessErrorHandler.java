package com.sergio.app.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sergio.app.commons.users.models.entity.User;
import com.sergio.app.oauth.services.IUserService;

import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher{
	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
	
	@Autowired
	private IUserService userService;
	@Autowired
	private Environment env;
	
	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		if(authentication.getName().equalsIgnoreCase(env.getProperty("config.security.oauth.client.id"))){
            return;
        }
		
		UserDetails userDetail = (UserDetails) authentication.getPrincipal();
		String message = "Success Login: " + userDetail.getUsername();
		System.out.println(message);
		log.info(message);
		
		User user = userService.findByUsername(authentication.getName());
		if(user.getLoginAttempts() != null && user.getLoginAttempts() > 0) {
			user.setLoginAttempts(0);
			userService.update(user, user.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String message = "Error en el Login: " + exception.getMessage();
		log.error(message);
		System.out.println(message);
		
		try {
			User user = userService.findByUsername(authentication.getName());
			if(user.getLoginAttempts() == null ) {
				user.setLoginAttempts(0);
			}
			
			log.info("Intentos actual es de: " + user.getLoginAttempts());
			user.setLoginAttempts(user.getLoginAttempts()+1);
			log.info("Intentos después es de: " + user.getLoginAttempts());
			if(user.getLoginAttempts() >= 3) {
				log.error(String.format("El usuario %s des-habilitado por máximos intentos.", user.getUsername()));
				user.setEnabled(false);
			}
			
			userService.update(user, user.getId());
		} catch (FeignException e) {
			log.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
			
		}
		
		
	}

}
