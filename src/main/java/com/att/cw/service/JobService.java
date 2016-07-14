
package com.att.cw.service;

import com.att.cw.dao.JobRepository;
import com.att.cw.model.Job;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * JobService concrete implementation
 * @author ebrimatunkara
 */
@Service("jobService")
public class JobService implements CrudService<Job,Long>{
    /**
     * JobRepository data access object
     **/
    @Resource
    private JobRepository jobRepository;
    
    /**
     * Save Job entity object 
     * @param object
     * @return , returns saved object
     **/
    @Override
    public Job save(Job object) {
      return jobRepository.save(object);
    }

    /**
     * Find Job entity object by ID
     * @param id
     * @return , returns object
     **/
    @Override
    public Job find(Long id) {
        return jobRepository.findOne(id);
    }

    /**
     * Delete Job entity object by ID
     * @param id
     **/
    @Override
    public void delete(Long id) {
         jobRepository.delete(id);
    }
    
    /**
     * find and return jobs by pageable
     * @param page
     * @return 
     **/
    @Override
    public Page<Job> findAll(Pageable page){
       return jobRepository.findAll(page);
    }
    /**
     * find and return jobs by owner id and pageable
     * @param ownerId
     * @param page
     * @return 
     **/
    public Page<Job> findAllByOwner(Long ownerId ,Pageable page){
           return jobRepository.findByOwnerId(ownerId, page);
    }

     /**
     * Find and return all jobs
     * @return 
     **/
    @Override
    public List<Job> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
