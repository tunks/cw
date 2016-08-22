package com.att.cw.controller.restricted;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.att.cw.controller.open.AuthenticationController;
import com.att.cw.security.AuthenticatedUser;

@RestController
@RequestMapping("/restricted/echo")
public class Echo 
{
	private static final Logger logger = LoggerFactory.getLogger(Echo.class);
	 @RequestMapping(method = RequestMethod.POST)
	 public String echo(@RequestBody final String data)
	 {
		 AuthenticatedUser userDetails = (AuthenticatedUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 logger.info("User Name is : "+userDetails.getUsername());
		 logger.info("Token is : "+userDetails.getToken());
	 	return data;
	 }
	 
	 @RequestMapping(method = RequestMethod.GET)
	 public String echo()
	 {
	 	return "Hello World....";
	 }

}
