/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.FileDocumentRepositoryTest;
import com.att.cw.model.FileDocument;
import com.att.cw.model.Job;
import com.att.cw.model.JobAnswerEntry;
import com.att.cw.model.JobApplication;
import com.att.cw.model.JobCandidate;
import com.att.cw.model.JobCategory;
import com.att.cw.model.JobLocation;
import com.att.cw.model.JobQuestion;
import com.att.cw.model.JobQuestionAnswer;
import com.att.cw.model.JobType;
import com.att.cw.model.JobVacancy;
import com.att.cw.model.QuestionCategory;
import com.att.cw.model.QuestionOption;
import com.att.cw.model.QuestionType;
import com.att.cw.model.Resume;
import com.att.cw.support.DataUtils;
import com.att.cw.support.ResourceType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Job application service test class
 *
 * @author ebrimatunkara
 */
@ActiveProfiles({"dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:springmvc-servlet.xml"})
@WebAppConfiguration
public class JobApplicationServiceTest {

    private final List<MockQuestion> mockQuestions = new ArrayList();

    private AnswerOptionTestFactory answerOptionFactory;

    @Autowired
    private JobService jobService;

    @Autowired
    private JobApplicationService jobApplicationService;

//    @Autowired
//    private ResumeService resumeService;
    @Autowired
    private FileDocumentService documentService;

    @Autowired
    private JobCandidateService jobCandidateService;

    @Autowired
    private JobQuestionService jobQuestionService;

    @Autowired
    private JobQuestionOptionService jobQuestionOptionService;

    @Autowired
    private JobQuestionAnswerService jobQuestionAnswerService;

    @Autowired
    private JobAnswerOptionService jobAnswerOptionService;

    @Autowired
    private JobCategoryService jobCategoryService;

    @Autowired
    private QuestionCategoryService questionCategoryService;

    @Autowired
    private QuestionTypeService questionTypeService;

    @Autowired
    private JobTypeService jobtypeService;

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    private List<JobQuestion> questions;

    private Job job;
    private JobApplication application;
    private JobCandidate candidate;
    private final String CATEGORY_PROFILE = "Profile";
    private List<JobCategory> jobCategories;
    private Set<JobQuestionAnswer> answers;
    private JobType jobType;

    public JobApplicationServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
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
        //create job candidate
        candidate = createJobCandidate();
        //intial job application
        initJobApplication();
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

    private JobQuestion createQuestion(MockQuestion question) {
        QuestionCategory category = questionCategoryService.findByCategory(question.category);
        JobQuestion component = new JobQuestion(question.getQuestion(), category, question.optionType);
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
        mockQuestions.add(new MockQuestion("First name", CATEGORY_PROFILE, questionTypes.get(0), Boolean.TRUE));
        mockQuestions.add(new MockQuestion("Middle Name", CATEGORY_PROFILE, questionTypes.get(0), Boolean.FALSE));
        mockQuestions.add(new MockQuestion("Last Name", CATEGORY_PROFILE, questionTypes.get(0), Boolean.TRUE));
        mockQuestions.add(new MockQuestion("Country of Birth", CATEGORY_PROFILE, questionTypes.get(0), Boolean.FALSE));
        mockQuestions.add(new MockQuestion("Years of Experience", CATEGORY_PROFILE, questionTypes.get(0), Boolean.FALSE));
        //questions.add(new MockQuestion("Attach your recent resume?", Category.RESUME, questionTypes.get(0), Boolean.FALSE));
        //questions.add(new MockQuestion("Do you now or will in the future require visa sponsorship?", Category.OTHER, questionTypes.get(0), Boolean.TRUE));
    }

