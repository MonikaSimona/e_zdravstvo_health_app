package ehealth.middleware.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ehealth.middleware.models.DatabaseHealthData;

public class DatabaseService 
{

    public DatabaseService()
    {

    } 

    public DatabaseHealthData getDataFromDb(String type, String user, Date start, Date end)
    {
        if((type.equalsIgnoreCase("heart rate")) && (user.equals("1")))
        {
            List<String> datas = new ArrayList<>();
            datas.add("rand4");
            datas.add("rand5");
            datas.add("rand6");
            DatabaseHealthData dbData = new DatabaseHealthData(datas, user, "lower", "upper");
            return dbData;
        }
        else
        {
            return null;
        }
    }
    
}