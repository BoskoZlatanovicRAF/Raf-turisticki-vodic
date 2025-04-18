package services;

import entities.Destinacija;
import repositories.destinacija.DestinacijaRepository;

import javax.inject.Inject;
import java.util.List;

public class DestinacijaService {

    @Inject
    private DestinacijaRepository destinacijaRepository;

    public List<Destinacija> getAll() {
        return destinacijaRepository.findAll();
    }

    public Destinacija addDestinacija(Destinacija destinacija) {
        return destinacijaRepository.add(destinacija);
    }

    public Destinacija getDestinacija(String ime) {
        return destinacijaRepository.findByName(ime);
    }

    public Destinacija update(String ime, Destinacija destinacija) {
        return destinacijaRepository.update(ime, destinacija);
    }

    public boolean delete(String ime) {
        return destinacijaRepository.delete(ime);
    }

}
