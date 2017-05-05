/*
 * Project name: Archive Rest service
 * A simple Rest service to archive data in MongoDB and index data in Apache Solr for  dynamic search queries 
 * 2016 © ATT Service Assurance  - Raptor POC team 
 *
 */
package com.att.cw.model;

import java.util.HashMap;

/**
 * SearchableContent class
 *
 * @author ebrimatunkara
 */
public class SearchableContent extends HashMap<String, Object> implements Searchable {

}
