/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.ResumeRepository;
import com.att.cw.model.Resume;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Resume service
 * @author ebrimatunkara
 */
@Service("resumeService")
public class ResumeService implements CrudService<Resume,Long>{
    @Resource 
    private ResumeRepository resumeRepository;
    /**
     * Save resume object
     * @param object
     * @return 
     */
    @Override
    public Resume save(Resume object) {
      return resumeRepository.save(object);
    }
    /**
     * Find resume object
     * @param id
     * @return 
     */
    @Override
    public Resume find(Long id) {
      return resumeRepository.findOne(id);
    }
    
    /**
     *  List of all resumes
     * @return 
     */
    @Override
    public List<Resume> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * List of resumes by page and sorting(default size 2)
     * @param page
     * @return 
     */
    @Override
    public Page<Resume> findAll(Pageable page) {
       return resumeRepository.findAll(page);
    }
    /**
     * Delete resume object by id
     * @param id
     */
    @Override
    public void delete(Long id) {
       resumeRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
