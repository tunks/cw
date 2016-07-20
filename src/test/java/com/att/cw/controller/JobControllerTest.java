
package com.att.cw.controller;

import com.att.cw.model.Job;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;

/**
 * JobControllerTest
 * @author ebrimatunkara
 */
@ActiveProfiles({"test","dev"})
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath:spring-jpa-config.xml",  
     "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration 
public class JobControllerTest {
    @Autowired
    private WebApplicationContext context;
   
    @Mock
    private MockMvc mockMvc;
    
    private  Job job;
    
    private String endPointUrl;
    
    public JobControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        endPointUrl = "/jobs";
        mockMvc = webAppContextSetup(context).build();
        job = new Job("Job3", "Job2 description");
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of findAllBy method, of class JobController.
     */
    @Test
    public void testFindAllBy() {
        System.out.println("findAllBy");
        Long ownerId = null;
        Pageable page = null;

    }

    /**
     * Test of find method, of class JobController.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        Long id = null;

    }

    /**
     * Test of deleteAll method, of class JobController.
     * @throws java.lang.Exception
     */
    @Test
    public void testDeleteAll() throws Exception {
      mockMvc.perform(delete(endPointUrl))
              .andExpect(status().isMethodNotAllowed())
              .andExpect(jsonPath("").isEmpty());
    }

    /**
     * Test of delete method, of class JobController.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
    }

    /**
     * Test of create method, of class JobController.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreate() throws Exception {
           mockMvc.perform(post(endPointUrl)
                           .contentType(MediaType.APPLICATION_JSON)
                           .param("title", job.getTitle())
                           .param("description",job.getDescription()))
           .andExpect(status().isOk())
           .andExpect(jsonPath("title").value(job.getTitle()))
           .andExpect(jsonPath("description").value(job.getDescription()));
    }

    /**
     * Test of update method, of class JobController.
     */
    @Test
    public void testUpdate() {
        System.out.println("update"); 
        
    }
    
}
