package com.att.cw.controller.restricted;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.att.cw.model.FileDocument;
import com.att.cw.service.FileDocumentService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * FileUpload controller
 *
 * @author ebrimatunkara
 */
@Controller
@RequestMapping("/restricted/files")
public class FileController {

    @Autowired
    private FileDocumentService documentService;

    /**
     * TODO --upload file
     *
     * @param name
     * @param file
     * @return
     *
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity handleFileUpload(@RequestParam(value = "name", required = false) String name, @RequestParam("file") MultipartFile file) {
        String message;
        Map<String, String> response = new HashMap();
        if (!file.isEmpty()) {
            String fileName = (name != null) ? name : file.getOriginalFilename();
            try {
                FileDocument document = new FileDocument();
                document.setContentType(file.getContentType());
                document.setName(fileName);
                document.setContent(FileCopyUtils.copyToByteArray(file.getInputStream()));
                document.setSize(file.getSize());
                FileDocument result = documentService.save(document);
                response.put("id", result.getId().toString());
                response.put("name", fileName);
                response.put("msg", "You successfully uploaded " + fileName + "!");
                return new ResponseEntity(response, HttpStatus.OK);
            } catch (Exception e) {
                response.put("msg", "You failed to upload " + fileName + " => " + e.getMessage());
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
        } else {
            response.put("msg", "You failed to upload, because the file was empty.");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) throws Exception {
        FileDocument document = documentService.find(id);
        if (document != null) {
            String contentType = document.getContentType();
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("charset", "utf-8");
            responseHeaders.setContentType(MediaType.parseMediaType(contentType));
            responseHeaders.setContentLength(document.getSize());
            responseHeaders.set("Content-disposition", "attachment; filename=" + document.getName());
            return new ResponseEntity(document.getContent(), responseHeaders, HttpStatus.OK);
        }
        return new ResponseEntity("No such file available",HttpStatus.BAD_REQUEST);
    }
}
