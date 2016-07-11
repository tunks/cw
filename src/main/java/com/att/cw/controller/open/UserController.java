package com.att.cw.controller.open;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.att.cw.model.User;
import com.att.cw.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserService UserService;
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserService ps){
		this.UserService = ps;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<User> listUsers() {
		return this.UserService.listUsers();
	}
	
	//For add and update User both
	@RequestMapping(value= "/add", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void addUser(@RequestBody User user){
			
			logger.info("add user request received : "+user.getName()+":"+user.getEnabled());
			this.UserService.addUser(user);
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final UserLogin login) throws ServletException 
	{
		User user= this.UserService.getUserByEmail(login.name);
		if (user != null)
		{
			logger.info("User logged in successfully : "+user.getName()+":"+user.getEnabled());
		
			/* if (login.name == null || !userDb.containsKey(login.name)) 
        	{
        		ServletException e = new ServletException();
        	
            	throw new ServletException("Invalid login");
        		//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        		//return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        	}*/
		
			return new LoginResponse(Jwts.builder().setSubject(login.name)
            .claim("roles", user.getEmailid()).setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
		}
		else 
		{
			logger.info("login failed....");
			return null;
		}
    }
	
	 @SuppressWarnings("unused")
	    private static class UserLogin {
	        public String name;
	        public String password;
	    }

	    @SuppressWarnings("unused")
	    private static class LoginResponse {
	        public String token;

	        public LoginResponse(final String token) {
	            this.token = token;
	        }
	    }
	
	/*@RequestMapping("/User/remove/{id}")
    public String removeUser(@PathVariable("id") int id){
		
        this.UserService.removeUser(id);
        return "redirect:/Users";
    }
 
    @RequestMapping("/User/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        model.addAttribute("User", this.UserService.getUserById(id));
        model.addAttribute("listUsers", this.UserService.listUsers());
        return "User";
    }*/
	
}