    /**
     * Initialize job application
     */
    public void initJobApplication() {
        application = createJobApplication(job, candidate);
        //jobanswer factory
        answerOptionFactory = new AnswerOptionTestFactory();
        //create and save job question answers
        answers = saveJobAnswer();

        //set job application answers
        application.setQuestionAnswers(answers);
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

    private Set<JobQuestionAnswer> saveJobAnswer() {
        //save job question answer
        ///AnswerOptionTask answerOptionTask = answerOptionFactory.createOptionTask();
        return questions.stream()
                //.filter(q->{ return !q.isRequired();})
                .map(q -> {
                    //get question type 
                    QuestionType opType = q.getQuestionType();
                    //get question options
                    Set<QuestionOption> ops = q.getOptions();
                    //set question answer
                    JobQuestionAnswer ans = new JobQuestionAnswer(q);

                    //TODO -- refactor to COR
                    JobAnswerEntry entryObj;
//                    if (!opType.getName().equals("TEXT")) {
//                        answerOps = ops.stream()
//                                .map(op -> {
//                                    JobAnswerOption ansOp = new JobAnswerOption(op);
//                                    answerOptionTask.process(ansOp, opType);
//                                    return jobAnswerOptionService.save(ansOp);
//                                })
//                                .collect(Collectors.toSet());
//                    } else {
                    int num = atomicInteger.getAndIncrement();
                    JobAnswerEntry entry = new JobAnswerEntry("Text Value " + num);
                    entryObj = jobAnswerOptionService.save(entry);

                    // }
                    //set question answer options
                    ans.setAnswerEntry(entryObj);
                    ans.setQuestion(q);
                    //save and return question answer
                    return jobQuestionAnswerService.save(ans);
                }).collect(Collectors.toSet());
    }

    public FileDocument createFileDocument() throws IOException {
        FileDocument document = FileDocumentRepositoryTest.createMockDocument();
        document.setResourceType(ResourceType.JOB_APPLICATION);
        //save document
        documentService.save(document);
        return document;
    }

    private JobApplication createJobApplication(Job job, JobCandidate candidate) {
        //job application
        JobApplication appl = new JobApplication();
        appl.setJob(job);
        appl.setCandidate(candidate);
        return appl;
    }

    private JobCandidate createJobCandidate() {
        JobCandidate object = new JobCandidate();
        return jobCandidateService.save(object);
    }

    @After
    public void tearDown() {
        //delete job application
        if (application != null) {
            jobApplicationService.delete(application);
        }

        //delete job question answers
        if (answers != null) {
            answers.stream().forEach(x -> {
                x.setQuestion(null);

                jobQuestionAnswerService.delete(x);
            });
        }

        //delete job questions
        if (questions != null) {
            job.getQuestions().stream().forEach(q -> {
                //jobQuestionService.delete(q);
            });
        }

        //delete job
        if (job != null && job.getId() != null) {

            jobService.delete(job);
        }

    }

    /**
     * Test of save method, of class JobApplicationService.
     */
    @Test
    public void testSave() {
        if (application != null) {
            application.setSubmitted(Boolean.TRUE);
            JobApplication result = jobApplicationService.save(application);
            System.out.println("Application saved " + result);
            assertNotNull(result);
        }
    }
//
//    /**
//     * Test of find method, of class JobApplicationService.
//     */
//    @Test
//    public void testFind() {
//        System.out.println("find");
//    }
//
//    /**
//     * Test of findAll method, of class JobApplicationService.
//     */
//    @Test
//    public void testFindAll_0args() {
//        System.out.println("findAll");
//        //create and save job application
//        //JobApplication appl = createJobApplication(job,candidate);
//       // jobApplicationService.save(appl);
//        //find applications by job
//        List<JobApplication> applications = jobApplicationService.findByJob(job);
//        assertTrue(applications.size() > 0);
//    }
//
//    /**
//     * Test of findAll method, of class JobApplicationService.
//     */
//    @Test
//    public void testFindAll_Pageable() {
//        System.out.println("findAll");
//    }
//
//    /**
//     * Test of delete method, of class JobApplicationService.
//     */
//    @Test
//    public void testDelete() {
//        System.out.println("delete");
//    }
//
//    /**
//     * Test of findByJob method, of class JobApplicationService.
//     */
//    @Test
//    public void testFindByJob() {
//        System.out.println("findByJob");
//    }
//
//    /**
//     * Test of findByIdAndJob method, of class JobApplicationService.
//     */
//    @Test
//    public void testFindByIdAndJob() {
//        System.out.println("findByIdAndJob");
//    }

    public static class MockQuestion {

        private String question;
        private QuestionType optionType;
        private boolean required;
        private String category;

        public MockQuestion(String question, String category, QuestionType optionType, boolean required) {
            this.question = question;
            this.category = category;
            this.optionType = optionType;
            this.required = required;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public QuestionType getOptionType() {
            return optionType;
        }

        public void setOptionType(QuestionType optionType) {
            this.optionType = optionType;
        }

        public boolean isRequired() {
            return required;
        }

        public void setRequired(boolean required) {
            this.required = required;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

    }

    private abstract class AnswerOptionTask {

        private AnswerOptionTask next;

        public AnswerOptionTask() {
        }

        public AnswerOptionTask(AnswerOptionTask next) {
            this.next = next;
        }

        public AnswerOptionTask getNext() {
            return next;
        }

        public void setNext(AnswerOptionTask next) {
            this.next = next;
        }

        public abstract void process(JobAnswerEntry ansOp, QuestionType opType);
    }

    private class NoteAnswerOptionTask extends AnswerOptionTask {

        private final AtomicInteger counter = new AtomicInteger(0);

        public NoteAnswerOptionTask() {
        }

        public NoteAnswerOptionTask(AnswerOptionTask next) {
            super(next);
        }

        @Override
        public void process(JobAnswerEntry entry, QuestionType opType) {
            if (opType.getName().equals("TEXT")) {
                entry.setValue("Answer note text >> " + counter.getAndIncrement());
            } else if (this.getNext() != null) {
                this.getNext().process(entry, opType);
            }
        }
    }

    private class FileAnswerOptionTask extends AnswerOptionTask {

        public FileAnswerOptionTask() {
        }

        public FileAnswerOptionTask(AnswerOptionTask next) {
            super(next);
        }

        @Override
        public void process(JobAnswerEntry ansOp, QuestionType opType) {
            if (opType.getName().equals("FILE ATTACHMENT")) {
                try {
                    FileDocument document = createFileDocument();
                    ansOp.setDocument(document);
                } catch (IOException ex) {
                    Logger.getLogger(JobApplicationServiceTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (this.getNext() != null) {
                this.getNext().process(ansOp, opType);
            }
        }
    }

    private class MutliChoiceAnswerOptionTask extends AnswerOptionTask {

        public MutliChoiceAnswerOptionTask() {
        }

        public MutliChoiceAnswerOptionTask(AnswerOptionTask next) {
            super(next);
        }

        @Override
        public void process(JobAnswerEntry entry, QuestionType opType) {
            if (opType.getName().equals("MULTIPLE CHOICE")) {
                //set checked true
                entry.setChecked(Boolean.TRUE);
            } else if (this.getNext() != null) {
                this.getNext().process(entry, opType);
            }
        }
    }

    private class SingleChoiceAnswerOptionTask extends AnswerOptionTask {

        public SingleChoiceAnswerOptionTask() {
        }

        public SingleChoiceAnswerOptionTask(AnswerOptionTask next) {
            super(next);
        }

        @Override
        public void process(JobAnswerEntry entry, QuestionType opType) {
            if (opType.getName().equals("SINGLE CHOICE")) {
                //set checked true
                entry.setChecked(Boolean.TRUE);
            } else if (this.getNext() != null) {
                this.getNext().process(entry, opType);
            }
        }
    }

    private class AnswerOptionTestFactory {

        public AnswerOptionTask createOptionTask() {
            AnswerOptionTask task1 = new NoteAnswerOptionTask();
            AnswerOptionTask task2 = new FileAnswerOptionTask();
            AnswerOptionTask task3 = new MutliChoiceAnswerOptionTask();
            AnswerOptionTask task4 = new SingleChoiceAnswerOptionTask();
            //set the chain
            task1.setNext(task2);
            task2.setNext(task3);
            task3.setNext(task4);
            return task1;
        }
    }

}
