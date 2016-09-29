CW- A simple HR recruitment  backend API service 

--------------------------------------------------------
For developer
--------------------------------------------------------
Run embedded jetty web application server 

mvn clean install

mvn jetty:run

Test end points
 1. User registration
    To register new user ,use sample json data (email must be unique for each new user)
    
    Request method --> POST http://localhost:8080/cw/register/new 
    
    Request body --> {"email": "ebrima1@att.com", "password" :"QgIaffOLtyKGDMad"}
    
    Content-Type --> application/json

 2. User login 
    To login registered user, user sample json data
    
    Request method --> POST http://localhost:8080/cw/authenticate/login
    
    Request body --> {"email": "ebrima1@att.com", "password" :"QgIaffOLtyKGDMad"}
    
    Content-Type --> application/json

For UI development
   1. Required development dependencies are: nodejs, gulp, bower
   2. a. First install nodejs , 
      b. Run "npm install" in the project directory to install the dev packages 
      c. Run "gulp build" to automatically build ui source codes( src/main/ng-app) to the webapp directory 
      d. Run "gulp" to start continuous live-reload server (port 8888), 
         which will automatically  build and refresh the browser during development  