package com.att.cw.service;

import com.att.cw.dao.JobRepository;
import com.att.cw.dto.JobDto;
import com.att.cw.model.Job;
import com.att.cw.model.JobQuestion;
import com.att.cw.support.DataTypeHelper;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toSet;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    @Autowired
    private JobQuestionService jobQuestionService;

    /**
     * Save Job entity object
     *
     * @param object
     * @return , returns saved object
     *
     */
    @Override
    public Job save(Job object) {
        return jobRepository.save(object);
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
     * Find and return all jobs
     *
     * @return
     *
     */
    @Override
    public List<Job> findAll() {
        return (List<Job>) jobRepository.findAll();
    }

    public Job findByQuestionId(Long id) {
        return jobRepository.findByQuestionId(id);
    }

    @Override
    public void deleteAll() {
        jobRepository.deleteAll();
    }

    public Job save(JobDto dto) {
        //find and load the job entity
        Long id = dto.getId();
        Job entity = (id != null) ? find(id) : new Job();
        //set  job entity
        byte[] description = (dto.getDescription() != null) ? DataTypeHelper.stringToByte(dto.getDescription()) : null;
        byte[] responsibilities = (dto.getResponsibilities() != null) ? DataTypeHelper.stringToByte(dto.getResponsibilities()) : null;
        byte[] skills = (dto.getDescription() != null) ? DataTypeHelper.stringToByte(dto.getSkills()) : null;

        entity.setTitle(dto.getTitle());
        entity.setDescription(description);
        entity.setResponsibilities(responsibilities);
        entity.setSkills(skills);
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

        //save job questions, questionaire is null
        Set<JobQuestion> questions = jobQuestionService.saveDto(dto.getQuestions());
        //set job questions
        entity.getQuestions().addAll(questions);

        return save(entity);
    }

    @Override
    public boolean exists(Long id) {
        return jobRepository.exists(id);
    }

}
