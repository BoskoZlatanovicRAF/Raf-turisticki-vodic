package repositories.guest;

import entities.Clanak;

import java.util.List;

public interface GuestRepository {
    List<Clanak> findAll();
}
