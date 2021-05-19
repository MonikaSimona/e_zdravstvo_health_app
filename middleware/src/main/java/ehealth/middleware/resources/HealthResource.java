package ehealth.middleware.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ehealth.middleware.exceptions.InvalidDateFormatException;
import ehealth.middleware.models.ErrorMessage;
import ehealth.middleware.models.Measurement;
import ehealth.middleware.models.MeasurementRequest;
import ehealth.middleware.services.DatabaseService;

@Path("/ehealth")
public class HealthResource 
{
    
    private DatabaseService service = new DatabaseService();

    @PUT
    @Path("/object")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Measurement> getDataViaObject(MeasurementRequest object) 
    {
        if (object.equals(null)) 
        {
            ErrorMessage msg = new ErrorMessage("Not acceptable", 406, "Please provide an input object.");
            Response rsp = Response.status(Status.NOT_ACCEPTABLE).entity(msg).build();
            throw new NotAcceptableException(rsp);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a dd-MM-yyyy");
        try 
        {
            Date start = sdf.parse(object.getFromDate());
            Date end = sdf.parse(object.getToDate());
            if(start.after(end))
            {
                ErrorMessage msg = new ErrorMessage("Not acceptable", 406, "The Start date is after the End date.");
                Response rsp = Response.status(Status.NOT_ACCEPTABLE).entity(msg).build();
                throw new NotAcceptableException(rsp);
            }
            List<Measurement> dbData = service.getMeasurements(object);
            if(dbData == null)
            {
                ErrorMessage msg = new ErrorMessage("Not found", 404, "The input does not match with any database records.");
                Response rsp = Response.status(Status.NOT_FOUND).entity(msg).build();
                throw new NotFoundException(rsp);
            }
            else
            {
                return dbData;
            }
        } 
        catch (ParseException e) 
        {
            ErrorMessage msg = new ErrorMessage("Not acceptable", 406, "Please provide a Start and an End date in a valid format of: hh:mm:ss a dd-MM-yyyy.");
            throw new InvalidDateFormatException(msg);
        }
    }

    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Measurement> getDataViaQuery(@QueryParam("userId") long userId, @QueryParam("deviceId") long deviceId,
    @QueryParam("type") String type, @QueryParam("start") String start, @QueryParam("end") String end)
    {
        if((type == null) || (userId == 0l) || (start == null) || (end == null) || (deviceId == 0l))
        {
            ErrorMessage msg = new ErrorMessage("Not found", 404, "Please provide the required query input parameters.");
            Response rsp = Response.status(Status.NOT_FOUND).entity(msg).build();
            throw new NotFoundException(rsp);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a dd-MM-yyyy");
        try 
        {
            Date startDate = sdf.parse(start);
            Date endDate = sdf.parse(end);
            if(startDate.after(endDate))
            {
                ErrorMessage msg = new ErrorMessage("Not acceptable", 406, "The Start date is after the End date.");
                Response rsp = Response.status(Status.NOT_ACCEPTABLE).entity(msg).build();
                throw new NotAcceptableException(rsp);
            }
            MeasurementRequest request = new MeasurementRequest(userId, deviceId, type, start, end);
            List<Measurement> dbData = service.getMeasurements(request);
            if(dbData == null)
            {
                ErrorMessage msg = new ErrorMessage("Not found", 404, "The input does not match with any database records.");
                Response rsp = Response.status(Status.NOT_FOUND).entity(msg).build();
                throw new NotFoundException(rsp);
            }
            else
            {
                return dbData;
            }
        } 
        catch (ParseException e) 
        {
            ErrorMessage msg = new ErrorMessage("Not acceptable", 406, "Please provide a Start and an End date in a valid format of: hh:mm:ss a dd-MM-yyyy.");
            throw new InvalidDateFormatException(msg);
        }
    }
    
}