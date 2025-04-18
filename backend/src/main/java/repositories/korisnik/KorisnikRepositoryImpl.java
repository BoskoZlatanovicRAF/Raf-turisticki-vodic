package repositories.korisnik;

import entities.Korisnik;
import entities.enums.Status_Korisnika;
import entities.enums.Tip_Korisnika;
import repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KorisnikRepositoryImpl extends MySqlAbstractRepository implements KorisnikRepository{
    @Override
    public Korisnik findByUsername(String email) {
        String sql = "SELECT * FROM korisnik WHERE email = ?";

        try (Connection connection = newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {

                    Korisnik korisnik = new Korisnik();
                    korisnik.setEmail(resultSet.getString("email"));
                    korisnik.setPassword(resultSet.getString("password"));
                    korisnik.setIme_prezime(resultSet.getString("ime_prezime"));
                    korisnik.setTip_korisnika(Tip_Korisnika.valueOf(resultSet.getString("tip_korisnika")));
                    korisnik.setStatus_korisnika(Status_Korisnika.valueOf(resultSet.getString("status_korisnika")));

                    return korisnik;
                }
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding user by username", e);
        }
    }

    @Override
    public void save(Korisnik korisnik) {
        String sql = "INSERT INTO korisnik (email, password, ime_prezime, tip_korisnika, status_korisnika) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, korisnik.getEmail());
            statement.setString(2, korisnik.getPassword());
            statement.setString(3, korisnik.getIme_prezime());
            statement.setString(4, korisnik.getTip_korisnika().toString());
            statement.setString(5, korisnik.getStatus_korisnika().toString());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving user", e);
        }

    }

    @Override
    public List<Korisnik> findAll() throws Exception {
        String sql = "SELECT * FROM korisnik";
        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Korisnik> korisnici = new ArrayList<>();
            while (resultSet.next()) {
                korisnici.add(new Korisnik(
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("ime_prezime"),
                        Tip_Korisnika.valueOf(resultSet.getString("tip_korisnika")),
                        Status_Korisnika.valueOf(resultSet.getString("status_korisnika"))
                ));
            }
            return korisnici;
        }
    }

    @Override
    public void update(Korisnik korisnik) throws Exception {
        String sql = "UPDATE korisnik SET ime_prezime = ?, tip_korisnika = ?, status_korisnika = ? WHERE email = ?";
        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, korisnik.getIme_prezime());
            statement.setString(2, korisnik.getTip_korisnika().toString());
            statement.setString(3, korisnik.getStatus_korisnika().toString());
            statement.setString(4, korisnik.getEmail());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(String email) throws Exception {
        String sql = "DELETE FROM korisnik WHERE email = ?";
        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.executeUpdate();
        }
    }

    @Override
    public Korisnik add(Korisnik korisnik) {
        String sql = "INSERT INTO korisnik (email, password, ime_prezime, tip_korisnika, status_korisnika) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, korisnik.getEmail());
            statement.setString(2, korisnik.getPassword());
            statement.setString(3, korisnik.getIme_prezime());
            statement.setString(4, korisnik.getTip_korisnika().toString());
            statement.setString(5, Status_Korisnika.active.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding Korisnik", e);
        }
        return korisnik;
    }
}
