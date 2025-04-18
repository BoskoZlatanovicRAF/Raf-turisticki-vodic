package services;

import entities.Clanak_Aktivnost;
import repositories.clanak_aktivnost.ClanakAktivnostRepository;

import javax.inject.Inject;
import java.util.List;

public class ClanakAktivnostService {
    @Inject
    private ClanakAktivnostRepository clanakAktivnostRepository;

    public List<Clanak_Aktivnost> getAll() {
        return clanakAktivnostRepository.getAll();
    }

    public Clanak_Aktivnost add(Clanak_Aktivnost clanakAktivnost) {
        return clanakAktivnostRepository.add(clanakAktivnost);
    }

    public Clanak_Aktivnost update(int clanakId, String aktivnostNaziv, Clanak_Aktivnost clanakAktivnost) {
        return clanakAktivnostRepository.update(clanakId, aktivnostNaziv, clanakAktivnost);
    }

    public void delete(int clanakId, String aktivnostNaziv) {
        clanakAktivnostRepository.delete(clanakId, aktivnostNaziv);
    }

    public void deleteByClanakId(int clanakId) {
        clanakAktivnostRepository.deleteByClanakId(clanakId);
    }

    public List<Clanak_Aktivnost> findByClanakId(int clanakId) {
        return clanakAktivnostRepository.findByClanakId(clanakId);
    }
}
