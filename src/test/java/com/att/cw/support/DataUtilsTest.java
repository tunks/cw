/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.support;

import com.att.cw.dto.JobDto;
import com.att.cw.model.Job;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;
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
public class DataUtilsTest {

    public DataUtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

//    /**
//     * Test of stringToByte method, of class DataUtils.
//     */
//    @Test
//    public void testStringToByte() {
//        System.out.println("stringToByte");
//        String text = "";
//        byte[] expResult = null;
//        byte[] result = DataUtils.stringToByte(text);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of bytesToString method, of class DataUtils.
//     */
//    @Test
//    public void testBytesToString() {
//        System.out.println("bytesToString");
//        byte[] bytes = null;
//        String expResult = "";
//        String result = DataUtils.bytesToString(bytes);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toUtcDate method, of class DataUtils.
//     */
//    @Test
//    public void testToUtcDate() {
//        System.out.println("toUtcDate");
//        String dateStr = "";
//        String expResult = "";
//        String result = DataUtils.toUtcDate(dateStr);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of mapToObject method, of class DataUtils.
//     */
//    @Test
//    public void testMapToObject() throws Exception {
//        System.out.println("mapToObject");
//        Object expResult = null;
//        Object result = DataUtils.mapToObject(null);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getClassFields method, of class DataUtils.
//     */
    @Test
    public void testGetClassFields_Class_StringArr() {
        System.out.println("getClassFields");
        Class classType = Job.class;
        String[] excludeFields = ArrayUtils.EMPTY_STRING_ARRAY;
        Map<String, Field> result = DataUtils.getClassFields(classType, excludeFields);
        assertTrue(!result.isEmpty());
    }
//
//    /**
//     * Test of getClassFields method, of class DataUtils.
//     */
//    @Test
//    public void testGetClassFields_3args() {
//        System.out.println("getClassFields");
//        Class classType = null;
//        boolean all = false;
//        String[] excludeFields = null;
//        Map<String, Field> expResult = null;
//        Map<String, Field> result = DataUtils.getClassFields(classType, all, excludeFields);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of classTypeExists method, of class DataUtils.
//     */
//    @Test
//    public void testClassTypeExists() {
//        System.out.println("classTypeExists");
//        String name = "";
//        boolean expResult = false;
//        boolean result = DataUtils.classTypeExists(name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of mapClassName method, of class DataUtils.
//     */
//    @Test
//    public void testMapClassName() {
//        System.out.println("mapClassName");
//        Class base = null;
//        String name = "";
//        String expResult = "";
//        String result = DataUtils.mapClassName(base, name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of objectToJson method, of class DataUtils.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testObjectToJson() throws Exception {
        System.out.println("objectToJson");
        JobDto dto = new JobDto();
        dto.setTitle("Software Engineer");
        dto.setId(new Long(100));
        dto.setSkills("Java, Spring, HTML");
        String json = DataUtils.objectToJson(dto);
        String title = JsonPath.parse(json).read("$.title");
        assertNotNull(json);
        assertNotNull(title);

    }

}
