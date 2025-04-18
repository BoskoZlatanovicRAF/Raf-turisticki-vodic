package resources;

import entities.Aktivnost;
import services.AktivnostService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/aktivnost")
public class AktivnostResource {

    @Inject
    private AktivnostService aktivnostService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(this.aktivnostService.getAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAktivnost(Aktivnost aktivnost) {
        return Response.ok(this.aktivnostService.addAktivnost(aktivnost)).build();
    }

    @DELETE
    @Path("/{naziv}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAktivnost(@PathParam("naziv") String naziv) {
        this.aktivnostService.deleteAktivnost(naziv);
        return Response.noContent().build();
    }
}
