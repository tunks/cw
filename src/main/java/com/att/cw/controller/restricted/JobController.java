package com.att.cw.controller.restricted;




import com.att.cw.controller.BaseController;
import com.att.cw.model.Job;
import com.att.cw.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
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
public class JobController implements BaseController<Job,Long>{
    /**
     * Job service instance
     **/
    @Autowired
    private JobService jobService;
   
    /**
     * find all
     * @param ownerId
     * @param page
     * @return 
     **/
    @RequestMapping(method=RequestMethod.GET)
    public Page<Job> findAllBy(@RequestParam(value="groupId", required=false) Long ownerId, Pageable page){
       if(ownerId != null)
           return jobService.findAllByOwner(ownerId, page);
       return jobService.findAll(page);
    }
    /**
     * find and return job entity by id
     **/
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    @Override
    public Job find(@PathVariable Long id) {
         return  jobService.find(id);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * delete job entity by id
     **/
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @Override
    @ResponseBody
    public void delete(@PathVariable Long id) {
       jobService.delete(id);
    }

    /**
     * Create and return new job entity
     **/
    @RequestMapping(method=RequestMethod.POST)
    @Override
    public Job create(Job object) {
        return jobService.save(object);
    }

    /**
     * Update and return existing job entity
     **/
    @RequestMapping(method=RequestMethod.PUT)
    @Override
    public Job update(Job object) {
        return jobService.save(object);
    }
    
}
