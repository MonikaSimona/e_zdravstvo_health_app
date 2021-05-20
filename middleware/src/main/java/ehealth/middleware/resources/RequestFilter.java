package ehealth.middleware.resources;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class RequestFilter implements ContainerRequestFilter 
{

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException 
    {
        requestContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        requestContext.getHeaders().add(
        "Access-Control-Allow-Methods", 
        "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        requestContext.getHeaders().add(
        "Access-Control-Allow-Headers",
        "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        requestContext.getHeaders().add(
        "Access-Control-Allow-Credentials", "true");
    }
    
}