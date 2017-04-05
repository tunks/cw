package com.att.cw.service;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.att.cw.dao.AuthorityRepository;
import com.att.cw.dto.UserRegistrationDto;
import com.att.cw.exception.JwtTokenMalformedException;
import com.att.cw.exception.UserAlreadyExistingException;
import com.att.cw.model.Authority;
import com.att.cw.model.AuthorityName;
import com.att.cw.model.User;
import com.att.cw.security.JwtUtil;

@Service
public class RegistrationService {

    @Resource
    private UserService userService;
    @Resource
    private AuthorityRepository authoRepo;

    @Resource
    private JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    public User registerUser(UserRegistrationDto userRegistrationDto) {
        if (!userService.existsByEmail(userRegistrationDto.getEmail())) {
            User user = new User();
            user.setEmail(userRegistrationDto.getEmail());
            user.setPassword(userRegistrationDto.getPassword());
            user.setName(userRegistrationDto.getName());
            user.setDateOfBirth(userRegistrationDto.getDateOfBirth());
            user.setGender(userRegistrationDto.getGender());
            Authority autho = authoRepo.findByName(AuthorityName.ROLE_USER);
            logger.info("Authority ID is :" + autho.getId());
            userService.save(user);
            logger.info("new User ID saved with ID  :" + user.getId());
            user.getAuthorities().add(autho);
            userService.save(user);
            return user;
        } else {
            UserAlreadyExistingException ex = new UserAlreadyExistingException("Email ID Already Registered");
            ex.setErrorMessage("This email ID is alredy used for registration. Please reset your password if you do not remember your password");
            throw ex;
        }
    }

    public void activateUser(String token) {
        String parsedEmail = jwtUtil.parseRegistrationToken(token);
        if (parsedEmail == null) {
            throw new JwtTokenMalformedException("Malformed Token", "The activation Key you are trying to use is invalid");
        } else {
            User user = userService.findByEmail(parsedEmail);
            user.setEnabled(true);
            userService.save(user);
        }

    }

}
