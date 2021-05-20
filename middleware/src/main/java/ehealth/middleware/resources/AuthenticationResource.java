package ehealth.middleware.resources;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import ehealth.middleware.exceptions.InvalidDateFormatException;
import ehealth.middleware.models.DeviceData;
import ehealth.middleware.models.ErrorMessage;
import ehealth.middleware.models.FullUser;
import ehealth.middleware.models.LoginData;
import ehealth.middleware.models.LoginUser;
import ehealth.middleware.models.User;
import ehealth.middleware.models.UserDevice;
import ehealth.middleware.services.AuthenticationService;

@Path("/auth")
public class AuthenticationResource 
{
    
    private AuthenticationService service = new AuthenticationService(); 

    @PUT
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(FullUser user, @Context UriInfo uriInfo)
    {
        if(user.equals(null))
        {
            ErrorMessage msg = new ErrorMessage("Not acceptable", 406, "Please provide an input object with credentials.");
            Response rsp = Response.status(Status.NOT_ACCEPTABLE).header("Access-Control-Allow-Origin", "*")
            .entity(msg).build();
            throw new NotAcceptableException(rsp);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            Date birth = null;
            birth = sdf.parse(user.getBirthDate());
            if(birth == null)
            {
                ErrorMessage msg = new ErrorMessage("Not acceptable", 406, "Please provide a birth date in a valid format of: dd/MM/yyyy.");
                throw new InvalidDateFormatException(msg);
            }
            User registered = service.register(user);
            if(registered == null)
            {
                return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*")
                .entity("Error while registering a new account, the MBR: " + 
                user.getMbr() + ", Email: " + user.getEmail() + " or Username: " + user.getUsername() + " is already taken.").build();
            }
            else
            {
                URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(registered.getId())).build();
                return Response.created(uri).header("Access-Control-Allow-Origin", "*")
                .build();
            }
        }
        catch (ParseException e) 
        {
            ErrorMessage msg = new ErrorMessage("Not acceptable", 406, "Please provide a birth date in a valid format of: dd-MM-yyyy.");
            throw new InvalidDateFormatException(msg);
        }
    }

    @PUT
    @Path("/addDevice")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDevice(DeviceData device)
    {
        if(device.equals(null))
        {
            ErrorMessage msg = new ErrorMessage("Not acceptable", 406, "Please provide an input object with credentials.");
            Response rsp = Response.status(Status.NOT_ACCEPTABLE).header("Access-Control-Allow-Origin", "*")
            .entity(msg).build();
            throw new NotAcceptableException(rsp);
        }
        UserDevice dvc = service.addDevice(device);
        if(dvc == null)
        {
            ErrorMessage msg = new ErrorMessage("Not found", 404, "Either an incorrect user ID is provided" +
            " or the device's name: " + device.getName() + " is already in use by this user.");
            Response rsp = Response.status(Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*")
            .entity(msg).build();
            throw new NotFoundException(rsp);
        }
        else
        {
            return Response.status(Status.OK).header("Access-Control-Allow-Origin", "*")
            .entity(dvc).build();
        }
    }

    @PUT
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(LoginUser user)
    {
        if(user.equals(null))
        {
            ErrorMessage msg = new ErrorMessage("Not acceptable", 406, "Please provide an input object with credentials.");
            Response rsp = Response.status(Status.NOT_ACCEPTABLE).header("Access-Control-Allow-Origin", "*")
            .entity(msg).build();
            throw new NotAcceptableException(rsp);
        }
        LoginData logged = service.login(user);
        if(logged == null)
        {
            ErrorMessage msg = new ErrorMessage("Not found", 404, "Incorrect username and/or password.");
            Response rsp = Response.status(Status.NOT_FOUND).header("Access-Control-Allow-Origin", "*")
            .entity(msg).build();
            throw new NotFoundException(rsp);
        }
        else
        {
            return Response.status(Status.OK).header("Access-Control-Allow-Origin", "*")
            .entity(logged).build();
        }
    }

}