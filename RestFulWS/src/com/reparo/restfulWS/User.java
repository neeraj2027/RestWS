package com.reparo.restfulWS;  

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement; 

@XmlRootElement(name = "user")
@XmlAccessorType (XmlAccessType.FIELD)
public class User implements Serializable {  
   private static final long serialVersionUID = 1L; 
   private int id; 
   private String name; 
   private String profession;  
   public User(){} 
    
   public User(int id, String name, String profession){  
      this.id = id; 
      this.name = name; 
      this.profession = profession; 
   }  
   public int getId() { 
      return id; 
   }  
   public void setId(int id) { 
      this.id = id; 
   } 
   public String getName() { 
      return name; 
   } 
   public void setName(String name) { 
      this.name = name; 
   } 
   public String getProfession() { 
      return profession; 
   } 
   public void setProfession(String profession) { 
      this.profession = profession; 
   }

@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", profession=" + profession + "]";
}   
   
} 