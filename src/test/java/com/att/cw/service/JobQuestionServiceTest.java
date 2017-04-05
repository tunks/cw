/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.model.JobQuestion;
import com.att.cw.model.QuestionOption;
import com.att.cw.model.QuestionType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * JobQuestion Service test unit
 *
 * @author ebrimatunkara
 */
@ActiveProfiles({"test", "dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc-servlet.xml"})
@WebAppConfiguration
public class JobQuestionServiceTest {

    private final String[] questions = {"First name", "Country of Birth", "Years of Experience", "Do you now or will in the future require visa sponsorship?"};

    @Autowired
    private JobQuestionService jobQuestionService;

    @Autowired
    private JobQuestionOptionService jobQuestionOptionService;

    @Autowired
    private QuestionTypeService questionTypeService;

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    List<JobQuestion> components;

    public JobQuestionServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<QuestionType> questionTypes = questionTypeService.findAll();
        createJobQuestions(questionTypes);
    }

    private void createJobQuestions(List<QuestionType> questionTypes) {
        components = new ArrayList();
        //create questions
        Boolean required = false;
        QuestionType qtype;
        for (int i = 0; i < questions.length; i++) {
            if (i % 2 == 0) {
                required = true;
                qtype = questionTypes.get(0);
            } else {
                qtype = questionTypes.get(1);
            }
            components.add(createComponent(questions[i], required, qtype));
        }
    }

    @After
    public void tearDown() {
    }

    private JobQuestion createComponent(String question, Boolean required, QuestionType type) {
        JobQuestion component = new JobQuestion(question, type);
        component.setRequired(required);
        return component;
    }

    /**
     * Test of save method, of class JobQuestionService.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        int expectedCount = questions.length;
        int resultCount = saveJobQuestion();

        assertEquals(expectedCount, resultCount);

    }

    private int saveJobQuestion() {
        Set<JobQuestion> results = components.stream()
                .map(component -> {
                    return jobQuestionService.save(component);
                })
                .filter(x -> {
                    return x != null;
                })
                .collect(Collectors.toSet());
        //save options
        results.parallelStream()
                .filter(q -> {
                    return q.getQuestionType().getName().equals("MULTIPLE CHOICE");
                })
                .forEach(q -> {
                    int num = atomicInteger.getAndIncrement();
                    QuestionOption option = new QuestionOption("Option " + num, q);
                    jobQuestionOptionService.save(option);
                    q.addOptions(option);
                    jobQuestionService.save(q);
                });
        int resultCount = results.stream()
                .collect(Collectors.counting()).intValue();
        return resultCount;
    }

    /**
     * Test of find method, of class JobQuestionService.
     */
//    @Test
//    public void testFind() {
//      Long id = new Long(3);
//      JobQuestion  result = jobQuestionService.find(id);
//      assertNotNull(result);
//    }
    /**
     * Test of findAll method, of class JobQuestionService.
     */
    @Test
    public void testFindAll_0args() {
        System.out.println("findAll");
        List<JobQuestion> result = jobQuestionService.findAll();
        assertTrue(result.size() > 0);

    }

    /**
     * Test of delete method, of class JobQuestionService.
     */
    @Test
    public void testDelete() {
//        System.out.println("delete");
//        Long id = null;
//        JobQuestionService instance = new JobQuestionService();
//        instance.delete(id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
