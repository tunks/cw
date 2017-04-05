package com.att.cw.controller.restricted;

import com.att.cw.model.Job;
import com.att.cw.model.JobVacancy;
import com.att.cw.support.DataTypeHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
 *
 * @author ebrimatunkara
 */
@ActiveProfiles({"test", "dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc-servlet.xml"})
@WebAppConfiguration
public class JobControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Mock
    private MockMvc mockMvc;

    private Job job;

    private String endPointUrl;
    private String content;
    private final String pattern = "yyyy-MM-dd HH:mm:ss";

    public JobControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws JsonProcessingException {
        Calendar cal = Calendar.getInstance();
        Date startDate = cal.getTime();
        cal.add(Calendar.MONTH, 2);
        Date endDate = cal.getTime();
        endPointUrl = "/restricted/jobs";
        mockMvc = webAppContextSetup(context).build();
        JobVacancy vacancy = new JobVacancy();
        vacancy.setOpenDate(startDate);
        vacancy.setCloseDate(endDate);
        byte[] description = DataTypeHelper.stringToByte("Technical Architect description 10001");
        job = new Job("Technical Architect", description);
        job.setVacancy(vacancy);
        content = objectToJson(job);
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
     */
    @Test
    public void testDeleteAll() {
        try {
            mockMvc.perform(delete(endPointUrl))
                    .andExpect(status().isMethodNotAllowed());
        } catch (Exception ex) {
            Logger.getLogger(JobControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     */
    @Test
    public void testCreate() {
        try {
            System.out.println("content ==> " + content);
            mockMvc.perform(post(endPointUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(content))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("title").value(job.getTitle()))
                    .andExpect(jsonPath("description").value(job.getDescription()));
        } catch (Exception ex) {
            Logger.getLogger(JobControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of update method, of class JobController.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");

    }

    private String objectToJson(Job job) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(job);
    }

}
