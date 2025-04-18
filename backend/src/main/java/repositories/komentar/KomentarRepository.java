package repositories.komentar;

import entities.Komentar;

import java.util.List;

public interface KomentarRepository {
    List<Komentar> findByClanakId(int clanakId);
    Komentar add(int clanakId, Komentar komentar);
    int updatePoseta(int clanakId);
}
