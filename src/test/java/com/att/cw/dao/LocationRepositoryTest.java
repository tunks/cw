/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.dao;

import com.att.cw.model.Location;
import com.att.cw.projection.LocationProjection;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * LocationRepository unit test
 * @author ebrimatunkara
 */
@ActiveProfiles({"test", "dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc-servlet.xml"})
@WebAppConfiguration
public class LocationRepositoryTest {
    @Autowired
    private LocationRepository locationRepository;
   
    
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

    /**
     * Test of findByLocationType method, of class LocationRepository.
     */
    @Test
    public void testFindByLocationType() {
        System.out.println("findByLocationType");
        int type = 0;
        List<LocationProjection> result = locationRepository.findByLocationType(type);
        assertTrue(result.size() > 0);
      }

    /**
     * Test of findByLocationTypeAndParentId method, of class LocationRepository.
     */
    @Test
    public void testFindByLocationTypeAndParentId() {
        System.out.println("findByLocationTypeAndParentId");
        int type = 0;
        Long parentId = null;
        List<LocationProjection> result = locationRepository.findByLocationType(type);
        type = 1; //state 
        parentId = result.get(0).getId();
        result = locationRepository.findByLocationTypeAndParentId(type, parentId);
        assertTrue(result.size() > 0);
    }

    
}
