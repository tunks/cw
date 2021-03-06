package com.att.cw.controller.restricted;

import com.att.cw.controller.BaseController;
import com.att.cw.model.FileDocument;
import com.att.cw.service.FileDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * JobController concrete implementation of BaseController
 *
 * @author ebrimatunkara
 */
@Controller
@RequestMapping("/restricted/documents")
public class FileDocumentController implements BaseController<FileDocument, Long> {

    public static final String NOT_SUPPORTED_YET = "Not supported yet.";
    /**
     * Job service instance
     *
     */
    @Autowired
    private FileDocumentService documentService;

    /**
     * find and return job entity by id
     *
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Override
    public FileDocument find(@PathVariable Long id) {
        return documentService.find(id);
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    /**
     * delete job entity by id
     *
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Override
    public void delete(@PathVariable Long id) {
        documentService.delete(id);
    }

    @Override
    public FileDocument create(FileDocument object) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    @Override
    public FileDocument update(FileDocument object) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

}
