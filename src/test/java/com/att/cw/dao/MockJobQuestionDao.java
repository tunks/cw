/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.Job;
import com.att.cw.model.JobCategory;
import com.att.cw.model.JobLocation;
import com.att.cw.model.JobQuestion;
import com.att.cw.model.JobType;
import com.att.cw.model.JobVacancy;
import com.att.cw.model.QuestionCategory;
import com.att.cw.model.QuestionOption;
import com.att.cw.model.QuestionType;
import com.att.cw.service.JobApplicationServiceTest;
import com.att.cw.service.JobCategoryService;
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
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ebrimatunkara
 */
public class MockJobQuestionDao extends  MockUserDao{

    private final List<JobApplicationServiceTest.MockQuestion> mockQuestions = new ArrayList();

    private final String CATEGORY_PROFILE = "Profile";

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Autowired
    protected QuestionTypeService questionTypeService;

    @Autowired
    protected JobQuestionService jobQuestionService;

    @Autowired
    protected JobQuestionOptionService jobQuestionOptionService;

    @Autowired
    protected QuestionCategoryService questionCategoryService;

    @Autowired
    protected JobTypeService jobtypeService;

    @Autowired
    protected JobCategoryService jobCategoryService;

    @Autowired
    private JobService jobService;

    protected JobType jobType;

    protected List<JobQuestion> questions;

    protected Job job;

    protected List<JobCategory> jobCategories;

    protected void setDummyJob() {
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

    protected void configureQuestions() {
        List<QuestionType> questionTypes = questionTypeService.findAll();
        mockQuestions.add(new JobApplicationServiceTest.MockQuestion("First name", CATEGORY_PROFILE, questionTypes.get(0), Boolean.TRUE));
        mockQuestions.add(new JobApplicationServiceTest.MockQuestion("Middle Name", CATEGORY_PROFILE, questionTypes.get(0), Boolean.FALSE));
        mockQuestions.add(new JobApplicationServiceTest.MockQuestion("Last Name", CATEGORY_PROFILE, questionTypes.get(0), Boolean.TRUE));
        mockQuestions.add(new JobApplicationServiceTest.MockQuestion("Country of Birth", CATEGORY_PROFILE, questionTypes.get(0), Boolean.FALSE));
        mockQuestions.add(new JobApplicationServiceTest.MockQuestion("Years of Experience", CATEGORY_PROFILE, questionTypes.get(0), Boolean.FALSE));
        //questions.add(new MockQuestion("Attach your recent resume?", Category.RESUME, questionTypes.get(0), Boolean.FALSE));
        //questions.add(new MockQuestion("Do you now or will in the future require visa sponsorship?", Category.OTHER, questionTypes.get(0), Boolean.TRUE));
    }

    protected List<JobQuestion> createJobQuestions() {
        //create job question
        List<JobQuestion> items = mockQuestions.stream()
                .map(q -> {
                    return createQuestion(q);
                })
                .collect(Collectors.toList());

        return saveJobQuestions(items);
    }

    protected JobQuestion createQuestion(JobApplicationServiceTest.MockQuestion question) {
        QuestionCategory category = questionCategoryService.findByCategory(question.getCategory());
        JobQuestion component = new JobQuestion(question.getQuestion(), category, question.getOptionType());
        component.setRequired(question.isRequired());
        return component;
    }

    protected List<JobQuestion> saveJobQuestions(List<JobQuestion> items) {
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
                    QuestionOption option = new QuestionOption("Option " + num);
                    jobQuestionOptionService.save(option);
                    q.addOptions(option);
                    return jobQuestionService.save(q);
                }).collect(toList());
    }

    protected Job createJob(Set<JobQuestion> jobQuestions) {
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

}
