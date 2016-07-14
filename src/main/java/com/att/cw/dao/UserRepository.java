package com.att.cw.dao;

import com.att.cw.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * User data access interface repository
 * @author ebrimatunkara
 * See http://docs.spring.io/spring-data/jpa/docs/1.9.4.RELEASE/reference/html/
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long>{
       /* find and return user by email
        * @param emailId
        * @return user 
        */
       public User findByEmailId(String emailId);
       /* find and return user by email and password
        * @param emailId
        * @param password
        * @return user 
        */
       public User findByEmailAndPassword(String emailId,String password);
}
