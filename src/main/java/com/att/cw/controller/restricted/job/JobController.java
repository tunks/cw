package com.att.cw.controller.restricted.job;

import com.att.cw.controller.BaseController;
import com.att.cw.dto.ErrorResponse;
import com.att.cw.dto.JobDto;
import com.att.cw.dto.mappers.JobDtoMapper;
import com.att.cw.model.Job;
import com.att.cw.model.JobApplication;
import com.att.cw.model.JobCategory;
import com.att.cw.service.JobApplicationService;
import com.att.cw.service.JobCategoryService;
import com.att.cw.service.JobService;
import com.att.cw.exception.JobException;
import com.att.cw.exception.NotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * JobController rest controller implementation of BaseController
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/restricted/jobs")
public class JobController implements BaseController<JobDto,Long>{
    /**
     * Job service instance
     */
    @Autowired
    private JobService jobService;

    /**
     * Find all
     * @param ownerId
     * @param page
     * @return 
     *
     */
    @RequestMapping(method = RequestMethod.GET)
    public Page<JobDto> findAllBy(@RequestParam(value = "groupId", required = false) Long ownerId, Pageable page) {
        // if(ownerId != null)
        //   return jobService.findAllByOwner(ownerId, page);
        return JobDtoMapper.mapEntityPageIntoDTOPage(page, jobService.findAll(page));
    }

    /**
     * find and return job entity by id
     *
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Override
    public JobDto find(@PathVariable Long id) {
        return JobDtoMapper.mapEntityIntoDTO(jobService.find(id));
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * delete job entity by id
     *
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Override
    @ResponseBody
    public void delete(@PathVariable Long id) {
        jobService.delete(id);
    }

    /**
     * Create and return new job entity
     * @param object
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST)
    @Override
    public JobDto create(@RequestBody JobDto object) {     
        return JobDtoMapper.mapEntityIntoDTO(jobService.save(object));
    }

    /**
     * Update and return existing job entity
     */
    @RequestMapping(method = RequestMethod.PUT)
    @Override
    public JobDto update(@RequestBody JobDto object) {
        return JobDtoMapper.mapEntityIntoDTO(jobService.save(object));
    }
    
    @ExceptionHandler({JobException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(JobException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Job action", ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }
}
