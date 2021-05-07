package ehealth.middleware.models;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DatabaseHealthData implements Serializable
{

    private static final long serialVersionUID = 1L;
    private List<String> dataValues;
    private String userId;
    private String upperLimit;
    private String lowerLimit;

    public DatabaseHealthData() 
    {
        
    }

    public DatabaseHealthData(List<String> dataValues, String userId, String upperLimit, String lowerLimit) 
    {
        this.dataValues = dataValues;
        this.userId = userId;
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
    }

    public List<String> getDataValues() 
    {
        return this.dataValues;
    }

    public void setDataValues(List<String> dataValues) 
    {
        this.dataValues = dataValues;
    }

    public String getUserId() 
    {
        return this.userId;
    }

    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUpperLimit() 
    {
        return this.upperLimit;
    }

    public void setUpperLimit(String upperLimit) 
    {
        this.upperLimit = upperLimit;
    }

    public String getLowerLimit() 
    {
        return this.lowerLimit;
    }

    public void setLowerLimit(String lowerLimit) 
    {
        this.lowerLimit = lowerLimit;
    }
    
}