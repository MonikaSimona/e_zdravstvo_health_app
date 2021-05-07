package ehealth.middleware.services;

import ehealth.middleware.models.DatabaseUser;

public class AuthenticationService 
{

    public AuthenticationService()
    {

    }
    
    public DatabaseUser register(String username, String password)
    {
        if(username.equals("Toto") && password.equals("123"))
        {
            DatabaseUser user = new DatabaseUser("1", username);
            return user;
        }
        else
        {
            return null;
        }
    }

    public DatabaseUser login(String username, String password)
    {
        if(username.equals("Toto") && password.equals("123"))
        {
            DatabaseUser user = new DatabaseUser("1", username);
            return user;
        }
        else
        {
            return null;
        }
    }

}