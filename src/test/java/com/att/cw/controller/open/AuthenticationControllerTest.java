/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.open;

import com.att.cw.model.User;
import com.jayway.jsonpath.JsonPath;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

/**
 *
 * @author ebrimatunkara
 */
@ActiveProfiles({"test", "dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc-servlet.xml"})
@WebAppConfiguration
public class AuthenticationControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Mock
    private MockMvc mockMvc;
    private String loginUrl;
    private String registerUrl;
    private String email;
    private String password;
    private String content;
    public AuthenticationControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            loginUrl = "/authenticate/login";
            registerUrl = "/register/new";
            mockMvc = webAppContextSetup(context).build();
            
            //mock user email and password
            email = RandomStringUtils.randomAlphanumeric(6)
                                       .concat("@localhost");
            password = RandomStringUtils.randomAlphanumeric(12);
            //register new user
             content = "{\"email\": \""+email+"\",\"password\":\""+password+"\"}";

            mockMvc.perform(post(registerUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                   .content(content));
        } catch (Exception ex) {
            Logger.getLogger(AuthenticationControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @After
    public void tearDown() {
//        try {
//            System.out.println("Results >>>> " + registeredUserId);               
//            mockMvc.perform(delete(registerUrl + "{id}",registeredUserId))
//                   .andExpect(status().isOk());
//        } catch (Exception ex) {
//            Logger.getLogger(AuthenticationControllerTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    /**
     * Test of login method, of class AuthenticationController.
     */
    @Test
    public void testLogin() {
        try {
            System.out.println("login");
            mockMvc.perform(post(loginUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content))
                    .andExpect(status().isAccepted());
        } catch (Exception ex) {
            Logger.getLogger(AuthenticationControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
