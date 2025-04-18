package repositories.clanak;

import entities.Clanak;
import entities.Komentar;

import java.util.List;

public interface ClanakRepository {
    List<Clanak> findAll();
    Clanak add(Clanak clanak);
    Clanak findById(int id);
    Clanak update(int id, Clanak clanak);
    void delete(int id);
    List<Komentar> findCommentsById(int id);


    List<Clanak> findNajnoviji();
    List<Clanak> findNajcitaniji();
    List<Clanak> findByDestinacija(String destinacija);
}
