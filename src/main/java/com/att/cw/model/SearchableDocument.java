/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Dynamic;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * Searchable Document data model
 *
 * @author ebrimatunkara
 */
@SolrDocument
public class SearchableDocument extends SearchableContent {

    /*
     * Solr Document Id 
     */
    @Id
    @Indexed
    @Field(ID_FIELD)
    private Long id;
    /**
     * document data type
     *
     */
    @Field(TYPE_FIELD)
    private String type;
    
    @Field(JSON_FIELD)
    private String json;
    /**
     * dynamic string|text field contents
     *
     */
    @Dynamic
    @Field("*" + TEXT_FIELD)
    private Map<String, Object> textContent = new HashMap();

    /**
     * dynamic date field contents
     *
     */
    @Dynamic
    @Field("*" + DATE_FIELD)
    private Map<String, Object> dateContent = new HashMap();

    /**
     * dynamic numeric field contents
     *
     */
    @Dynamic
    @Field("*" + NUMERIC_FIELD)
    private Map<String, Object> numericContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getTextContent() {
        return textContent;
    }

    public void setTextContent(Map<String, Object> content) {
        this.textContent = content;
    }

    public Map<String, Object> getDateContent() {
        return dateContent;
    }

    public Map<String, Object> getNumericContent() {
        return numericContent;
    }

    public void setNumericContent(Map<String, Object> numericContent) {
        this.numericContent = numericContent;
    }

    public void setDateContent(Map<String, Object> dateContent) {
        this.dateContent = dateContent;
    }

    public void addTextValue(String field, Object object) {
        textContent.put(field, object);
    }

    public void addNumericValue(String field, Object object) {
        numericContent.put(field, object);
    }

    public void addDateValue(String field, Object object) {
        dateContent.put(field, object);
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "SearchableDocument{" + "id=" + id + ", type=" + type + ", textContent=" + textContent + ", dateContent=" + dateContent + '}';
    }

    public static class SearchableDocumentBuilder {

        private SearchableDocument document;
        private Map<String, Object> content = new HashMap();

        public SearchableDocumentBuilder() {
            document = new SearchableDocument();
        }

        public SearchableDocumentBuilder setId(Long id) {
            document.setId(id);
            return this;
        }

        public SearchableDocumentBuilder setDocType(String docType) {
            document.setType(docType);
            return this;
        }

        public SearchableDocumentBuilder setContent(Map<String, Object> content) {
            if (content != null) {
                this.content = content;
            }
            return this;
        }
        
         public SearchableDocumentBuilder setAsJson(String json) {
            this.document.setJson(json);
            return this;
        }
        
        

        public SearchableDocument build() {
            content.entrySet().stream().forEach(e -> {
                Object value = e.getValue();
                if (value instanceof Date || value instanceof Timestamp) {
                    document.addDateValue(e.getKey(), value);
                } else {
                    document.addTextValue(e.getKey(), value);
                }
            });

            return document;
        }
    }
}
