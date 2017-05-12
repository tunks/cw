package com.att.cw.service;

import com.att.cw.dao.FileDocumentRepository;
import com.att.cw.model.FileDocument;
import com.att.cw.support.ResourceType;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * FileDocument service concrete implementation
 *
 * @author ebrimatunkara
 */
@Service("documentService")
public class FileDocumentService implements CrudService<FileDocument, Long> {

    /**
     * FileDocumentRepository data access object
     *
     */
    @Resource
    private FileDocumentRepository fileDocumentRepository;

    /**
     * Save FileDocument entity object
     *
     * @param object
     * @return , returns saved object
     *
     */
    @Override
    public FileDocument save(FileDocument object) {
        return fileDocumentRepository.save(object);
    }

    /**
     * Find FileDocument entity object by ID
     *
     * @param id
     * @return , returns object
     *
     */
    @Override
    public FileDocument find(Long id) {
        return fileDocumentRepository.findOne(id);
    }

    /**
     * Delete Job entity object by ID
     *
     * @param id
     *
     */
    @Override
    public void delete(Long id) {
        fileDocumentRepository.delete(id);
    }

    /**
     * find and return jobs by owner id and pageable
     *
     * @param resourceId
     * @param type
     * @param page
     * @return
     *
     */
//    public Page<FileDocument> findAllByOwner(Long resourceId ,ResourceType type, Pageable page){
//           return  fileDocumentRepository.findByResourceIdAndResourceType(resourceId, type, page);
//    }
    /**
     * Find and return all file documents
     *
     * @return
     *
     */
    @Override
    public List<FileDocument> findAll() {
        return (List<FileDocument>) fileDocumentRepository.findAll();
    }

    /**
     * Find and return all file documents by page
     *
     * @param page
     * @return
     *
     */
    @Override
    public Page<FileDocument> findAll(Pageable page) {
        return fileDocumentRepository.findAll(page);
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
    public void delete(FileDocument object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
