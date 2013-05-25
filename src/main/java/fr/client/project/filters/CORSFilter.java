package fr.client.project.filters;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

public class CORSFilter implements ContainerResponseFilter {
    @Override
    public ContainerResponse filter(ContainerRequest req, ContainerResponse resp) {
        resp.getHttpHeaders().add("Access-Control-Allow-Origin","*");
        resp.getHttpHeaders().add("Access-Control-Allow-Headers","origin, content-type, accept, authorization");
        resp.getHttpHeaders().add("Access-Control-Allow-Credentials","true");
        resp.getHttpHeaders().add("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,OPTIONS,HEAD");
        resp.getHttpHeaders().add("Access-Control-Max-Age","1209600");
        return resp;
    }
}
