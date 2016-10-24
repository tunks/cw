/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.JobWorkFlowRepository;
import com.att.cw.model.JobWorkFlow;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * JobWorkFlow service
 * @author ebrimatunkara
 */
@Service("jobWorkflowService")
public class JobWorkFlowService implements CrudService<JobWorkFlow,Long>{
    @Resource
    private JobWorkFlowRepository workflowRepository;
    
    @Override
    public JobWorkFlow save(JobWorkFlow object) {
        return workflowRepository.save(object);
    }

    @Override
    public JobWorkFlow find(Long id) {
        return workflowRepository.findOne(id);
    }

    @Override
    public List<JobWorkFlow> findAll() {
        return (List<JobWorkFlow>) workflowRepository.findAll();
    }

    @Override
    public Page<JobWorkFlow> findAll(Pageable page) {
        return workflowRepository.findAll(page);
    }

    @Override
    public void delete(Long id) {
        workflowRepository.delete(id);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
