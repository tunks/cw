/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.JobAnswerOptionRepository;
import com.att.cw.dto.JobApplicationEntryDto;
import com.att.cw.dto.JobApplicationEntryDto.AnswerEntryDto;
import com.att.cw.model.JobAnswerEntry;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * JobAnswerEntryService
 *
 * @author ebrimatunkara
 */
@Service("jobAnswerEntryService")
public class JobAnswerEntryService implements CrudService<JobAnswerEntry, Long> {

    @Resource
    private JobAnswerOptionRepository jobAnswerOptionRepository;

    @Override
    public JobAnswerEntry save(JobAnswerEntry object) {
        return jobAnswerOptionRepository.save(object);
    }

    @Override
    public JobAnswerEntry find(Long id) {
        return jobAnswerOptionRepository.findOne(id);
    }

    @Override
    public List<JobAnswerEntry> findAll() {
        return (List<JobAnswerEntry>) jobAnswerOptionRepository.findAll();
    }

    @Override
    public Page<JobAnswerEntry> findAll(Pageable page) {
        return jobAnswerOptionRepository.findAll(page);
    }

    @Override
    public void delete(Long id) {
        jobAnswerOptionRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    JobAnswerEntry save(AnswerEntryDto entry) {
        Long entryId = entry.getId();
        String value = entry.getValue();
        JobAnswerEntry entryObj = (entryId != null) ? find(entryId) : new JobAnswerEntry();
        entryObj.setValue(value);
        return save(entryObj);
    }

    @Override
    public void delete(JobAnswerEntry object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
