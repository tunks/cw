/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.FileDocumentRepositoryTest;
import com.att.cw.model.Category;
import com.att.cw.model.FileDocument;
import com.att.cw.model.Job;
import com.att.cw.model.JobAnswerOption;
import com.att.cw.model.JobApplication;
import com.att.cw.model.JobCandidate;
import com.att.cw.model.JobQuestion;
import com.att.cw.model.JobQuestionAnswer;
import com.att.cw.model.JobVacancy;
import com.att.cw.model.QuestionCategory;
import com.att.cw.model.QuestionOption;
import com.att.cw.model.QuestionOptionType;
import com.att.cw.model.Resume;
import com.att.cw.support.ResourceType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
@ActiveProfiles({"test", "dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc-servlet.xml"})
@WebAppConfiguration
public class JobApplicationServiceTest {
    private final List<MockQuestion> questions = new ArrayList();

    private AnswerOptionTestFactory answerOptionFactory;
    @Autowired
    private JobService jobService;

    @Autowired
    private JobApplicationService jobApplicationService;

    @Autowired
    private ResumeService resumeService;

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
    private QuestionCategoryService questionCategoryService;

    private List<QuestionCategory> categories;

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    private List<JobQuestion> components;

    private Job job;
    private JobApplication application;
    private Resume resume;
    private JobCandidate candidate;

    private Set<JobQuestion> jobQuestions;
        
    public JobApplicationServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    private void createJobQuestions() {
       //create job question
        components =  questions.stream()
                                .map(q->{
                                  return createComponent(q);
                                })
                         .collect(Collectors.toList());
    }

    private JobQuestion createComponent(MockQuestion question) {
        QuestionCategory category = questionCategoryService.findByCategory(question.category.name());
        JobQuestion component = new JobQuestion(question.getQuestion(), category, question.optionType);
        component.setRequired(question.isRequired());
        return component;
    }

    private Set<JobQuestion> saveJobQuestions() {
        Set<JobQuestion> results = components.stream()
                .map(component -> {
                    return jobQuestionService.save(component);
                })
                .filter(x -> {
                    return x != null;
                })
                .collect(Collectors.toSet());

        //create and save question option
        results.stream()
                .filter(q -> {
                    return !q.getQuestionType().equals(QuestionOptionType.NOTE);
                })
                .forEach(q -> {
                    int num = atomicInteger.getAndIncrement();
                    QuestionOption option = new QuestionOption("Option " + num, q);
                    jobQuestionOptionService.save(option);
                    q.addOptions(option);
                    jobQuestionService.save(q);
                });
        return results;
    }

    @Before
    public void setUp() throws IOException {   
        questions.add(new MockQuestion("First name",Category.PROFILE,Boolean.TRUE));
        questions.add(new MockQuestion("Middle Name",Category.PROFILE));
        questions.add(new MockQuestion("Last Name",Category.PROFILE,Boolean.TRUE));
        questions.add(new MockQuestion("Country of Birth",Category.PROFILE));
        questions.add(new MockQuestion("Years of Experience",Category.PROFESSION_HISTORY));
        questions.add(new MockQuestion("Attach your recent resume?",Category.RESUME,QuestionOptionType.FILE_ATTACHMENT));
        questions.add(new MockQuestion("Do you now or will in the future require visa sponsorship?",Category.QUESTION,QuestionOptionType.MULTI_CHOICE));
  
        //create job categories
        createJobCategories();
        //intial job application
        initJobApplication();
    }
     //create job categories
    private void createJobCategories() {
        //create job categories
         categories = new ArrayList();
         for(Category c :Category.values()){
            categories.add( new QuestionCategory(c.name()));
          }
         
        categories.stream()
                .forEach(c -> {
                    if(questionCategoryService.findByCategory(c.getCategory()) == null){
                       questionCategoryService.save(c);
                    }
                });
    }

    public void initJobApplication() {
        Calendar cal = Calendar.getInstance();
        Date startDate = cal.getTime();
        cal.add(Calendar.MONTH, 2);
        Date closeDate = cal.getTime();
        JobVacancy vacancy = new JobVacancy();
        vacancy.setOpenDate(startDate);
        vacancy.setCloseDate(closeDate);

        job = new Job("Technical Architect", "Experience in java technologies");
        job.setVacancy(vacancy);
        /*FileDocument document = createFileDocument();
        //job resume
        resume = new Resume();
        resume.setDocument(document);
        resumeService.save(resume);
        */
        //create job questions
        createJobQuestions();
        //save and return job questions
        jobQuestions = saveJobQuestions();
        //set job questions
        job.setQuestions(jobQuestions);
        //save job
        jobService.save(job);
        //job application
        //create and save job candidate
        candidate = jobCandidateService.save(createJobCandidate());
        application = createJobApplication(job,candidate);
        //jobanswer factory
        answerOptionFactory = new AnswerOptionTestFactory();
        //create and save job question answers
        Set<JobQuestionAnswer> answers = saveJobAnswer();
        
        //set job application answers
        application.setQuestionAnswers(answers);
    }

