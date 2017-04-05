package com.att.cw.controller.restricted;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/restricted")
public class FileUploadController {

    /**
     * TODO --upload file
     *
     * @param name
     * @param file
     * @return
     *
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        String message;
        if (!file.isEmpty()) {
            try {

                byte[] bytes = file.getBytes();
                try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)))) {
                    stream.write(bytes);
                }
                message = "You successfully uploaded " + name + "!";
                return new ResponseEntity(message, HttpStatus.CREATED);
            } catch (Exception e) {
                message = "You failed to upload " + name + " => " + e.getMessage();
                return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
            }
        } else {
            message = "You failed to upload " + name + " because the file was empty.";
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }
    }

}
