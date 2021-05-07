package ehealth.middleware;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import ehealth.middleware.models.DatabaseHealthData;
import ehealth.middleware.models.HealthData;
import ehealth.middleware.services.DatabaseService;

public class test 
{

    private DatabaseService service = new DatabaseService();

    public test()
    {

    }

    public static void main(String[] args) 
    {
        test t = new test();
        String d1 = "06:31:41 AM 29-11-2017";
        String d2 = "06:31:41 AM 29-11-2018";
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a dd-MM-yyyy");
        try 
        {
            Date date1 = sdf.parse(d1);
            Date date2 = sdf.parse(d2);
            System.out.println(date2);
            HealthData data = new HealthData("heart rate", "1", "06:31:41 AM 29-11-2017", "06:31:41 AM 29-11-2018");
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            try 
            {
                String json = ow.writeValueAsString(data);
                System.out.println(json);
            } 
            catch (JsonProcessingException e) 
            {
                e.printStackTrace();
            }
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
    }

}