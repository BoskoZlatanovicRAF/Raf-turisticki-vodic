package services;

import entities.Komentar;
import repositories.komentar.KomentarRepository;

import javax.inject.Inject;
import java.util.List;

public class KomentarService {
    @Inject
    private KomentarRepository komentarRepository;

    public List<Komentar> getKomentarByClanakId(int clanakId) {
        return komentarRepository.findByClanakId(clanakId);
    }

    public Komentar dodajKomentar(int clanakId, Komentar komentar) {
        return komentarRepository.add(clanakId, komentar);
    }

    public int updatePoseta(int clanakId) {
        return komentarRepository.updatePoseta(clanakId);
    }
}
