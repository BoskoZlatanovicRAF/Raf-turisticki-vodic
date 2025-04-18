package repositories.destinacija;

import entities.Destinacija;
import repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DestinacijaRepositoryImpl extends MySqlAbstractRepository implements DestinacijaRepository{
    @Override
    public List<Destinacija> findAll() {
        List<Destinacija> destinacije = new ArrayList<>();
        String sql = "SELECT * FROM destinacija";

        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Destinacija destinacija = new Destinacija();
                destinacija.setIme(resultSet.getString("ime"));
                destinacija.setOpis(resultSet.getString("opis"));
                destinacije.add(destinacija);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching destinacije", e);
        }

        return destinacije;
    }

    @Override
    public Destinacija add(Destinacija destinacija) {
        String sql = "INSERT INTO destinacija (ime, opis) VALUES (?, ?)";

        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, destinacija.getIme());
            statement.setString(2, destinacija.getOpis());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding destinacija", e);
        }

        return destinacija;
    }

    @Override
    public Destinacija findByName(String ime) {
        String sql = "SELECT * FROM destinacija WHERE ime = ?";
        Destinacija destinacija = null;

        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, ime);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    destinacija = new Destinacija();
                    destinacija.setIme(resultSet.getString("ime"));
                    destinacija.setOpis(resultSet.getString("opis"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding destinacija by ime", e);
        }

        return destinacija;
    }

    @Override
    public Destinacija update(String ime, Destinacija destinacija) {
        String sql = "UPDATE destinacija SET ime = ?, opis = ? WHERE ime = ?";

        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, destinacija.getIme());
            statement.setString(2, destinacija.getOpis());
            statement.setString(3, ime);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating destinacija", e);
        }

        return destinacija;
    }

    @Override
    public boolean delete(String ime) {
        // Proveri da li postoje članci povezani sa destinacijom
        String checkSql = "SELECT COUNT(*) FROM clanak WHERE destinacija_ime = ?";
        try (Connection connection = this.newConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
            checkStatement.setString(1, ime);
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking related articles", e);
        }

        String sql = "DELETE FROM destinacija WHERE ime = ?";
        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ime);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting destinacija", e);
        }

        return true;

    }
}
