package com.att.cw.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.att.cw.security.UserSession;

/*
 * Author : Dileep Mundakkapatta
 */


@Repository
public class SessionRepository
{
	@Autowired
	private RedisTemplate<String, UserSession> redisTemplate;
	
	private static String SESSION_KEY = "Session";
	
	public RedisTemplate<String, UserSession> getRedisTemplate()
	{
	      return redisTemplate;
	}
	
	public void setRedisTemplate(RedisTemplate<String, UserSession> redisTemplate)
	{
	      this.redisTemplate = redisTemplate;
	 }


	public void save(UserSession session) {
		this.redisTemplate.opsForHash().put(SESSION_KEY, session.getEmailID(), session);

		
	}

	public UserSession find(String emailID) {
		 return (UserSession)this.redisTemplate.opsForHash().get(SESSION_KEY, emailID);
	}


	public void delete(String emailID) {
		this.redisTemplate.opsForHash().delete(SESSION_KEY,emailID); 

		
	}

}
