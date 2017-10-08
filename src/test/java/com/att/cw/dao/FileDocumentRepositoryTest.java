package com.att.cw.dao;

import com.att.cw.model.FileDocument;
import com.att.cw.support.ResourceType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.FileCopyUtils;

/**
 * FileDocumentRepository test implementation
 *
 */
@ActiveProfiles({"test", "dev"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springmvc-servlet.xml"})
@WebAppConfiguration
public class FileDocumentRepositoryTest {

    @Resource
    private FileDocumentRepository documentRepository;
    private FileDocument document;
    private FileDocument result;

    @Before
    public void setUp() throws Exception {
        document = FileDocumentRepositoryTest.createMockDocument();
    }

    public static FileDocument createMockDocument() throws IOException {
        FileDocument doc = new FileDocument();
       // doc.setResourceType(ResourceType.USER);
        doc.setContentType(MediaType.IMAGE_JPEG_VALUE);
        //store file data as bytes
        ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
        //load xml file as test file
        InputStreamSource resource = new ClassPathResource("spring-jpa-config.xml");
        FileCopyUtils.copy(resource.getInputStream(), output);
        doc.setContent(output.toByteArray());
        doc.setContentType(MediaType.APPLICATION_XML_VALUE);
        return doc;
    }

    @After
    public void tearDown() throws Exception {
        if (result != null) {
            documentRepository.delete(result);
        }
    }

    @Test
    public void test() {
        result = documentRepository.save(document);
        assertNotNull(result);
    }

}
