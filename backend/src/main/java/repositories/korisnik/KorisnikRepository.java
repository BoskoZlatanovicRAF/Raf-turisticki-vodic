package repositories.korisnik;

import entities.Korisnik;

import java.util.List;

public interface KorisnikRepository {
    Korisnik findByUsername(String username);
    void save(Korisnik korisnik);
    List<Korisnik> findAll() throws Exception;
    void update(Korisnik korisnik) throws Exception;
    void delete(String email) throws Exception;
    Korisnik add(Korisnik korisnik);
}