    private Set<JobQuestionAnswer> saveJobAnswer() {
        //save job question answer
        AnswerOptionTask answerOptionTask = answerOptionFactory.createOptionTask();
        Set<JobQuestionAnswer> answers = jobQuestions.stream()
                                        //.filter(q->{ return !q.isRequired();})
                                        .map(q -> {
                                            //get question type 
                                            QuestionOptionType opType = q.getQuestionType();
                                            //get question options
                                            Set<QuestionOption> ops = q.getOptions();
                                            //set question answer
                                            JobQuestionAnswer ans = new JobQuestionAnswer(q);

                                            //TODO -- refactor to COR
                                            Set<JobAnswerOption> answerOps = new HashSet();
                                                 if(!opType.equals(QuestionOptionType.NOTE)){
                                                     answerOps = ops.stream()
                                                                    .map(op -> {
                                                                        JobAnswerOption ansOp = new JobAnswerOption(op);
                                                                        answerOptionTask.process(ansOp, opType);
                                                                        return jobAnswerOptionService.save(ansOp);
                                                                    })
                                                                   .collect(Collectors.toSet());
                                                 }
                                                 else{
                                                    int num = atomicInteger.getAndIncrement();
                                                    JobAnswerOption ansOp = new JobAnswerOption("Note Text "+num);
                                                    jobAnswerOptionService.save(ansOp);
                                                    answerOps.add(ansOp);
                                                 }
                                            //set question answer options
                                            ans.setAnswerOptions(answerOps);
                                            //save and return question answer
                                            return jobQuestionAnswerService.save(ans);
                }).collect(Collectors.toSet());
        return answers;
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
        return new JobCandidate();
    }

    @After
    public void tearDown() {
        //questionCategoryService.deleteAll();
    }
    
    private void clearQuestionCategories(){
      questionCategoryService.deleteAll();
    }

    /**
     * Test of save method, of class JobApplicationService.
     */
    @Test
    public void testSave() {
        application.setSubmitted(Boolean.TRUE);
        JobApplication result = jobApplicationService.save(application);
        assertNotNull(result);
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

    private class MockQuestion{
       private String question;
       private QuestionOptionType optionType;
       private boolean required;
       private Category category;
       
       public MockQuestion(String question, Category category) {
            this.question = question;
            this.category = category;
            this.optionType = QuestionOptionType.NOTE;
            this.required = Boolean.FALSE;
            
        }
       
       
       
       public MockQuestion(String question,Category category, QuestionOptionType optionType) {
            this(question,category);
            this.optionType = optionType;
        }
       
       public MockQuestion(String question, Category category, boolean required) {
         this(question,category);
         this.required = required;
       }
              
       public MockQuestion(String question,  Category category, QuestionOptionType optionType, boolean required) {
            this(question,category,optionType);
            this.required = required;
        }

       
        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public QuestionOptionType getOptionType() {
            return optionType;
        }

        public void setOptionType(QuestionOptionType optionType) {
            this.optionType = optionType;
        }

        public boolean isRequired() {
            return required;
        }

        public void setRequired(boolean required) {
            this.required = required;
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

        public abstract void process(JobAnswerOption ansOp, QuestionOptionType opType);
    }

    private class NoteAnswerOptionTask extends AnswerOptionTask {
        private final AtomicInteger counter = new AtomicInteger(0);

        public NoteAnswerOptionTask() {
        }

        public NoteAnswerOptionTask(AnswerOptionTask next) {
            super(next);
        }

        @Override
        public void process(JobAnswerOption ansOp, QuestionOptionType opType) {
            if (opType.equals(QuestionOptionType.NOTE)) {
                ansOp.setNote("Answer note text >> "+counter.getAndIncrement());
            } else if (this.getNext() != null) {
                this.getNext().process(ansOp, opType);
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
        public void process(JobAnswerOption ansOp, QuestionOptionType opType) {
             if (opType.equals(QuestionOptionType.FILE_ATTACHMENT)) {
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
        public void process(JobAnswerOption ansOp, QuestionOptionType opType) {
            if (opType.equals(QuestionOptionType.MULTI_CHOICE)) {
                 //set checked true
                ansOp.setChecked(Boolean.TRUE);
            } else if (this.getNext() != null) {
                this.getNext().process(ansOp, opType);
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
        public void process(JobAnswerOption ansOp, QuestionOptionType opType) {
            if (opType.equals(QuestionOptionType.SINGLE_CHOICE)) {
                 //set checked true
                ansOp.setChecked(Boolean.TRUE);
            } else if (this.getNext() != null) {
                this.getNext().process(ansOp, opType);
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
