package com.att.cw.dao;

import com.att.cw.model.FileDocument;
import com.att.cw.support.ResourceType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/**
 * FileDocument Repository interface - extending Paging,sorting and CRUD interface operations
 * Spring data - http://docs.spring.io/spring-data/jpa/docs/1.9.4.RELEASE/reference/html/
 **/
public interface FileDocumentRepository extends PagingAndSortingRepository<FileDocument, Long>{
//       /**
//        * Find and return file document by resource id and resource type with pagination
//        * @param resourceId
//        * @param resourceType
//        * @param page
//        * @return 
//        */
//       public Page<FileDocument> findByResourceIdAndResourceType(Long resourceId,ResourceType resourceType, Pageable page);
//       
//        /**
//        * Find and return file document by resource id and resource type 
//        * @param resourceId
//        * @param resourceType
//        * @return 
//        */
//       public FileDocument findByResourceIdAndResourceType(Long resourceId,ResourceType resourceType);
}
