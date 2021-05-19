package ehealth.middleware.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MeasurementRequest implements Serializable
{

    private static final long serialVersionUID = 1L;
    private long userId;
    private long deviceId;
    private String type;
    private String fromDate;
    private String toDate;

    public MeasurementRequest() 
    {

    }

    public MeasurementRequest(long userId, long deviceId, String type, String fromDate, String toDate) 
    {
        this.userId = userId;
        this.deviceId = deviceId;
        this.type = type;
        this.fromDate = fromDate;
        this.toDate = toDate;
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

    public String getType() 
    {
        return this.type;
    }

    public void setType(String type) 
    {
        this.type = type;
    }

    public String getFromDate() 
    {
        return this.fromDate;
    }

    public void setFromDate(String fromDate) {

        this.fromDate = fromDate;
    }

    public String getToDate() 
    {
        return this.toDate;
    }

    public void setToDate(String toDate) 
    {
        this.toDate = toDate;
    }

}