package resources;


import entities.Destinacija;
import services.DestinacijaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/destinacija")
public class DestinacijaResource {

    @Inject
    private DestinacijaService destinacijaService;

    // Implementirati metode za CRUD operacije nad destinacijama
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.ok(this.destinacijaService.getAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDestinacija(Destinacija destinacija) {
        return Response.ok(this.destinacijaService.addDestinacija(destinacija)).build();
    }

    @GET
    @Path("/{ime}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDestinacija(@PathParam("ime") String ime) {
        return Response.ok(this.destinacijaService.getDestinacija(ime)).build();
    }

    @PUT
    @Path("/{ime}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDestinacija(@PathParam("ime") String ime, Destinacija destinacija) {
        Destinacija updatedDestinacija = this.destinacijaService.update(ime, destinacija);
        return Response.ok(updatedDestinacija).build();
    }

    @DELETE
    @Path("/{ime}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDestinacija(@PathParam("ime") String ime) {
        boolean isDeleted = this.destinacijaService.delete(ime);
        if (isDeleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Ne možete obrisati destinaciju dok postoje članci povezani sa njom.")
                    .build();
        }
    }
}
