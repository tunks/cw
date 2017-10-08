/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.support;

import com.att.cw.support.solr.ObjectToSolrDocumentConverter;
import com.att.cw.model.Job;
import com.att.cw.model.JobCategory;
import com.att.cw.model.JobLocation;
import com.att.cw.model.SearchableDocument;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ebrimatunkara
 */
public class ObjectToSolrDocumentConverterTest {

    private String description = "If you are using reflection, why do you care, why do this check at all. The get/set methods always use objects so you don't need to know if the field is a primitive type (unless you try to set a primitive type to the null value";
    private Set<JobCategory> categories;
    private JobLocation location;

    public ObjectToSolrDocumentConverterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        categories = new HashSet();
        JobCategory cat1 = new JobCategory();
        cat1.setName("Big Data");

        JobCategory cat2 = new JobCategory();
        cat2.setName("Software Development");

        categories.add(cat1);
        categories.add(cat2);

        //location
        location = new JobLocation();
        location.setCity("New York City");
        location.setLocationState("NYC");
        location.setCountry("United States of America");

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of convert method, of class ObjectToSolrDocumentConverter.
     */
    @Test
    public void testConvert() {
        System.out.println("convert");
        Job obj = new Job();
        obj.setId(Long.MIN_VALUE);
        obj.setTitle("Software engineer");
        obj.setVersion(Long.MIN_VALUE);
        //obj.setDescription(description.getBytes(StandardCharsets.UTF_8));
        obj.setCategories(categories);
        //obj.setLocation(location);

        ObjectToSolrDocumentConverter instance = new ObjectToSolrDocumentConverter();
        SearchableDocument result = instance.convert(obj);
        assertNotNull(result);
        System.out.println(result);
        // TODO review the generated test code and remove the default call to fail.
    }

}
