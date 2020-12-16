package com.guosen.serviceuser.domain;

import java.io.Serializable;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
  
    private String username;
  
    private String password;

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
       return  this.id;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public String getUserName(){
       return  this.username;
    }
    public void setPassword(String pwd){
      this.password = BCrypt.hashpw(pwd, BCrypt.gensalt());
  }

  public Boolean checkPwd(String pwd){
     Boolean b = BCrypt.checkpw(pwd, this.password);
     return b;
  }

    @Override
  public String toString() {
    return getId() + "," + getUserName();
  }
}
