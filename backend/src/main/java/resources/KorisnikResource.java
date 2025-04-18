package resources;


import entities.Korisnik;
import entities.enums.Status_Korisnika;
import entities.enums.Tip_Korisnika;
import services.KorisnikService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/korisnik")
public class KorisnikResource {

    @Inject
    private KorisnikService korisnikService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Map<String, String> credentials) {
        try {
            String email = credentials.get("email");
            String password = credentials.get("password");
            String token = korisnikService.login(email, password);

            if (token == null) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid email or password").build();
            }

            return Response.ok(token).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(Map<String, String> credentials) {
        try {
            String email = credentials.get("email");
            String password = credentials.get("password");
            String ime_prezime = credentials.get("ime_prezime");


            Tip_Korisnika tipKorisnikaEnum = Tip_Korisnika.valueOf("editor");
            Status_Korisnika statusKorisnikaEnum = Status_Korisnika.valueOf("active");

            korisnikService.register(email, password, ime_prezime, tipKorisnikaEnum, statusKorisnikaEnum);

            return Response.ok(email).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllKorisnici() {
        try {
            List<Korisnik> korisnici = korisnikService.getAllKorisnici();
            return Response.ok(korisnici).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateKorisnik(@PathParam("email") String email, Map<String, String> updateFields) {
        try {
            String ime_prezime = updateFields.get("ime_prezime");
            Tip_Korisnika tip_korisnika = Tip_Korisnika.valueOf(updateFields.get("tip_korisnika"));

            korisnikService.updateKorisnik(email, ime_prezime, tip_korisnika);

            return Response.ok(email).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/status/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStatus(@PathParam("email") String email, Map<String, String> updateFields) {
        try {
            Status_Korisnika status_korisnika = Status_Korisnika.valueOf(updateFields.get("status"));
            korisnikService.updateStatus(email, status_korisnika);

            return Response.ok(email).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteKorisnik(@PathParam("email") String email) {
        try {
            korisnikService.deleteKorisnik(email);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addKorisnik(Korisnik korisnik) {
        try {
            korisnikService.addKorisnik(korisnik);
            return Response.ok(korisnik.getEmail()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
