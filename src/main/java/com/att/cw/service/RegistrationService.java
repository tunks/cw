package com.att.cw.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.cw.controller.open.RegistrationController;
import com.att.cw.dao.AuthorityRepository;
import com.att.cw.dto.UserRegistrationDto;
import com.att.cw.exception.JwtTokenMalformedException;
import com.att.cw.exception.UserAlreadyExistingException;
import com.att.cw.model.Authority;
import com.att.cw.model.AuthorityName;
import com.att.cw.model.User;
import com.att.cw.security.JwtUserDto;
import com.att.cw.security.JwtUtil;



@Service
public class RegistrationService
{
	@Resource
    private  UserService userService;
	@Resource
	private AuthorityRepository authoRepo;
	
	@Resource
	private JwtUtil jwtUtil;
	
	 private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);
	 
	 public User registerUser(UserRegistrationDto  userRegistrationDto) throws Exception
	 {
		 if(!userService.existsByEmail(userRegistrationDto.getEmail()))
		 {
			 User user=new User();
			 user.setEmail(userRegistrationDto.getEmail());
			 user.setPassword(userRegistrationDto.getPassword());
			 user.setName(userRegistrationDto.getName());
			 Authority autho = authoRepo.findByName(AuthorityName.ROLE_USER);
			 logger.info("Authority ID is :"+autho.getId());
		     userService.save(user);
		     logger.info("new User ID saved with ID  :"+user.getId());
		     user.getAuthorities().add(autho);
		     userService.save(user);
		     return user;
		 }
		 else
		 {
			 throw new UserAlreadyExistingException("This email ID is already registered");
		 }
	 }

	public void activateUser(String token) throws Exception 
	{
		String parsedEmail = jwtUtil.parseRegistrationToken(token);
		if (parsedEmail == null) {
            throw new JwtTokenMalformedException("Invalid Token");
        }
		else
		{
			User user = userService.findByEmail(parsedEmail);
			user.setEnabled(true);
			userService.save(user);
		}
        
		
	}
	 

}
