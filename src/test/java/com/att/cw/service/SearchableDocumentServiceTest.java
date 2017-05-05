/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.service;

import com.att.cw.model.Job;
import com.att.cw.model.JobCategory;
import com.att.cw.model.JobLocation;
import com.att.cw.model.JobVacancy;
import com.att.cw.model.SearchableDocument;
import com.att.cw.support.solr.ObjectToSolrDocumentConverter;
import com.att.cw.support.solr.SolrDocumentToObjectConverter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author ebrimatunkara
 */
@ActiveProfiles({"dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-solr-config_test.xml"})
public class SearchableDocumentServiceTest {

    @Autowired
    private SearchableDocumentService searchableDocumentService;
    private SearchableDocument document;
    private final String description = "As the technical architect, you’ll be responsible for defining the overall structure of a program or system. You’ll act as project manager, overseeing IT assignments that are aimed at improving the business, and ensuring all parts of the project run smoothly. ";
    private Set<JobCategory> categories;
    private JobLocation location;
    private Job job;
    private ObjectToSolrDocumentConverter converter;
    private SolrDocumentToObjectConverter solrToObjectConverter;

    public SearchableDocumentServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        converter = new ObjectToSolrDocumentConverter();
        solrToObjectConverter = new SolrDocumentToObjectConverter();
        //categories
        categories = new HashSet();
        JobCategory cat1 = new JobCategory();
        cat1.setName("Big Data");

        JobCategory cat2 = new JobCategory();
        cat2.setName("Software Development");

        categories.add(cat1);
        categories.add(cat2);

        //location
        location = new JobLocation();
        location.setCity("Orlando");
        location.setLocationState("Florida");
        location.setCountry("United States of America");

        ///job
        job = new Job();
        job.setId(generatedId());
        job.setTitle("Technical Architect");
        // job.setVersion(Long.MIN_VALUE);
        job.setDescription(description.getBytes(StandardCharsets.UTF_8));
        job.setCategories(categories);
        job.setLocation(location);

        //Job vacancy
        JobVacancy vacancy = new JobVacancy();
        vacancy.setCloseDate(new Date());
        vacancy.setOpenDate(new Date());
        job.setVacancy(vacancy);
        //document 
        document = converter.convert(job);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class SearchableDocumentService.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        SearchableDocument result = searchableDocumentService.save(document);
        assertNotNull(result);
        System.out.println("document indexed result " + result);
        Map obj = solrToObjectConverter.convert(result);
        System.out.println("Document Object " + obj);

    }

    /**
     * Test of find method, of class SearchableDocumentService.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        SearchableDocument result = searchableDocumentService.find(job.getId());
        /// assertNotNull(result);
    }

    /**
     * Test of findAll method, of class SearchableDocumentService.
     */
    @Test
    public void testFindAll_0args() {
        System.out.println("findAll");
        //  List<SearchableDocument> result = searchableDocumentService.findAll();
        //assertNotNull(result);
    }

    /**
     * Test of findAll method, of class SearchableDocumentService.
     */
//    @Test
//    public void testFindAll_Pageable() {
//        System.out.println("findAll");
//        Pageable page = null;
//        Page<SearchableDocument> expResult = null;
//        Page<SearchableDocument> result = searchableDocumentService.findAll(page);
//        assertEquals(expResult, result);
//    }
    /**
     * Test of delete method, of class SearchableDocumentService.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = document.getId();
        searchableDocumentService.delete(id);
    }

    /**
     * Test of deleteAll method, of class SearchableDocumentService.
     */
    @Test
    public void testDeleteAll() {
        System.out.println("deleteAll");
        searchableDocumentService.deleteAll();

    }

    /**
     * Test of exists method, of class SearchableDocumentService.
     */
    @Test
    public void testExists() {
        System.out.println("exists");
//        boolean result = searchableDocumentService.exists(document.getId());
//        assertNotNull(result);
    }

    /**
     * Test of search method, of class SearchableDocumentService.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        MultiValueMap params = new LinkedMultiValueMap();
        Page result = searchableDocumentService.search(params);
        assertNotNull(result);
        System.out.println("results " + result.getNumberOfElements());
    }

    /**
     * Test of search method, of class SearchableDocumentService.
     */
    @Test
    public void testSearchWithPageable() {
        // System.out.println("exists");
        //boolean result = searchableDocumentService
        //assertNotNull(result);
    }

    private Long generatedId() {
        long leftLimit = 1L;
        long rightLimit = 1000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}
