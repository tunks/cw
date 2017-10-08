/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.restricted.job;

import com.att.cw.controller.BaseController;
import com.att.cw.dto.JobApplicationEntryDto;
import com.att.cw.dto.JobQuestionListDto;
import com.att.cw.dto.mappers.JobApplicationDtoMapper;
import com.att.cw.exception.JobApplicationException;
import com.att.cw.model.Job;
import com.att.cw.model.JobApplication;
import com.att.cw.service.JobApplicationService;
import com.att.cw.service.JobService;
import com.att.cw.exception.NotFoundException;
import com.att.cw.model.JobCandidate;
import com.att.cw.model.User;
import com.att.cw.service.UserService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/restricted/applications")
public class JobApplicationController implements BaseController<JobApplication, Long> {

    /**
     * Job service instance
     */
    @Autowired
    private JobService jobService;
    /**
     * Job application service
     */
    @Autowired
    private JobApplicationService jobApplicationService;

    @Autowired
    private UserService userService;

    /**
     * Post job applications
     *
     * @param dto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> saveApplication(@RequestBody JobApplicationEntryDto dto) {
        JobApplicationEntryDto result = saveJobApplication(dto);
        Map<String, Object> body = new HashMap();
        body.put("content", result);
        body.put("msg", "Job application successfully saved ");
        return new ResponseEntity(body, HttpStatus.OK);
    }

    /**
     * Post job applications
     *
     * @param dto
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public JobApplicationEntryDto updateApplication(@RequestBody JobApplicationEntryDto dto) {
        return saveJobApplication(dto);
    }

    /**
     * Find job applications by id
     *
     * @param jobId
     * @param page
     * @return
     */
//    @RequestMapping(method = RequestMethod.GET)
//    public Page<JobApplicationDto> findApplications(@RequestParam("jobId") Long jobId, Pageable page) {
////        Job job = jobService.find(jobId);
////        //throw exception if job is not found
////        if (job == null) {
////            throw new NotFoundException(jobId);
////        }
//        //find job applications
//        //TODO
//        return null;//jobApplicationService.findByJob(job, page);
//    }
    /**
     * Find job applications by id
     *
     * @param jobId
     * @param userId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public JobApplicationEntryDto find(@RequestParam("jobId") Long jobId, @RequestParam(name = "userId", required = false) Long userId) {
        Job job = jobService.find(jobId);
        User user = (userId != null) ? userService.find(userId) : null;

        if (user != null) {
            JobCandidate candidate = user.getCandidate();
            JobApplication application = jobApplicationService.findByCandidateAndJob(candidate, job);
            return JobApplicationDtoMapper.mapEntityIntoDTO(application, job);
        }
        return JobApplicationDtoMapper.mapEntityIntoDTO(null, job);
    }

    /**
     * Find all jobs by question id and user id
     *
     * @param jobId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/entries", method = RequestMethod.GET)
    public JobQuestionListDto findApplicationEntries(@RequestParam("jobid") Long jobId, @RequestParam(name = "userId", required = false) Long userId) {
        Job job = jobService.find(jobId);
        if (job != null) {
            return JobQuestionListDto.build(job);
        }
        throw new NotFoundException(jobId);
    }

    /**
     * Delete job application
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteApplication(@PathVariable Long id) {
//        Job job = jobService.find(jobId);
//        //throw exception if job is not found
//        if (job == null) {
//            throw new NotFoundException(jobId);
//        }
//        //delete job application
//        delete(id);
    }

    @Override
    public JobApplication find(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) {
        jobApplicationService.delete(id);
    }

    @Override
    public JobApplication create(JobApplication object) {
        //save the job application object
        return jobApplicationService.save(object);
    }

    @Override
    public JobApplication update(JobApplication object) {
        return jobApplicationService.save(object);
    }

    /**
     * Save job application
     *
     * @param dto: JobApplicationEntryDto
     * @param JobApplicationEntryDto
     *
     */
    private JobApplicationEntryDto saveJobApplication(JobApplicationEntryDto dto) throws JobApplicationException, NotFoundException {
        Long jobId = dto.getJobId();
        Long userId = dto.getUserId();
        Job job = jobService.find(jobId);
        User user = userService.find(userId);
        //throw exception if job is not found
        if (job == null) {
            throw new NotFoundException(jobId);
        }

        if (user == null) {
            throw new NotFoundException(userId);
        }

        JobApplication application = jobApplicationService.save(job, user, dto);
        if (application == null) {
            throw new JobApplicationException("Job application unseccessful");
        }
        return JobApplicationDtoMapper.mapEntityIntoDTO(application);
    }

}
