package ehealth.middleware.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HealthData implements Serializable
{

    private static final long serialVersionUID = 1L;
    private String parameterType;
    private String userId;
    private String timeFrom;
    private String timeTo;

    public HealthData() 
    {

    }

    public HealthData(String parameterType, String userId, String timeFrom, String timeTo) 
    {
        this.parameterType = parameterType;
        this.userId = userId;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public String getParameterType() 
    {
        return this.parameterType;
    }

    public void setParameterType(String parameterType) 
    {
        this.parameterType = parameterType;
    }

    public String getUserId() 
    {
        return this.userId;
    }

    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getTimeFrom() 
    {
        return this.timeFrom;
    }

    public void setTimeFrom(String timeFrom) 
    {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() 
    {
        return this.timeTo;
    }

    public void setTimeTo(String timeTo) 
    {
        this.timeTo = timeTo;
    }
    
}