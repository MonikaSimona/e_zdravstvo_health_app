package ehealth.middleware.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DatabaseUser implements Serializable
{

    private static final long serialVersionUID = 1L;
    private String userId;
    private String username;

    public DatabaseUser() 
    {

    }

    public DatabaseUser(String userId, String username) 
    {
        this.userId = userId;
        this.username = username;
    }

    public String getUserId() 
    {
        return this.userId;
    }

    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUsername() 
    {
        return this.username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }
   
}