package com.att.cw.controller.open;

  
  
//import org.apache.log4j.Logger;

import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.RestController;  

  
@RestController  
@RequestMapping("open")
public class HelloWorld {  
	//static final Logger logger = Logger.getLogger(HelloWorld.class);
	
 @RequestMapping(value = "/helloworld", method = RequestMethod.GET,headers="Accept=application/json")  
 public String sayHelloWorld()  
 {  
	//logger.debug("hello World Called");
	return "hello World";
 }  
  
}
