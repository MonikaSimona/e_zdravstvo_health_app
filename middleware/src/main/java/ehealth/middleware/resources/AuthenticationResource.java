package ehealth.middleware.resources;

import java.net.URI;

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

import ehealth.middleware.models.DatabaseUser;
import ehealth.middleware.models.ErrorMessage;
import ehealth.middleware.models.User;
import ehealth.middleware.services.AuthenticationService;

@Path("/auth")
public class AuthenticationResource 
{
    
    private AuthenticationService service = new AuthenticationService();

    @PUT
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User user, @Context UriInfo uriInfo)
    {
        if(user.equals(null))
        {
            ErrorMessage msg = new ErrorMessage("Not acceptable", 406, "Please provide an input object with credentials.");
            Response rsp = Response.status(Status.NOT_ACCEPTABLE).entity(msg).build();
            throw new NotAcceptableException(rsp);
        }
        DatabaseUser registered = service.register(user.getUsername(), user.getPassword());
        if(registered == null)
        {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error while registering a new account, username: " + 
            user.getUsername() + " is already taken.").build();
        }
        else
        {
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(registered.getUserId())).build();
            return Response.created(uri).build();
        }
    }

    @PUT
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DatabaseUser loginUser(User user)
    {
        if(user.equals(null))
        {
            ErrorMessage msg = new ErrorMessage("Not acceptable", 406, "Please provide an input object with credentials.");
            Response rsp = Response.status(Status.NOT_ACCEPTABLE).entity(msg).build();
            throw new NotAcceptableException(rsp);
        }
        DatabaseUser logged = service.login(user.getUsername(), user.getPassword());
        if(logged == null)
        {
            ErrorMessage msg = new ErrorMessage("Not found", 404, "The user with username=" + user.getUsername() + " does not exist.");
            Response rsp = Response.status(Status.NOT_FOUND).entity(msg).build();
            throw new NotFoundException(rsp);
        }
        else
        {
            return logged;
        }
    }

}