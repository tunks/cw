package com.att.cw.service;

import com.att.cw.dao.JobRepository;
import com.att.cw.dto.JobDto;
import com.att.cw.model.Job;
import com.att.cw.model.JobApplication;
import com.att.cw.model.JobCategory;
import java.util.List;
import static java.util.stream.Collectors.toSet;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * JobService concrete implementation
 *
 * @author ebrimatunkara
 */
@Service("jobService")
public class JobService implements CrudService<Job, Long> {

    /**
     * JobRepository data access object
     *
     */
    @Resource
    private JobRepository jobRepository;

    @Autowired
    private JobCategoryService jobCategoryService;

    @Autowired
    private JobTypeService jobtypeService;

    /**
     * Save Job entity object
     *
     * @param object
     * @return , returns saved object
     *
     */
    @Override
    public Job save(Job object) {
        // mapReferenceObjects(object);

        return jobRepository.save(object);
    }

    private void mapReferenceObjects(Job object) {
//        JobCategory category = object.getCategory();
//        if(category != null){
//            //object.setCategory(jobCategoryService.find(category.getId()));
//            
//        }
    }

    /**
     * Find Job entity object by ID
     *
     * @param id
     * @return , returns object
     *
     */
    @Override
    public Job find(Long id) {
        return jobRepository.findOne(id);
    }

    /**
     * Delete Job entity object by ID
     *
     * @param id
     *
     */
    @Override
    public void delete(Long id) {
        jobRepository.delete(id);
    }

    /**
     * find and return jobs by pageable
     *
     * @param page
     * @return
     *
     */
    @Override
    public Page<Job> findAll(Pageable page) {
        return jobRepository.findAll(page);
    }

    /**
     * find and return jobs by owner id and pageable
     *
     * @param ownerId
     * @param page
     * @return
     */
//    public Page<Job> findAllByOwner(Long ownerId ,Pageable page){
//           return jobRepository.findByOwnerId(ownerId, page);
//    }
    /**
     * Find and return all jobs
     *
     * @return
     *
     */
    @Override
    public List<Job> findAll() {
        return (List<Job>) jobRepository.findAll();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Job save(JobDto dto) {
        //find and load the job entity
        Job entity = (dto.getId() != null) ? find(dto.getId()) : new Job();
        if (entity == null) {
            entity = new Job();
        }
        //set 
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setVacancy(dto.getVacancy());
        entity.setLocation(dto.getLocation());
        //save new entity object
        if (entity.isNew()) {
            save(entity);
        }
        //save reference objects
        entity.getCategories().addAll(dto.getCategories()
                .stream().map(c -> {
                    return jobCategoryService.find(c.getId());
                }).collect(toSet()));

        if (dto.getJobType() != null) {
            entity.setJobType(jobtypeService.find(dto.getJobType().getId()));
        }
        return save(entity);
    }

    @Override
    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
