package com.att.cw.dao;

import com.att.cw.model.FileDocument;
import com.att.cw.support.ResourceType;
import java.io.ByteArrayOutputStream;
import javax.annotation.Resource;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.FileCopyUtils;

/**
 * FileDocumentRepository test implementation
 **/
@ContextConfiguration(locations = "classpath:spring-jpa-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FileDocumentRepositoryTest {
    @Resource
    FileDocumentRepository documentRepository;

    private FileDocument document;
    private  byte [] content;
    @Before
    public void setUp() throws Exception {
         document   = new FileDocument();
         document.setResourceType(ResourceType.USER);
         document.setContentType(MediaType.IMAGE_JPEG_VALUE);
         //store file data as bytes 
         ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
         //load xml file as test file
         InputStreamSource resource = new ClassPathResource("spring-jpa-config.xml");
         FileCopyUtils.copy(resource.getInputStream(), output);
         content =  output.toByteArray();
         document.setContent(output.toByteArray());
         document.setContentType(MediaType.APPLICATION_XML_VALUE);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {    
         FileDocument result = documentRepository.save(document);
         assertNotNull(result);
         Assert.assertArrayEquals(content,result.getContent());
    }

}
