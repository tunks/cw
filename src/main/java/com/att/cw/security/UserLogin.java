package com.att.cw.security;

public class UserLogin {
       public String name;
       public String password;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
        
        public static class  LoginBuild{
             private final UserLogin login;
             public LoginBuild(){
                login = new UserLogin();
             }
             
             public LoginBuild setName(String name){
                this.login.setName(name);
                return this;
             }
              public LoginBuild setPassword(String password){
                this.login.setPassword(password);
                return this;
             }
              
              public UserLogin create(){
                 return login;
              }
        }
        
        
}