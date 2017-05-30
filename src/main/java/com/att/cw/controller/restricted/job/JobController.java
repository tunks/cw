package com.att.cw.controller.restricted.job;

import com.att.cw.controller.BaseController;
import com.att.cw.dto.ErrorResponse;
import com.att.cw.dto.JobDto;
import com.att.cw.dto.JobQuestionDto;
import com.att.cw.dto.mappers.JobDtoMapper;
import com.att.cw.dto.mappers.JobQuestionDtoMapper;
import com.att.cw.service.JobService;
import com.att.cw.exception.JobException;
import com.att.cw.model.Job;
import java.util.Collections;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.slf4j.LoggerFactory;
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
 *
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/restricted/jobs")
public class JobController implements BaseController<JobDto, Long> {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JobController.class);

    /**
     * Job service instance
     */
    @Autowired
    private JobService jobService;

    /**
     * Find all
     *
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

    /**
     * Delete multiple jobs by list of ids
     * @param ids
     * @param forceful
     * @return 
     */
    @RequestMapping( method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAll(@RequestParam("ids") List<Long> ids, @RequestParam(value ="forceful", required=false, defaultValue="true") boolean forceful) {
        try{ 
            jobService.delete(ids,forceful);       
            return new ResponseEntity(Collections.singletonMap("msg", "Job(s) successfully deleted"), HttpStatus.OK);
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
            throw new JobException("Error deleting job ids "+ids);
        }
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
     *
     * @param object
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @Override
    public JobDto create(@RequestBody JobDto object) {
        return JobDtoMapper.mapFullEntityIntoDto(jobService.save(object));
    }

    /**
     * Update and return existing job entity
     */
    @RequestMapping(method = RequestMethod.PUT)
    @Override
    public JobDto update(@RequestBody JobDto object) {
        return JobDtoMapper.mapEntityIntoDTO(jobService.save(object));
    }

    /**
     * Get job questions
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/questions", method = RequestMethod.GET)
    public List<JobQuestionDto> getQuestions(@PathVariable Long id) {
        Job job = jobService.find(id);
        return JobQuestionDtoMapper.mapEntitiesIntoDTOs(job.getQuestions().stream().collect(toList()));
    }

    @ExceptionHandler({JobException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(JobException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Job action", ex.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
