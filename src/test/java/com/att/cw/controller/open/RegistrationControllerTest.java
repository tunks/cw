/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.open;

import com.att.cw.model.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author ebrimatunkara
 */
@ActiveProfiles({"test", "dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc-servlet.xml"})
@WebAppConfiguration
public class RegistrationControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Mock
    private MockMvc mockMvc;

    private String registerUrl;
    private String userJson;
    public RegistrationControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        registerUrl = "/register/new";
        mockMvc = webAppContextSetup(context).build();
        //Generate random email address
        String name = RandomStringUtils.randomAlphanumeric(6);
        String email = name.concat("@att.com");
        userJson= "{\"name\": \""+name+"\",\"emailId\": \""+email+"\",\"password\":\"123\"}";
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of registerUser method, of class RegistrationController.
     */
    @Test
    public void testRegisterUser() {
        try {
            System.out.println("registerUser");
            mockMvc.perform(post(registerUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(userJson))
                    .andExpect(status().isCreated());
        } catch (Exception ex) {
            Logger.getLogger(RegistrationControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
