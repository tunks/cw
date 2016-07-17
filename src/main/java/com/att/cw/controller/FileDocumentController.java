package com.att.cw.controller;


import com.att.cw.model.FileDocument;
import com.att.cw.service.FileDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * JobController concrete implementation of BaseController
 * @author ebrimatunkara
 */
@RestController
@RequestMapping("/documents")
public class FileDocumentController implements BaseController<FileDocument,Long>{
    /**
     * Job service instance
     **/
    @Autowired
    private FileDocumentService documentService;
    /**
     * find and return job entity by id
     **/
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    @Override
    public FileDocument find(@PathVariable Long id) {
         return  documentService.find(id);
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
    public void delete(@PathVariable Long id) {
      documentService.delete(id);
    }


    @Override
    public FileDocument create(FileDocument object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FileDocument update(FileDocument object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
