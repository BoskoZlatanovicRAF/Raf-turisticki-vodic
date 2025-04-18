package services;

import entities.Clanak;
import entities.Komentar;
import repositories.clanak.ClanakRepository;

import javax.inject.Inject;
import java.util.List;

public class ClanakService {

    @Inject
    private ClanakRepository clanakRepository;

    public List<Clanak> getAll() {
        return clanakRepository.findAll();
    }

    public Clanak addClanak(Clanak clanak) {
        return clanakRepository.add(clanak);
    }

    public Clanak getClanak(int id) {
        return clanakRepository.findById(id);
    }

    public Clanak updateClanak(int id, Clanak clanak) {
        return clanakRepository.update(id, clanak);
    }

    public void deleteClanak(int id) {
        clanakRepository.delete(id);
    }

    public List<Komentar> getComments(int id) {
        return clanakRepository.findCommentsById(id);
    }

    public List<Clanak> findNajnoviji() {
        return clanakRepository.findNajnoviji();
    }

    public List<Clanak> findNajcitaniji() {
        return clanakRepository.findNajcitaniji();
    }

    public List<Clanak> findByDestinacija(String destinacija) {
        return clanakRepository.findByDestinacija(destinacija);
    }


}
