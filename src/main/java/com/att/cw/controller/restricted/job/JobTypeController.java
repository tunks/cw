/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.restricted.job;

import com.att.cw.controller.BaseController;
import com.att.cw.dto.JobTypeDto;
import com.att.cw.model.JobType;
import com.att.cw.service.JobTypeService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * JobTypeController
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/restricted/jobs/types")
public class JobTypeController implements BaseController<JobType,Long>{
    @Autowired
    private JobTypeService jobtypeService;
  
    @RequestMapping(method = RequestMethod.GET)
    public List<JobTypeDto> findAll() {
       return jobtypeService.findAll()
               .stream()
               .map(jt -> {
                  return new JobTypeDto(jt.getId(),jt.getName());
               }).collect(Collectors.toList());
    }
    
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    @Override
    public JobType find(@PathVariable Long id) {
       return jobtypeService.find(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Override
    public void deleteAll() {
       jobtypeService.deleteAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    @Override
    public void delete(@PathVariable Long id) {
        jobtypeService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Override
    public JobType create(JobType object) {
        return jobtypeService.save(object);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @Override
    public JobType update(JobType object) {
       return jobtypeService.save(object);
    }
    
}
