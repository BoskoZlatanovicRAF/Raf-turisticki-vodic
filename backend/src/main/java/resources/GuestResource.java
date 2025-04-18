package resources;

import entities.Aktivnost;
import entities.Clanak;
import entities.Komentar;
import services.AktivnostService;
import services.ClanakAktivnostService;
import services.ClanakService;
import services.KomentarService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/guest")
public class GuestResource {

    @Inject
    private ClanakService clanakService;

    @Inject
    private KomentarService komentarService;

    @Inject
    private ClanakAktivnostService clanakAktivnostService;

    @GET
    @Path("/clanak/{clanak_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClanak(@PathParam("clanak_id") int id) {
        try {
            return Response.ok(this.clanakService.getClanak(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/clanci")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllClanci() {
        try {
            List<Clanak> clanci = clanakService.getAll();
            return Response.ok(clanci).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/clanakAktivnost/{clanak_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByClanakId(@PathParam("clanak_id") int clanakId) {
        return Response.ok(clanakAktivnostService.findByClanakId(clanakId)).build();
    }

    @GET
    @Path("/clanci/najnoviji")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNajnovijiClanci() {
        try {
            List<Clanak> clanci = clanakService.findNajnoviji();
            return Response.ok(clanci).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/clanci/najcitaniji")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNajcitanijiClanci() {
        try {
            List<Clanak> clanci = clanakService.findNajcitaniji();
            return Response.ok(clanci).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/destinacija/{ime}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClanciByDestinacija(@PathParam("ime") String ime) {
        try {
            List<Clanak> clanci = clanakService.findByDestinacija(ime);
            return Response.ok(clanci).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/komentar/{clanak_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response dodajKomentar(@PathParam("clanak_id") int clanakId, Komentar komentar) {
        try {
            komentarService.dodajKomentar(clanakId, komentar);
            return Response.ok(komentar).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
