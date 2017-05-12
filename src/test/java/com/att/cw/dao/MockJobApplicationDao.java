/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.Job;
import com.att.cw.model.JobAnswerEntry;
import com.att.cw.model.JobApplication;
import com.att.cw.model.JobCandidate;
import com.att.cw.model.JobQuestionAnswer;
import com.att.cw.service.JobAnswerOptionService;
import com.att.cw.service.JobApplicationService;
import com.att.cw.service.JobCandidateService;
import com.att.cw.service.JobQuestionAnswerService;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ebrimatunkara
 */
public class MockJobApplicationDao extends MockJobQuestionDao {
    @Autowired
    protected JobApplicationService jobApplicationService;

    @Autowired
    protected JobCandidateService jobCandidateService;

    @Autowired
    protected JobQuestionAnswerService jobQuestionAnswerService;

    @Autowired
    protected JobAnswerOptionService jobAnswerOptionService;

    protected JobApplication application;
    protected JobCandidate candidate;
    protected Set<JobQuestionAnswer> answers;

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    protected void setupDummyJobApplication() {
        candidate = createJobCandidate();
        application = createJobApplication(job, candidate);
        //jobanswer factory        //create and save job question answers
        answers = saveJobAnswer();
        //set job application answers
        application.setQuestionAnswers(answers);
        application.setSubmitted(Boolean.TRUE);
        jobApplicationService.save(application);
    }

    protected JobApplication createJobApplication(Job job, JobCandidate candidate) {
        //job application
        JobApplication appl = new JobApplication();
        appl.setJob(job);
        appl.setCandidate(candidate);
        return appl;
    }

    protected JobCandidate createJobCandidate() {
        JobCandidate object = new JobCandidate();
        return jobCandidateService.save(object);
    }

    protected Set<JobQuestionAnswer> saveJobAnswer() {
        //save job question answer
        return questions.stream()
                //.filter(q->{ return !q.isRequired();})
                .map(q -> {
                    //set question answer
                    JobQuestionAnswer ans = new JobQuestionAnswer(q);
                    int num = atomicInteger.getAndIncrement();
                    JobAnswerEntry entry = new JobAnswerEntry("Text value answer: " + num);
                    JobAnswerEntry entryObj = jobAnswerOptionService.save(entry);
                    //set question answer options
                    ans.setAnswerEntry(entryObj);
                    ans.setQuestion(q);
                    //save and return question answer
                    return jobQuestionAnswerService.save(ans);
                }).collect(Collectors.toSet());
    }

}
