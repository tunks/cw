package com.att.cw.model;

import com.att.cw.listener.AuditingEntityListener;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.Version;
/**
 * @author ebrimatunkara 
 * Audit class that will provide the auditing for model entities
 * @param <ID>
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Audit<ID extends Serializable> implements Serializable{
    private static final long serialVersionUID = 1L;
    @Version
    private Long version;

    private String createdBy;
    private String lastModifiedBy;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    public abstract ID getId();
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.domain.Persistable#isNew()
     */
    public boolean isNew() {
        /**
         * Assuming the Id is generated by database. If not, then need to
         * override in each model depending on how to identify if a Model is a
         * new one or already saved Model.  
         */
        return getId() == null;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.domain.Auditable#getCreatedBy()
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.domain.Auditable#setCreatedBy(java.lang.Object)
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.domain.Auditable#getCreatedDate()
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.domain.Auditable#setCreatedDate(org.joda.time.DateTime)
     */
    public void setCreatedDate(Date creationDate) {
        // TODO Auto-generated method stub
        this.createdDate = creationDate;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.domain.Auditable#getLastModifiedBy()
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.domain.Auditable#setLastModifiedBy(java.lang.Object)
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.domain.Auditable#getLastModifiedDate()
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /* (non-Javadoc)
     * @see org.springframework.data.domain.Auditable#setLastModifiedDate(org.joda.time.DateTime)
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        // TODO Auto-generated method stub
        this.lastModifiedDate = lastModifiedDate;
    }
}
