/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.JobApplicationRepository;
import com.att.cw.dto.JobApplicationEntryDto;
import com.att.cw.dto.JobApplicationEntryDto.AnswerDto;
import com.att.cw.dto.JobApplicationEntryDto.AnswerEntryDto;
import com.att.cw.model.Job;
import com.att.cw.model.JobAnswerEntry;
import com.att.cw.model.JobApplication;
import com.att.cw.model.JobCandidate;
import com.att.cw.model.JobQuestion;
import com.att.cw.model.JobQuestionAnswer;
import com.att.cw.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * JobApplication service
 *
 * @author ebrimatunkara
 */
@Service("jobApplicationService")
public class JobApplicationService implements CrudService<JobApplication, Long> {
    
    @Resource
    private JobApplicationRepository jobApplicationRepository;
    
    @Autowired
    private JobQuestionService jobQuestionService;
    
    @Autowired
    private JobQuestionAnswerService jobQuestionAnswerService;
    
    @Autowired
    private JobAnswerOptionService jobAnswerOptionService;
    
    @Autowired
    private JobCandidateService jobCandidateService;

    /**
     * Save job object
     *
     * @param object
     * @return
     */
    @Override
    public JobApplication save(JobApplication object) {
        return jobApplicationRepository.save(object);
    }

    /**
     * Find job object by id
     *
     * @param id
     * @return
     */
    @Override
    public JobApplication find(Long id) {
        return jobApplicationRepository.findOne(id);
    }

    /**
     * Find all jobs
     *
     * @return
     */
    @Override
    public List<JobApplication> findAll() {
        return (List<JobApplication>) jobApplicationRepository.findAll();
    }

    /**
     * Find all job applications by page
     *
     * @param page
     * @return
     */
    @Override
    public Page<JobApplication> findAll(Pageable page) {
        return jobApplicationRepository.findAll(page);
    }

    /**
     * Delete job application
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        jobApplicationRepository.delete(id);
    }

    /**
     * Find list of job applications by page
     *
     * @param job
     * @param page
     * @return
     */
    public Page<JobApplication> findByJob(Job job, Pageable page) {
        return jobApplicationRepository.findByJob(job, page);
    }

    /**
     * Find job application by id and job
     *
     * @param id
     * @param job
     * @return
     */
    public JobApplication findByIdAndJob(Long id, Job job) {
        return jobApplicationRepository.findByIdAndJob(id, job);
    }
    
    List<JobApplication> findByJob(Job job) {
        return jobApplicationRepository.findByJob(job);
    }
    
    @Override
    public void deleteAll() {
        jobApplicationRepository.deleteAll();
    }
    
    @Override
    public boolean exists(Long id) {
        return jobApplicationRepository.exists(id);
    }
    
    public JobApplication save(JobApplicationEntryDto application) {
        //Long userId = application.getUserId();
        
        return null;
    }
    
    public JobApplication save(Job job, User user, JobApplicationEntryDto application) {
        Long id = application.getId();       
        //TODO -- catch null exception
        JobApplication appObject = (id != null) ? find(id) : new JobApplication();
        //set job candidate
         JobCandidate candidate = appObject.getCandidate();
         candidate = (candidate ==null)?jobCandidateService.findByUser(user): candidate;
        
        if(candidate== null){
            candidate = new JobCandidate(user);
            jobCandidateService.save(candidate);
        }
        
        List<JobQuestionAnswer> questionAnswers = new ArrayList();
        application.getQuestionAnswers().stream().forEach(e -> {
            Long qId = e.getQuestion().getId();
            JobQuestion question = jobQuestionService.find(qId);
            AnswerDto ans = e.getAnswer();
            if (ans != null) {
                Long answerId = ans.getId();
                JobQuestionAnswer answer = (answerId != null) ? jobQuestionAnswerService.find(answerId) : new JobQuestionAnswer();
                if (answer.isNew()) {
                    answer.setQuestion(question);
                }
                AnswerEntryDto entryDto = ans.getEntry();
                if (entryDto != null) {
                    JobAnswerEntry entry = new  JobAnswerEntry();
                    entry.setId(entryDto.getId());
                    entry.setValue(entryDto.getValue());
                    JobAnswerEntry entryObj = jobAnswerOptionService.save(entry);
                    answer.setAnswerEntry(entryObj);
                }
                
                questionAnswers.add(jobQuestionAnswerService.save(answer));
            }
        });
        
        appObject.getQuestionAnswers().addAll(questionAnswers);
        appObject.setJob(job);
        appObject.setCandidate(candidate);
        //candidate.getApplications().add(appObject);
        save(appObject);
        //set to candidate
        //candidate.getApplications().add(appObject);
        //jobCandidateService.save(candidate);
        return appObject;
    }
    
    @Override
    public void delete(JobApplication object) {
        jobApplicationRepository.delete(object);
    }

    public JobApplication findByCandidateAndJob(Job job, JobCandidate candidate) {
           return jobApplicationRepository.findByCandidateAndJob(job,candidate);
    }
}
