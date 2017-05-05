/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.restricted;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.ArrayUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author ebrimatunkara
 */
@ActiveProfiles({"dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:root-context.xml"})
@WebAppConfiguration
public class SearchContentControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Mock
    private MockMvc mockMvc;
    private String endPointUrl;
    private String authorization;
    private UriComponents uriComponent;

    public SearchContentControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        endPointUrl = "/cw/restricted/contents/search";
        authorization = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlYnJpbWFAbG9jYWxob3N0LmxvY2FsIiwidXNlcklkIjoxfQ.q2pQDzf9wp2-BsKSVuJAhIesJ4wxujVHXzp00fdOU7dYY1DH9oygVmAhhMLbOt0pso_QGraKZLcYSike07ZJpg";
        mockMvc = webAppContextSetup(context)
                .build();

        MultiValueMap<String, String> params = new LinkedMultiValueMap();
        // params.add("query", "{\"name\":\"TICKET_TYPE\",\"type\":\"bucket\"}");

        uriComponent = UriComponentsBuilder
                .newInstance()
                .path(endPointUrl)
                .queryParams(params)
                .build()
                .encode();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of search method, of class SearchContentController.
     */
    @Test
    public void testSearch_MultiValueMap_Pageable() {
        System.out.println("search");
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap();
            mockMvc.perform(get(uriComponent.toUri())
                    //.params(params)
                    .accept(MediaType.APPLICATION_JSON)
                    .header("Authorization", authorization))
                    //.andExpect(status().isOk())
                    .andDo(print());
            //.andReturn().getResponse().getContentAsString();
            // System.out.println("response ==> " + response);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(JobControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of search method, of class SearchContentController.
     */
    @Test
    public void testSearch_MultiValueMap() {
        System.out.println("search");
//        MultiValueMap<String, String> params = null;
//        SearchContentController instance = new SearchContentController();
//        Page<Map> expResult = null;
//        Page<Map> result = instance.search(params);
//        assertEquals(expResult, result);
    }

}
