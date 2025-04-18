package repositories.clanak;

import entities.Clanak;
import entities.Komentar;
import repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClanakRepositoryImpl extends MySqlAbstractRepository implements ClanakRepository{
    @Override
    public List<Clanak> findAll() {
        List<Clanak> clanci = new ArrayList<>();
        String sql = "SELECT * FROM clanak";

        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Clanak clanak = new Clanak();
                clanak.setId(resultSet.getInt("id"));
                clanak.setNaslov(resultSet.getString("naslov"));
                clanak.setTekst(resultSet.getString("tekst"));
                clanak.setBroj_poseta(resultSet.getInt("broj_poseta"));
                clanak.setAutor_email(resultSet.getString("autor_email"));
                clanak.setDestinacija_ime(resultSet.getString("destinacija_ime"));
                clanak.setDatum(resultSet.getTimestamp("datum"));
                clanci.add(clanak);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching clanci", e);
        }

        return clanci;
    }

    @Override
    public Clanak add(Clanak clanak) {
        String sql = "INSERT INTO clanak (naslov, tekst, broj_poseta, autor_email, destinacija_ime, datum) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, clanak.getNaslov());
            statement.setString(2, clanak.getTekst());
            statement.setInt(3, 0);
            statement.setString(4, clanak.getAutor_email());
            statement.setString(5, clanak.getDestinacija_ime());
            statement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    clanak.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error adding clanak", e);
        }

        return clanak;
    }

    @Override
    public Clanak findById(int id) {
        String sql = "SELECT * FROM clanak WHERE id = ?";
        Clanak clanak = null;

        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    clanak = new Clanak();
                    clanak.setId(resultSet.getInt("id"));
                    clanak.setNaslov(resultSet.getString("naslov"));
                    clanak.setTekst(resultSet.getString("tekst"));
                    clanak.setBroj_poseta(resultSet.getInt("broj_poseta"));
                    clanak.setAutor_email(resultSet.getString("autor_email"));
                    clanak.setDestinacija_ime(resultSet.getString("destinacija_ime"));
                    clanak.setDatum(resultSet.getTimestamp("datum"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding clanak by id", e);
        }

        return clanak;
    }

    @Override
    public Clanak update(int id, Clanak clanak) {
        String sql = "UPDATE clanak SET naslov = ?, tekst = ?, destinacija_ime = ? WHERE id = ?";

        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, clanak.getNaslov());
            statement.setString(2, clanak.getTekst());
//            statement.setInt(3, clanak.getBroj_poseta());
//            statement.setString(3, clanak.getAutor_email());
            statement.setString(3, clanak.getDestinacija_ime());
//            statement.setTimestamp(5, new Timestamp(clanak.getDatum().getTime()));
            statement.setInt(4, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating clanak", e);
        }

        return clanak;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM clanak WHERE id = ?";

        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting clanak", e);
        }
    }

    @Override
    public List<Komentar> findCommentsById(int id) {
        List<Komentar> komentari = new ArrayList<>();
        String sql = "SELECT * FROM komentar WHERE clanak_id = ?";

        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Komentar komentar = new Komentar();
                    komentar.setId(resultSet.getInt("id"));
                    komentar.setAutor_komentara(resultSet.getString("autor_komentara"));
                    komentar.setTekst(resultSet.getString("tekst"));
                    komentar.setDatum(resultSet.getTimestamp("datum"));
                    komentar.setClanak_id(resultSet.getInt("clanak_id"));
                    komentari.add(komentar);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching komentari for clanak id " + id, e);
        }

        return komentari;
    }

    @Override
    public List<Clanak> findNajnoviji() {
        String sql = "SELECT * FROM clanak ORDER BY datum DESC LIMIT 10";
        List<Clanak> clanci = new ArrayList<>();
        try(Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Clanak clanak = new Clanak();
                clanak.setId(resultSet.getInt("id"));
                clanak.setNaslov(resultSet.getString("naslov"));
                clanak.setTekst(resultSet.getString("tekst"));
                clanak.setBroj_poseta(resultSet.getInt("broj_poseta"));
                clanak.setAutor_email(resultSet.getString("autor_email"));
                clanak.setDestinacija_ime(resultSet.getString("destinacija_ime"));
                clanak.setDatum(resultSet.getTimestamp("datum"));
                clanci.add(clanak);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching clanci", e);
        }
        return clanci;

    }

    @Override
    public List<Clanak> findNajcitaniji() {
        String sql = "SELECT * FROM clanak ORDER BY broj_poseta DESC LIMIT 10";
        List<Clanak> clanci = new ArrayList<>();
        try(Connection connection = this.newConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Clanak clanak = new Clanak();
                clanak.setId(resultSet.getInt("id"));
                clanak.setNaslov(resultSet.getString("naslov"));
                clanak.setTekst(resultSet.getString("tekst"));
                clanak.setBroj_poseta(resultSet.getInt("broj_poseta"));
                clanak.setAutor_email(resultSet.getString("autor_email"));
                clanak.setDestinacija_ime(resultSet.getString("destinacija_ime"));
                clanak.setDatum(resultSet.getTimestamp("datum"));
                clanci.add(clanak);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching clanci", e);
        }
        return clanci;
    }

    @Override
    public List<Clanak> findByDestinacija(String destinacija) {
        String sql = "SELECT * FROM clanak WHERE destinacija_ime = ?";
        List<Clanak> clanci = new ArrayList<>();
        try(Connection connection = this.newConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, destinacija);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Clanak clanak = new Clanak();
                    clanak.setId(resultSet.getInt("id"));
                    clanak.setNaslov(resultSet.getString("naslov"));
                    clanak.setTekst(resultSet.getString("tekst"));
                    clanak.setBroj_poseta(resultSet.getInt("broj_poseta"));
                    clanak.setAutor_email(resultSet.getString("autor_email"));
                    clanak.setDestinacija_ime(resultSet.getString("destinacija_ime"));
                    clanak.setDatum(resultSet.getTimestamp("datum"));
                    clanci.add(clanak);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching clanci", e);
        }
        return clanci;
    }


}
