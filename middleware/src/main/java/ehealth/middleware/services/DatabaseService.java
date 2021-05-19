package ehealth.middleware.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ehealth.middleware.models.Measurement;
import ehealth.middleware.models.MeasurementRequest;

public class DatabaseService 
{

    private static final String CONN_STRING = "jdbc:sqlserver://localhost\\sqlexpress;databaseName=E-Zdravstvo;integratedSecurity=true";
    private static final String DB_USER = "";
    private static final String DB_PASS = "";

    public DatabaseService()
    {
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(CONN_STRING, DB_USER, DB_PASS);
            if (conn != null) 
            {
                conn.close();
                return;
            }
            else
            {
                return;
            }
        }
        catch(SQLException e)
        {
            conn = null;
            return;
        }
    } 

    public List<Measurement> getMeasurements(MeasurementRequest request)
    {
        List<Measurement> measurements = new ArrayList<>();
        return measurements;
    }
    
}