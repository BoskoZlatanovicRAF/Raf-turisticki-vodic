package repositories.clanak_aktivnost;

import entities.Clanak_Aktivnost;

import java.util.List;

public interface ClanakAktivnostRepository {
    List<Clanak_Aktivnost> getAll();
    Clanak_Aktivnost add(Clanak_Aktivnost clanakAktivnost);
    Clanak_Aktivnost update(int clanakId, String aktivnostNaziv, Clanak_Aktivnost clanakAktivnost);
    void delete(int clanakId, String aktivnostNaziv);
    List<Clanak_Aktivnost> findByClanakId(int clanakId);

    void deleteByClanakId(int clanakId);
}
