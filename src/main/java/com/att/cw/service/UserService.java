package com.att.cw.service;

import com.att.cw.dao.UserRepository;
import java.util.List;
import com.att.cw.model.User;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * User service concrete class - to manager user objects
 */
@Service("userService")
public class UserService implements CrudService<User, Long> {
    @Resource
    private UserRepository userRepository;

    /**
     * Save and persist user object
     * @param object
     * @return 
     */
    @Override
    public User save(User object) {
        return userRepository.save(object);
    }

    /**
     * Find and return user by id
     * @param id
     * @return 
     */
    @Override
    public User find(Long id) {
        return userRepository.findOne(id);
    }

    /**
     * Delete user by id
     * @param id 
     */
    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

     /**
     * Find and return all users by page
     * @param page
     * @return 
     **/
    @Override
    public Page<User> findAll(Pageable page) {
        return userRepository.findAll(page);
    }

     /**
     * Find  and return user by email
     * @param email
     * @return 
     **/
    public User findByEmail(String email) {
        return userRepository.findByEmailId(email);
    }
/**
     * Find by email/name and return user
     * @param emailOrName
     * @return 
     **/
    public User findByEmailOrName(String emailOrName) {
        return userRepository.findByEmailIdOrName(emailOrName,emailOrName);
    }
     /**
     * Find by email/name and return user
     * @param email
     * @param name
     * @return 
     **/
    public User findByEmailOrName(String email, String name) {
        return userRepository.findByEmailIdOrName(email,name);
    }
      /**
     * Find  and return user by email and password
     * @param email
     * @param password
     * @return 
     **/
    public User findByEmailAndPassword(String email,String password) {
        return userRepository.findByEmailIdAndPassword(email, password);
    }
    /**
     * Find  and return all users
     * @return 
     **/
    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
