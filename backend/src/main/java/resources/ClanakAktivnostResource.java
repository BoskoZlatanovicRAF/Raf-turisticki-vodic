package resources;

import entities.Clanak_Aktivnost;
import services.ClanakAktivnostService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/clanak-aktivnost")
public class ClanakAktivnostResource {
    @Inject
    private ClanakAktivnostService clanakAktivnostService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok(clanakAktivnostService.getAll()).build();
    }

    // sve aktivnosti za clanak
    @GET
    @Path("/clanak/{clanakId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByClanakId(@PathParam("clanakId") int clanakId) {
        return Response.ok(clanakAktivnostService.findByClanakId(clanakId)).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Clanak_Aktivnost clanakAktivnost) {
        return Response.ok(clanakAktivnostService.add(clanakAktivnost)).build();
    }

    @PUT
    @Path("/{clanakId}/{aktivnostNaziv}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("clanakId") int clanakId, @PathParam("aktivnostNaziv") String aktivnostNaziv, Clanak_Aktivnost clanakAktivnost) {
        return Response.ok(clanakAktivnostService.update(clanakId, aktivnostNaziv, clanakAktivnost)).build();
    }

    @DELETE
    @Path("/clanak/{clanakId}")
    public Response deleteByClanakId(@PathParam("clanakId") int clanakId) {
        clanakAktivnostService.deleteByClanakId(clanakId);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{clanakId}/{aktivnostNaziv}")
    public Response delete(@PathParam("clanakId") int clanakId, @PathParam("aktivnostNaziv") String aktivnostNaziv) {
        clanakAktivnostService.delete(clanakId, aktivnostNaziv);
        return Response.noContent().build();
    }
}
