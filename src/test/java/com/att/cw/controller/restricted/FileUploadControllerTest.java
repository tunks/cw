/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.controller.restricted;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author ebrimatunkara
 */
@ActiveProfiles({"test", "dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/springmvc-servlet.xml",
    "file:src/main/webapp/WEB-INF/root-context.xml",
    "file:src/main/webapp/WEB-INF/applicationContext-security.xml"})
@WebAppConfiguration
public class FileUploadControllerTest {
    
    @Autowired
    private WebApplicationContext context;

    @Mock
    private MockMvc mockMvc;

    private String endPoint;
    
    private MockMultipartFile multipartFile;
    private final String FILE_NAME = "log4j.xml";
    public FileUploadControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws FileNotFoundException, IOException {
        endPoint =  "/restricted/files/upload";
        mockMvc = webAppContextSetup(context).build();
        DefaultResourceLoader rsLoader = new DefaultResourceLoader();
        Resource resource = rsLoader.getResource(FILE_NAME);
        FileInputStream fis = new FileInputStream(resource.getFile());
        multipartFile = new MockMultipartFile("file", fis);

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of handleFileUpload method, of class FileUploadController.
     */
    @Test
    public void testHandleFileUpload() {
        try {
            System.out.println("handleFileUpload");
            mockMvc.perform(MockMvcRequestBuilders.fileUpload(endPoint)
                    .file(multipartFile))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch (Exception ex) {
            Logger.getLogger(FileUploadControllerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
