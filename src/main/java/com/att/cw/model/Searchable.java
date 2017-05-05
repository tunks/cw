/*
 * Project name: Archive Rest service
 * A simple Rest service to archive data in MongoDB and index data in Apache Solr for  dynamic search queries 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.cw.model;

/**
 * Document Searchable Interface containing field names and dynamic field
 * suffices
 *
 * @author ebrimatunkara
 */
public interface Searchable {
    String ID_FIELD = "id";
    String BOOLEAN_FIELD = "_b";
    String DATE_FIELD = "_dt";
    String TEXT_FIELD = "_t";
    String NUMERIC_FIELD = "_d";
    String TYPE_FIELD = "doc_type";
    String[] EXCLUDED_FIELDS = {"serialVersionUID"};
    int SEARCH_FEILD_LEVEL = 1;
    String FIELD_SEPERATOR = ".";
    String QUERY_PARAM = "q";

}
