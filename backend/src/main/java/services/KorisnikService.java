package services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import entities.Korisnik;
import entities.enums.Status_Korisnika;
import entities.enums.Tip_Korisnika;
import org.apache.commons.codec.digest.DigestUtils;
import repositories.korisnik.KorisnikRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class KorisnikService {

    @Inject
    private KorisnikRepository korisnikRepository;

    public String login(String email, String password) {
        String hashedPassword = DigestUtils.sha256Hex(password);

        Korisnik korisnik = this.korisnikRepository.findByUsername(email);
        if (korisnik == null || !korisnik.getPassword().equals(hashedPassword) || korisnik.getStatus_korisnika() != Status_Korisnika.active){
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");

        // JWT-om mozete bezbedono poslati informacije na FE
        // Tako sto sve sto zelite da posaljete zapakujete u claims mapu

        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .withClaim("tip_korisnika", korisnik.getTip_korisnika().toString())
                .sign(algorithm);
    }

    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        String email = jwt.getSubject();

        Korisnik korisnik = this.korisnikRepository.findByUsername(email);

        return korisnik != null;

    }

    public void register(String email, String password, String ime_prezime, Tip_Korisnika tip_korisnika, Status_Korisnika status_korisnika) {
        String hashedPassword = DigestUtils.sha256Hex(password);

        Korisnik korisnik = new Korisnik();
        korisnik.setEmail(email);
        korisnik.setPassword(hashedPassword);
        korisnik.setIme_prezime(ime_prezime);
        korisnik.setTip_korisnika(tip_korisnika);
        korisnik.setStatus_korisnika(status_korisnika);

        korisnikRepository.save(korisnik);
    }


    public List<Korisnik> getAllKorisnici() throws Exception {
        return korisnikRepository.findAll();
    }


    public void updateKorisnik(String email, String ime_prezime, Tip_Korisnika tip_korisnika) throws Exception {
        Korisnik korisnik = korisnikRepository.findByUsername(email);
        if (korisnik != null) {
            korisnik.setIme_prezime(ime_prezime);
            korisnik.setTip_korisnika(tip_korisnika);
            korisnikRepository.update(korisnik);
        }
    }


    public void updateStatus(String email, Status_Korisnika status_korisnika) throws Exception {
        Korisnik korisnik = korisnikRepository.findByUsername(email);
        if (korisnik != null) {
            korisnik.setStatus_korisnika(status_korisnika);
            korisnikRepository.update(korisnik);
        }
    }


    public void deleteKorisnik(String email) throws Exception {
        korisnikRepository.delete(email);
    }

    public Korisnik addKorisnik(Korisnik korisnik) {
        String hashedPassword = DigestUtils.sha256Hex(korisnik.getPassword());
        korisnik.setPassword(hashedPassword);
        return korisnikRepository.add(korisnik);
    }


}
