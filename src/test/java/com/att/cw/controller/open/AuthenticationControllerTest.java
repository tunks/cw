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
    String content = "{\"name\": \"ebrima@localhost\",\"password\":\"123\"}";

    String respondContent;
    Long registeredUserId;
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
            registerUrl = "/users/";
            mockMvc = webAppContextSetup(context).build();
            //register new user
            mockMvc.perform(post(registerUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("name", "ebrima@localhost")
                    .param("password", "123"))
                    .andDo((MvcResult result) -> {
                        String json = result.getResponse().getContentAsString();
                        System.out.println("json results "+json);
                       registeredUserId = JsonPath.parse(json).read("$['id']",Long.class);
            });
        } catch (Exception ex) {
            Logger.getLogger(AuthenticationControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @After
    public void tearDown() {
        try {
            System.out.println("Results >>>> " + registeredUserId);               
            mockMvc.perform(delete(registerUrl + "{id}",registeredUserId))
                   .andExpect(status().isOk());
        } catch (Exception ex) {
            Logger.getLogger(AuthenticationControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
