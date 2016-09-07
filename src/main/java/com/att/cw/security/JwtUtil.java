package com.att.cw.security;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.att.cw.model.Authority;
import com.att.cw.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     * 
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public JwtUserDto parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            JwtUserDto u = new JwtUserDto();
            u.setUsername(body.getSubject());
            //u.setId(Long.parseLong((String) body.get("userId")));
            u.setRole((String) body.get("roles"));

            return u;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     * 
     * @param u the user for which the token will be generated
     * @return the JWT token
     */
    public String generateToken(User u) {
        Claims claims = Jwts.claims().setSubject(u.getEmail());
        claims.put("userId", u.getId());
        
       /* String role = "";
        for(Iterator<Authority> it = u.getAuthorities().iterator(); it.hasNext();)
        {
        	role = role+it.next().getName()+":";
        }
        role = role.substring(0,role.length()-1);
        logger.info("Role is : "+role);
        claims.put("roles", role);
        //claims.put("roles", u.getAuthorities());*/

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    
    public String generateRegistrationToken(User u)
    {
    	logger.info("Encryption Key is : "+secret);
    	 Claims claims = Jwts.claims().setSubject(u.getEmail());
         return Jwts.builder()
                 .setClaims(claims)
                 .signWith(SignatureAlgorithm.HS512, secret)
                 .compact();
    }
    public String parseRegistrationToken(String token)
    {
    	try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

          
         return  body.getSubject();
            
         

        } catch (JwtException | ClassCastException e) {
        	logger.info(e.getMessage());
            return null;
        }
    }
}