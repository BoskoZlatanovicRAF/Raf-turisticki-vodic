package repositories.aktivnost;

import entities.Aktivnost;

import java.util.List;

public interface AktivnostRepository {
    List<Aktivnost> findAll();
    Aktivnost add(Aktivnost aktivnost);
    void delete(String naziv);
}
