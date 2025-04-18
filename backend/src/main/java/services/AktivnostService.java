package services;

import entities.Aktivnost;
import repositories.aktivnost.AktivnostRepository;

import javax.inject.Inject;
import java.util.List;

public class AktivnostService {

    @Inject
    private AktivnostRepository aktivnostRepository;

    public List<Aktivnost> getAll() {
        return aktivnostRepository.findAll();
    }

    public Aktivnost addAktivnost(Aktivnost aktivnost) {
        return aktivnostRepository.add(aktivnost);
    }

    public void deleteAktivnost(String naziv) {
        aktivnostRepository.delete(naziv);
    }
}
