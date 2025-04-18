package resources;

import entities.Clanak;
import services.ClanakService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/clanak")
public class ClanakResource {

    @Inject
    private ClanakService clanakService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(this.clanakService.getAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addClanak(Clanak clanak) {
        return Response.ok(this.clanakService.addClanak(clanak)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClanak(@PathParam("id") int id) {
        return Response.ok(this.clanakService.getClanak(id)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateClanak(@PathParam("id") int id, Clanak clanak) {
        return Response.ok(this.clanakService.updateClanak(id, clanak)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteClanak(@PathParam("id") int id) {
        this.clanakService.deleteClanak(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComments(@PathParam("id") int id) {
        return Response.ok(this.clanakService.getComments(id)).build();
    }


}
