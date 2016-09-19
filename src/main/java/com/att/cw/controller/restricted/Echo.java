package com.att.cw.controller.restricted;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.att.cw.controller.open.AuthenticationController;
import com.att.cw.model.Gender;
import com.att.cw.security.AuthenticatedUser;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@RequestMapping("/restricted/echo")
public class Echo 
{
	private static final Logger logger = LoggerFactory.getLogger(Echo.class);
	 @RequestMapping(method = RequestMethod.POST)
	 public ResponseEntity<String> echo(@RequestBody final String data)
	 {
		 AuthenticatedUser userDetails = (AuthenticatedUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 logger.info("User Name is : "+userDetails.getUsername());
		 logger.info("Token is : "+userDetails.getToken());
	 	return new ResponseEntity<String>(data, HttpStatus.OK) ;
	 }
	 
	 @RequestMapping(method = RequestMethod.GET)
	 public ResponseEntity<TestObject> echo()
	 {
		 return new ResponseEntity<TestObject>(new TestObject(), HttpStatus.OK) ;
	 }
	 
	 private class TestObject
	 {
		 Gender gender;
		 
		 @JsonFormat(pattern="yyyy-MM-dd")
		 Date dateOfBirth;
		 
		 TestObject()
		 {
			 Calendar cal = Calendar.getInstance();
			    cal.set(1985, 1, 8);
			    this.setDateOfBirth(cal.getTime());
			    this.gender=Gender.MALE;
		 }
		public Gender getGender() {
			return gender;
		}
		public void setGender(Gender gender) {
			this.gender = gender;
		}
		public Date getDateOfBirth() {
			return dateOfBirth;
		}
		public void setDateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
	 }

}
