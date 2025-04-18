package resources;


import entities.Komentar;
import services.KomentarService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/komentar")
public class KomentarResource {

    @Inject
    private KomentarService komentarService;

    // Implementirati metode za CRUD operacije nad komentarima
    @GET
    @Path("/clanak/{clanak_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKomentarByClanakId(@PathParam("clanak_id") int clanakId){
        return Response.ok(this.komentarService.getKomentarByClanakId(clanakId)).build();
    }

    @POST
    @Path("/dodajKomentar/{clanak_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response dodajKomentar(@PathParam("clanak_id") int clanakId, Komentar komentar){
        return Response.ok(this.komentarService.dodajKomentar(clanakId, komentar)).build();
    }

    @PUT
    @Path("/poseta/{clanak_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePoseta(@PathParam("clanak_id") int clanakId) {
        return Response.ok(this.komentarService.updatePoseta(clanakId)).build();
    }
}
