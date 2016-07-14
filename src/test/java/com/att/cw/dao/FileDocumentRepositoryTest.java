package com.att.cw.dao;

import javax.annotation.Resource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;

/**
 * FileDocumentRepository test implementation
 **/
@ContextConfiguration(locations = "classpath:spring-jpa-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FileDocumentRepositoryTest {
    @Resource
    FileDocumentRepository documentRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {

    }

}
