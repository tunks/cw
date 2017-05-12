/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.restricted.job;

import com.att.cw.dao.MockJobQuestionDao;
import com.att.cw.dto.JobApplicationEntryDto;
import com.att.cw.dto.JobApplicationEntryDto.AnswerDto;
import com.att.cw.dto.JobApplicationEntryDto.AnswerEntryDto;
import com.att.cw.dto.JobApplicationEntryDto.QuestionAnswerDto;
import com.att.cw.dto.JobQuestionDto;
import com.att.cw.model.User;
import com.att.cw.security.UserLogin.LoginBuild;
import com.att.cw.service.JobAnswerOptionService;
import com.att.cw.service.JobQuestionAnswerService;
import com.att.cw.support.DataUtils;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 *
 * @author ebrimatunkara
 */
@ActiveProfiles({"dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/springmvc-servlet.xml",
    "file:src/main/webapp/WEB-INF/root-context.xml",
    "file:src/main/webapp/WEB-INF/applicationContext-security.xml"})
@WebAppConfiguration
public class JobApplicationControllerTest extends MockJobQuestionDao {

    private static final Logger logger = LoggerFactory.getLogger(JobApplicationControllerTest.class);

    @Autowired
    private WebApplicationContext context;

    @Mock
    private MockMvc mockMvc;

    @Autowired
    private JobQuestionAnswerService jobQuestionAnswerService;

    @Autowired
    private JobAnswerOptionService jobAnswerOptionService;

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    private String jobApplicationUrl;
    private String authorization;
    private User user;

    public JobApplicationControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(context)
                //.addFilters(new JwtAuthenticationFilter())
                .build();
        jobApplicationUrl = "/restricted/applications";

        createLoginUser();
        setDummyJob();
    }

    private void createLoginUser() {
        //create user
        user = this.createDummyData();
        //login user
        if (user != null) {
            Map<String, Object> login = dummyUserLogin(new LoginBuild()
                    .setName(user.getEmail())
                    .setPassword(user.getPassword())
                    .create());
            if (login != null) {
                StringBuilder builder = new StringBuilder();
                builder.append("Bearer")
                        .append(StringUtils.SPACE)
                        .append(login.get("token"));
                //set authorization token
                authorization = builder.toString();
            }
        }
    }

    private Set<QuestionAnswerDto> createJobApplicationAnswers() {
        //create job question answer       
        return questions.stream()
                //.filter(q->{ return !q.isRequired();})
                .map(q -> {

                    QuestionAnswerDto questionAnswerDto = new QuestionAnswerDto();
                    //map question
                    questionAnswerDto.setQuestion(new JobQuestionDto(q.getId()));
                    //set up answer
                    AnswerDto answerDto = new AnswerDto();
                    //set entry dto
                    AnswerEntryDto entryDto = new AnswerEntryDto();
                    //String set answer value
                    int num = atomicInteger.getAndIncrement();
                    String value = "Job application text entry " + num;
                    entryDto.setValue(value);
                    answerDto.setEntry(entryDto);
                    questionAnswerDto.setAnswer(answerDto);
                    return questionAnswerDto;
                }).collect(Collectors.toSet());
    }

    @After
    public void tearDown() {
        this.removeDummyData(user);
    }

    /**
     * Test of saveApplication method, of class JobApplicationController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSaveApplication() throws Exception {
        System.out.println("saveApplication");
        if (job != null && authorization != null) {
            logger.debug("Posting job application");
            JobApplicationEntryDto dto = setupDummyJobApplication();
            mockMvc.perform(post(jobApplicationUrl)
                    .header("Authorization", authorization)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(DataUtils.objectToJson(dto)))
                    .andExpect(status().isOk())
                    .andDo(print());
        }

    }

    private JobApplicationEntryDto setupDummyJobApplication() {
        JobApplicationEntryDto dto = new JobApplicationEntryDto();
        dto.setJobId(job.getId());
        dto.setUserId(user.getId());
        dto.setQuestionAnswers(createJobApplicationAnswers());
        return dto;
    }

}
