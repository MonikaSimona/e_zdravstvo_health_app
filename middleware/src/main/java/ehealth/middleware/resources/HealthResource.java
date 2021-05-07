package ehealth.middleware.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ehealth.middleware.exceptions.InvalidDateFormatException;
import ehealth.middleware.models.DatabaseHealthData;
import ehealth.middleware.models.ErrorMessage;
import ehealth.middleware.models.HealthData;
import ehealth.middleware.services.DatabaseService;

@Path("/ehealth")
public class HealthResource 
{
    
    private DatabaseService service = new DatabaseService();

    @PUT
    @Path("/object")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DatabaseHealthData getDataViaObject(HealthData object) 
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
            Date start = sdf.parse(object.getTimeFrom());
            Date end = sdf.parse(object.getTimeTo());
            if(start.after(end))
            {
                ErrorMessage msg = new ErrorMessage("Not acceptable", 406, "The Start date is after the End date.");
                Response rsp = Response.status(Status.NOT_ACCEPTABLE).entity(msg).build();
                throw new NotAcceptableException(rsp);
            }
            DatabaseHealthData dbData = service.getDataFromDb(object.getParameterType(), object.getUserId(), start, end);
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
    public DatabaseHealthData getDataViaQuery(@QueryParam("type") String type, @QueryParam("userId") String userId, 
    @QueryParam("start") String start, @QueryParam("end") String end)
    {
        if((type == null) || (userId == null) || (start == null) || (end == null))
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
            DatabaseHealthData dbData = service.getDataFromDb(type, userId, startDate, endDate);
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
    @Path("/shortquery/{type}/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public DatabaseHealthData getDataViaShortQuery(@PathParam("type") String type, @PathParam("userId") String userId,
    @QueryParam("start") String start, @QueryParam("end") String end)
    {
        if((start == null) || (end == null))
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
            DatabaseHealthData dbData = service.getDataFromDb(type, userId, startDate, endDate);
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