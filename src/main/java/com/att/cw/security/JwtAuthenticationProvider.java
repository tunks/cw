package com.att.cw.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.att.cw.exception.JwtInvalidTokenException;
import com.att.cw.exception.JwtTokenMalformedException;
import com.att.cw.service.SessionService;
import com.att.cw.service.UserService;


public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationProvider.class);
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
	private SessionService sessionService;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    	
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();
        logger.info("Token is : "+token);
        logger.info("username is : "+username);
        
        //Session session = sessionService.find(emailID);
        
        JwtUserDto parsedUser = jwtUtil.parseToken(token);
        if (parsedUser == null) {
            throw new JwtTokenMalformedException("Invalid Token");
        }
        
        UserSession session = sessionService.find(parsedUser.getUsername());
        
        if(!session.getToken().equals(token))
        	throw new JwtInvalidTokenException("JWT token is not valid");
       
        
        logger.info("User ID is :"+parsedUser.getUsername());
        logger.info("Role is :"+parsedUser.getRole());
       // List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(parsedUser.getRole());

        //return new AuthenticatedUser(parsedUser.getId(), parsedUser.getUsername(), token, authorityList);
        return new AuthenticatedUser(parsedUser.getId(), parsedUser.getUsername(), token, null);
    }

}
