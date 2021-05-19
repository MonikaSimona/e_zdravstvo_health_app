package ehealth.middleware;

import java.sql.Connection;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import ehealth.middleware.models.User;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SqlTest {

    public SqlTest() {

    }

    public static void main(String[] args) {
        SqlTest t = new SqlTest();
        // t.printResultOfInsert();
        // t.printResultOfSelect();
        User u = t.printMixedResult("556613", "picke", "Jassumnevidliv21", "pece", "meckata", "13/12/2012", 176, 99,
                "klinecot@gmail.com");
        if (u == null) {
            System.out.println("Failed");
        } 
        else 
        {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            try 
            {
                String json = ow.writeValueAsString(u);
                System.out.println(json);
            } 
            catch (JsonProcessingException e) 
            {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    private void printResultOfSelect()
    {
        String dbURL = "jdbc:sqlserver://localhost\\sqlexpress;databaseName=E-Zdravstvo;integratedSecurity=true";
        Connection conn = null;
        System.out.println("Trying to establish a connection with the database...");
        try 
        {
            conn = DriverManager.getConnection(dbURL, "", "");
            if(conn != null)
            {
                System.out.println("Preparing and executing the query...");
                String sql = "SELECT * FROM Person WHERE MBR_ID=?";
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1, "556610");
                ResultSet result = stm.executeQuery();
                while(result.next())
                {
                    String fName = result.getString("FIRST_NAME");
                    String lName = result.getString("LAST_NAME");
                    System.out.println("Full name is: " + fName + " " + lName);
                }
                result.close();
            }
            else
            {
                System.out.println("Failed to connect to the database.");
            }
        } 
        catch (SQLException e) 
        {
           System.out.println(e.getLocalizedMessage());
        }
        finally
        {
            try 
            {
                if (conn != null && !conn.isClosed()) 
                {
                    conn.close();
                    System.out.println("The connection is closed.");
                }
                else
                {
                    System.out.println("The connection is closed.");
                }
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex.getLocalizedMessage());
            }
        }
    }

    private void printResultOfInsert()
    {
        String dbURL = "jdbc:sqlserver://localhost\\sqlexpress;databaseName=E-Zdravstvo;integratedSecurity=true";
        Connection conn = null;
        try
        {
            System.out.println("Trying to establish a connection with the database...");
            conn = DriverManager.getConnection(dbURL, "", "");
            if (conn != null) 
            {
                System.out.println("Preparing and executing the query...");
                String mbr = "412233";
                String fname = "mendo";
                String lname = "lasa";
                String dateString = "15/12/2012";
                int height = 178;
                int weight = 76;
                String email = "zorancho@gmail.com";
                Date birthDate = null;
                try
                {
                    birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
                    java.sql.Date sqlDate = new java.sql.Date(birthDate.getTime());
                    String sql1 = "INSERT INTO Person (MBR_ID, FIRST_NAME, LAST_NAME, DATE_BIRTH, HEIGHT, WEIGHT, EMAIL)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement stm = conn.prepareStatement(sql1);
                    stm.setString(1, mbr);
                    stm.setString(2, fname);
                    stm.setString(3, lname);
                    stm.setDate(4, sqlDate);
                    stm.setInt(5, height);
                    stm.setInt(6, weight);
                    stm.setString(7, email);
                    int rows = stm.executeUpdate();
                    if(rows == 0)
                    {
                        System.out.println("Query execution has failed.");
                    }
                    else
                    {
                        System.out.println("A total of " + rows + " rows were affected by this query.");
                    }
                }
                catch(ParseException ex)
                {
                    System.out.println(ex.getLocalizedMessage());
                }
            }
            else
            {
                System.out.println("Failed to connect to the database.");
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getLocalizedMessage());
        }
        finally 
        {
            try 
            {
                if (conn != null && !conn.isClosed()) 
                {
                    conn.close();
                    System.out.println("The connection is closed.");
                }
                else
                {
                    System.out.println("The connection is closed.");
                }
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex.getLocalizedMessage());
            }
        }
    }

    private User printMixedResult(String mbr, String username, String password, String fName, String lName, 
    String bDate, int height, int weight, String email)
    {
        Connection conn = null;
        String dbURL = "jdbc:sqlserver://localhost\\sqlexpress;databaseName=E-Zdravstvo;integratedSecurity=true";
        try
        {
            conn = DriverManager.getConnection(dbURL, "", "");
            if (conn != null) 
            {
                String sql1 = "SELECT * FROM Person WHERE MBR_ID=? OR EMAIL=?";
                PreparedStatement stm = conn.prepareStatement(sql1);
                stm.setString(1, mbr);
                stm.setString(2, email);
                ResultSet result = stm.executeQuery();
                if(result.next())
                {
                    System.out.println("Found existing.");
                    stm.close();
                    conn.close();
                    return null;
                }
                else
                {
                    stm.close();
                    String sql2 = "INSERT INTO Person (MBR_ID, FIRST_NAME, LAST_NAME, DATE_BIRTH, HEIGHT, WEIGHT, EMAIL)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
                    Date birthDate = null;
                    try
                    {
                        birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(bDate);
                        java.sql.Date sqlDate = new java.sql.Date(birthDate.getTime());
                        stm = conn.prepareStatement(sql2);
                        stm.setString(1, mbr);
                        stm.setString(2, fName);
                        stm.setString(3, lName);
                        stm.setDate(4, sqlDate);
                        stm.setInt(5, height);
                        stm.setInt(6, weight);
                        stm.setString(7, email);
                        int rows = stm.executeUpdate();
                        if(rows == 0)
                        {
                            stm.close();
                            conn.close();
                            return null;
                        }
                        else
                        {
                            stm.close();
                            String sql3 = "INSERT INTO Person_Login (MBR_ID, PERSON_USERNAME, PERSON_PASSWORD)"
                            + " VALUES (?, ?, ?)";
                            stm = conn.prepareStatement(sql3);
                            stm.setString(1, mbr);
                            stm.setString(2, username);
                            stm.setString(3, password);
                            rows = stm.executeUpdate();
                            if(rows == 0)
                            {
                                stm.close();
                                conn.close();
                                return null;
                            }
                            else
                            {  
                                Date bbDate = new SimpleDateFormat("dd/MM/yyyy").parse(bDate);
                                User us = new User(Integer.parseInt(mbr), 
                                fName, lName, bbDate, 
                                height, weight, email);
                                stm.close();
                                conn.close();
                                return us;
                            }
                        }
                    }
                    catch(ParseException ex)
                    {
                        ex.printStackTrace();
                        return null;
                    }
                }
            }
            else
            {
                return null;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        finally 
        {
            try 
            {
                if (conn != null && !conn.isClosed()) 
                {
                    conn.close();
                }
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex.getLocalizedMessage());
            }
        }
    }

}