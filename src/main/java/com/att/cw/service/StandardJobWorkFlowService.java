/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.dao.JobWorkFlowRepository;
import com.att.cw.dao.StandardJobWorkFlowRepository;
import com.att.cw.model.JobActivity;
import com.att.cw.model.JobWorkFlow;
import com.att.cw.model.StandardJobWorkFlow;
import com.att.cw.support.workflow.ActivityStatus;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * JobWorkFlow service
 *
 * @author ebrimatunkara
 */
@Service("standardJobWorkflowService")
public class StandardJobWorkFlowService implements CrudService<StandardJobWorkFlow, Long>, InitializingBean {

    @Resource
    private StandardJobWorkFlowRepository workflowRepository;

    @Autowired
    private JobActivityService jobActivityService;

    @Override
    public StandardJobWorkFlow save(StandardJobWorkFlow object) {
        return workflowRepository.save(object);
    }

    @Override
    public StandardJobWorkFlow find(Long id) {
        return workflowRepository.findOne(id);
    }

    @Override
    public List<StandardJobWorkFlow> findAll() {
        return (List<StandardJobWorkFlow>) workflowRepository.findAll();
    }

    @Override
    public Page<StandardJobWorkFlow> findAll(Pageable page) {
        return workflowRepository.findAll(page);
    }

    @Override
    public void delete(Long id) {
        //  workflowRepository.delete(id);
    }

    /**
     * Initialize default standard workflow
     *
     * @throws java.lang.Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        /*  System.out.println("After start  standard workflow---");
        String name = "Internal Applicantion";
        StandardJobWorkFlow workflow = workflowRepository.findByName(name);//
        if(workflow == null){
          workflow = new StandardJobWorkFlow(name);
          workflowRepository.save(workflow);
        }
        
        
        Set<JobActivity> activities = new HashSet();
        JobActivity  a1 = new JobActivity(workflow,ActivityStatus.SEND_MAIL);
        jobActivityService.save(a1);
        activities.add(a1);
        workflow.setActivities(activities);
        workflowRepository.save(workflow);
         */
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(StandardJobWorkFlow object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
