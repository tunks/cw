package com.att.cw.model;

import com.att.cw.support.ResourceType;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * FileDocument
 */
@Entity
@Table(name = "file_documents")
public class FileDocument extends Audit implements Serializable {
    /**
     * document id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * document description
     */
    private String description;
    /*
     * resource id
     */
    private Long resourceId;
    
   /**
    * resource type 
    **/
    private ResourceType resourceType;
    
    @OneToOne
    private FileContent file;

    @Override
    public Long getId() {
       return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }    

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public FileContent getFile() {
        return file;
    }

    public void setFile(FileContent file) {
        this.file = file;
    }
}
