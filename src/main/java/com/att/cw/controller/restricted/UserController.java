package com.att.cw.controller.restricted;



import com.att.cw.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.att.cw.model.User;
import com.att.cw.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/restricted/users")
public class UserController implements BaseController<User, Long> {
    /**
     * TODO logger using AOP
     **/
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    @Qualifier(value = "userService")
    private UserService userService;
    
    /**
     * Find and return user by user id
     *
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Override
    public User find(@PathVariable Long id) {
        return userService.find(id);
    }
    /**
     * Delete user by id
     *
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Override
    @ResponseBody
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    /**
     * Create new user
     *
     */
    @RequestMapping(method = RequestMethod.POST)
    @Override
    public User create(User object) {
        return userService.save(object);
    }

    /**
     * Update existing user 
     *
     */
    @RequestMapping(method = RequestMethod.PUT)
    @Override
    public User update(User object) {
        return userService.save(object);
    }

    /**
     * Find all users by page
     *
     */
    @RequestMapping(method = RequestMethod.GET)
    Page<User> findAll(Pageable page) {
        return userService.findAll(page);
    }

    /**
     * Not implemented
     *
     */
    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
