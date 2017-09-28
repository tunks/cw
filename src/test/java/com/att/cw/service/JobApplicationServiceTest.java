/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.FileDocumentRepositoryTest;
import com.att.cw.dao.MockJobApplicationDao;
import com.att.cw.model.FileDocument;
import com.att.cw.model.JobAnswerEntry;
import com.att.cw.model.JobApplication;
import com.att.cw.model.QuestionType;
import com.att.cw.support.ResourceType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 * Job application service test class
 *
 * @author ebrimatunkara
 */
@ActiveProfiles({"dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/springmvc-servlet.xml",
    "file:src/main/webapp/WEB-INF/root-context.xml",
    "file:src/main/webapp/WEB-INF/applicationContext-security.xml"})
public class JobApplicationServiceTest extends MockJobApplicationDao{

    private final List<MockQuestion> mockQuestions = new ArrayList();

    private AnswerOptionTestFactory answerOptionFactory;

    @Autowired
    private JobService jobService;


//    @Autowired
//    private ResumeService resumeService;
    @Autowired
    private FileDocumentService documentService;

    private final AtomicInteger atomicInteger = new AtomicInteger(0);


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
        this.setDummyJob();
        this.setupDummyJobApplication();
    }

    public FileDocument createFileDocument() throws IOException {
        FileDocument document = FileDocumentRepositoryTest.createMockDocument();
        //document.setResourceType(ResourceType.JOB_APPLICATION);
        //save document
        documentService.save(document);
        return document;
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
