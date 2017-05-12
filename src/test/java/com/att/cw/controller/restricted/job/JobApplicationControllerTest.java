/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.restricted.job;

import com.att.cw.dao.MockUserDao;
import com.att.cw.dto.JobApplicationDto;
import com.att.cw.dto.JobApplicationEntryDto;
import com.att.cw.dto.JobApplicationEntryDto.AnswerDto;
import com.att.cw.dto.JobApplicationEntryDto.AnswerEntryDto;
import com.att.cw.dto.JobApplicationEntryDto.QuestionAnswerDto;
import com.att.cw.dto.JobQuestionDto;
import com.att.cw.dto.JobQuestionListDto;
import com.att.cw.dto.UserRegistrationDto;
import com.att.cw.model.Gender;
import com.att.cw.model.Job;
import com.att.cw.model.JobAnswerEntry;
import com.att.cw.model.JobApplication;
import com.att.cw.model.JobCategory;
import com.att.cw.model.JobLocation;
import com.att.cw.model.JobQuestion;
import com.att.cw.model.JobQuestionAnswer;
import com.att.cw.model.JobType;
import com.att.cw.model.JobVacancy;
import com.att.cw.model.QuestionCategory;
import com.att.cw.model.QuestionOption;
import com.att.cw.model.QuestionType;
import com.att.cw.model.User;
import com.att.cw.security.JwtAuthenticationFilter;
import com.att.cw.security.UserLogin.LoginBuild;
import com.att.cw.service.JobAnswerOptionService;
import com.att.cw.service.JobApplicationServiceTest;
import com.att.cw.service.JobCategoryService;
import com.att.cw.service.JobQuestionAnswerService;
import com.att.cw.service.JobQuestionOptionService;
import com.att.cw.service.JobQuestionService;
import com.att.cw.service.JobService;
import com.att.cw.service.JobTypeService;
import com.att.cw.service.QuestionCategoryService;
import com.att.cw.service.QuestionTypeService;
import com.att.cw.support.DataUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

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
public class JobApplicationControllerTest extends MockUserDao {
    private static final Logger logger = LoggerFactory.getLogger( JobApplicationControllerTest.class);

    @Autowired
    private WebApplicationContext context;

    @Mock
    private MockMvc mockMvc;

    private final List<JobApplicationServiceTest.MockQuestion> mockQuestions = new ArrayList();

    @Autowired
    private JobService jobService;

    @Autowired
    private JobQuestionService jobQuestionService;

    @Autowired
    private JobQuestionOptionService jobQuestionOptionService;

    @Autowired
    private JobCategoryService jobCategoryService;

    @Autowired
    private QuestionCategoryService questionCategoryService;

    @Autowired
    private QuestionTypeService questionTypeService;

    @Autowired
    private JobTypeService jobtypeService;

    @Autowired
    private JobQuestionAnswerService jobQuestionAnswerService;

    @Autowired
    private JobAnswerOptionService jobAnswerOptionService;

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    private List<JobQuestion> questions;

    private Job job;

    private final String CATEGORY_PROFILE = "Profile";

    private List<JobCategory> jobCategories;

