package com.att.cw.service;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.att.cw.dao.AuthorityRepository;
import com.att.cw.dto.DepartmentRegistrationDto;
import com.att.cw.dto.UserRegistrationDto;
import com.att.cw.exception.JwtTokenMalformedException;
import com.att.cw.exception.UserAlreadyExistingException;
import com.att.cw.model.Authority;
import com.att.cw.model.AuthorityName;
import com.att.cw.model.CandidateProfile;
import com.att.cw.model.DepartmentProfile;
import com.att.cw.model.User;
import com.att.cw.model.UserProfile;
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
            CandidateProfile profile = new CandidateProfile();
            user.setEmail(userRegistrationDto.getEmail());
            user.setPassword(userRegistrationDto.getPassword());
            profile.setFirstName(userRegistrationDto.getName());
            profile.setDateOfBirth(userRegistrationDto.getDateOfBirth());
            profile.setGender(userRegistrationDto.getGender());
            user.setProfile(profile);
            profile.setUser(user);
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

	public User registerDepartment(DepartmentRegistrationDto department) {
		// TODO Auto-generated method stub
		logger.info("Department REgistration service called : " + department.getName());
		if (!userService.existsByEmail(department.getEmail())) {
            User user = new User();
            DepartmentProfile profile = new DepartmentProfile();
            user.setEmail(department.getEmail());
            user.setPassword(department.getPassword());
            profile.setName(department.getName());
            profile.setCountry(department.getCountry());
            profile.setPhoneNumber(department.getPhone());
            user.setProfile(profile);
            profile.setUser(user);
            Authority autho = authoRepo.findByName(AuthorityName.ROLE_DEPTMT);
            logger.info("Authority ID   is :" + autho.getId());
            logger.info("Authority Name is :" + autho.getName());
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

}
