package repositories.destinacija;

import entities.Destinacija;

import java.util.List;

public interface DestinacijaRepository {
    List<Destinacija> findAll();
    Destinacija add(Destinacija destinacija);
    Destinacija findByName(String ime);
    Destinacija update(String ime, Destinacija destinacija);
    boolean delete(String ime);
}
