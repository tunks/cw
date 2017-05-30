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
import com.att.cw.dto.JobApplicationEntryDto.FileDto;
import com.att.cw.dto.JobApplicationEntryDto.QuestionAnswerDto;
import com.att.cw.listener.JobApplicationEntityListener;
import com.att.cw.model.FileDocument;
import com.att.cw.model.Job;
import com.att.cw.model.JobAnswerEntry;
import com.att.cw.model.JobApplication;
import com.att.cw.model.JobCandidate;
import com.att.cw.model.JobQuestion;
import com.att.cw.model.JobQuestionAnswer;
import com.att.cw.model.QuestionOption;
import com.att.cw.model.User;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import static java.util.stream.Collectors.toSet;
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
public class JobApplicationService extends JobApplicationEntityListener implements CrudService<JobApplication, Long>  {
    
    @Resource
    private JobApplicationRepository jobApplicationRepository;
    
    @Autowired
    private JobQuestionService jobQuestionService;
    
    @Autowired
    private JobQuestionAnswerService jobQuestionAnswerService;
    
    @Autowired
    private JobAnswerEntryService jobAnswerEntryService;
    
    @Autowired
    private JobCandidateService jobCandidateService;
   
    @Autowired
    private JobQuestionOptionService jobQuestionOptionService;
    
    @Autowired
    private FileDocumentService documentService;
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
        JobApplication appObject = (id != null) ? find(id) : new JobApplication();
        //set job candidate   
        if( appObject.isNew()){ 
            JobCandidate candidate =jobCandidateService.findByUser(user);   
            appObject.setJob(job);
            appObject.setCandidate((candidate != null)? candidate: jobCandidateService.save(new JobCandidate(user)));
        }
        
        Set<JobQuestionAnswer> answers = new HashSet();
        for( QuestionAnswerDto e: application.getQuestionAnswers()){
            Long qId = e.getQuestion().getId();
           JobQuestion question = jobQuestionService.find(qId);
           AnswerDto ans = e.getAnswer();
           JobQuestionAnswer answer = null;
           if (ans != null) {
               Long answerId = ans.getId();
               answer = (answerId != null) ? jobQuestionAnswerService.find(answerId) : new JobQuestionAnswer();
               if (answer.isNew()) {
                   answer.setQuestion(question);
               }
               //answer entry
               AnswerEntryDto entryDto = ans.getEntry();
               if (entryDto != null) {
                   Long entryId = entryDto.getId();
                   JobAnswerEntry entry = (entryId !=null)? jobAnswerEntryService.find(entryId): new  JobAnswerEntry();
                   entry.setValue(entryDto.getValue());
                   entry.setQuestionOptions(entryDto.getOptions()
                           .stream()
                           .map(x->{
                               return jobQuestionOptionService.find(x.getId());
                           })
                           .collect(toSet()));
                   
                   FileDto fileDto = entryDto.getFile();
                   if(fileDto != null){
                      FileDocument document = documentService.find(fileDto.getId());
                      entry.setDocument(document);
                   }
                   else{
                       entry.setDocument(null);
                   }
                   answer.setAnswerEntry(entry);//jobAnswerEntryService.save(entry));//
                   answers.add(answer);//jobQuestionAnswerService.save(answer));
               }
           }
         }

        appObject.setQuestionAnswers(answers);
        appObject.setSubmitted(application.getSubmitted());
       
        JobApplication result = save(appObject);
        //when application is submitted , send email
        if(application.getSubmitted()){
          this.postUpdateMessage(result);
        }
        return result;
    }
    
    @Override
    public void delete(JobApplication object) {
        jobApplicationRepository.delete(object);
    }

    public JobApplication findByCandidateAndJob(JobCandidate candidate, Job job) {
        return jobApplicationRepository.findByCandidateAndJob(candidate,job);
    }

    void deleteAll(List<JobApplication> apps) {
        jobApplicationRepository.delete(apps);
    }
}