    private JobType jobType;

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
        //create job questions and job
        configureQuestions();
        questions = createJobQuestions();
        //get job categories
        jobCategories = jobCategoryService.findAll();
        //job type
        List<JobType> types = jobtypeService.findAll();
        if (!types.isEmpty()) {
            jobType = types.get(0);
        }
        //create job
        if (questions != null) {
            job = createJob(questions.stream().collect(toSet()));
        }
    }

    private void createLoginUser() {
        //create user
        user = createDummyData();
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

    private List<JobQuestion> createJobQuestions() {
        //create job question
        List<JobQuestion> items = mockQuestions.stream()
                .map(q -> {
                    return createQuestion(q);
                })
                .collect(Collectors.toList());

        return saveJobQuestions(items);
    }

    private JobQuestion createQuestion(JobApplicationServiceTest.MockQuestion question) {
        QuestionCategory category = questionCategoryService.findByCategory(question.getCategory());
        JobQuestion component = new JobQuestion(question.getQuestion(), category, question.getOptionType());
        component.setRequired(question.isRequired());
        return component;
    }

    private List<JobQuestion> saveJobQuestions(List<JobQuestion> items) {
        List<JobQuestion> results = items.stream()
                .map(component -> {
                    return jobQuestionService.save(component);
                })
                .filter(x -> {
                    return x != null;
                })
                .collect(Collectors.toList());

        //create and save question option
        return results.stream()
                .filter(q -> {
                    return !q.getQuestionType().getName().equals("TEXT");
                })
                .map(q -> {
                    int num = atomicInteger.getAndIncrement();
                    QuestionOption option = new QuestionOption("Option " + num, q);
                    jobQuestionOptionService.save(option);
                    q.addOptions(option);
                    return jobQuestionService.save(q);
                }).collect(toList());
    }

    private void configureQuestions() {
        List<QuestionType> questionTypes = questionTypeService.findAll();
        mockQuestions.add(new JobApplicationServiceTest.MockQuestion("First name", CATEGORY_PROFILE, questionTypes.get(0), Boolean.TRUE));
        mockQuestions.add(new JobApplicationServiceTest.MockQuestion("Middle Name", CATEGORY_PROFILE, questionTypes.get(0), Boolean.FALSE));
        mockQuestions.add(new JobApplicationServiceTest.MockQuestion("Last Name", CATEGORY_PROFILE, questionTypes.get(0), Boolean.TRUE));
        mockQuestions.add(new JobApplicationServiceTest.MockQuestion("Country of Birth", CATEGORY_PROFILE, questionTypes.get(0), Boolean.FALSE));
        mockQuestions.add(new JobApplicationServiceTest.MockQuestion("Years of Experience", CATEGORY_PROFILE, questionTypes.get(0), Boolean.FALSE));
        //questions.add(new MockQuestion("Attach your recent resume?", Category.RESUME, questionTypes.get(0), Boolean.FALSE));
        //questions.add(new MockQuestion("Do you now or will in the future require visa sponsorship?", Category.OTHER, questionTypes.get(0), Boolean.TRUE));
    }

    private Job createJob(Set<JobQuestion> jobQuestions) {
        Calendar cal = Calendar.getInstance();
        Date startDate = cal.getTime();
        cal.add(Calendar.MONTH, 2);
        Date closeDate = cal.getTime();
        //job vacancy
        JobVacancy vacancy = new JobVacancy();
        vacancy.setOpenDate(startDate);
        vacancy.setCloseDate(closeDate);
        //location
        JobLocation location = new JobLocation();
        location.setCity("New York City");
        location.setCountry("USA");
        location.setLocationState("NY");
        byte[] description = DataUtils.stringToByte("Experience in java technologies");
        Job jobObj = new Job("Technical Architect", description);
        jobObj.setVacancy(vacancy);
        jobObj.setLocation(location);
        //set job categories
        jobObj.getCategories().addAll(jobCategories.subList(0, 10).stream().collect(toSet()));
        //set job type
        if (jobType != null) {
            jobObj.setJobType(jobType);
        }
        //set job mockQuestions
        jobObj.getQuestions().addAll(jobQuestions);
        //save job
        return jobService.save(jobObj);
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
            JobApplicationEntryDto dto = new JobApplicationEntryDto();
            dto.setJobId(job.getId());
            dto.setUserId(user.getId());
            dto.setQuestionAnswers(createJobApplicationAnswers());
            mockMvc.perform(post(jobApplicationUrl)
                    .header("Authorization", authorization)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(DataUtils.objectToJson(dto)))
                    .andExpect(status().isOk())
                    .andDo(print());
        }

    }

}
