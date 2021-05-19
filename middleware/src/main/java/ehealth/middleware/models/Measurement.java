package ehealth.middleware.models;

import java.io.Serializable;
import java.util.Date;

public class Measurement implements Serializable 
{

    private static final long serialVersionUID = 1L;
    private long userId;
    private long deviceId;
    private Date time;
    private String type;
    private String value;

    public Measurement() 
    {

    }

    public Measurement(long userId, long deviceId, Date time, String type, String value) 
    {
        this.userId = userId;
        this.deviceId = deviceId;
        this.time = time;
        this.type = type;
        this.value = value;
    }

    public long getUserId() 
    {
        return this.userId;
    }

    public void setUserId(long userId) 
    {
        this.userId = userId;
    }

    public long getDeviceId() 
    {
        return this.deviceId;
    }

    public void setDeviceId(long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Date getTime() 
    {
        return this.time;
    }

    public void setTime(Date time) 
    {
        this.time = time;
    }

    public String getType() 
    {
        return this.type;
    }

    public void setType(String type) 
    {
        this.type = type;
    }

    public String getValue() 
    {
        return this.value;
    }

    public void setValue(String value) 
    {
        this.value = value;
    }

}