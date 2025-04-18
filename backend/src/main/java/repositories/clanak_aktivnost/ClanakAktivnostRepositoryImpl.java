package repositories.clanak_aktivnost;

import entities.Clanak_Aktivnost;
import repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClanakAktivnostRepositoryImpl extends MySqlAbstractRepository implements ClanakAktivnostRepository{
    @Override
    public List<Clanak_Aktivnost> getAll() {
        String sql = "SELECT * FROM clanak_aktivnost";
        List<Clanak_Aktivnost> results = new ArrayList<>();
        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Clanak_Aktivnost ca = new Clanak_Aktivnost();
                ca.setClanak_id(resultSet.getInt("clanak_id"));
                ca.setAktivnost_naziv(resultSet.getString("aktivnost_naziv"));
                results.add(ca);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching ClanakAktivnost", e);
        }
        return results;
    }

    @Override
    public Clanak_Aktivnost add(Clanak_Aktivnost clanakAktivnost) {
        String sql = "INSERT INTO clanak_aktivnost (clanak_id, aktivnost_naziv) VALUES (?, ?)";
        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clanakAktivnost.getClanak_id());
            statement.setString(2, clanakAktivnost.getAktivnost_naziv());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding ClanakAktivnost", e);
        }
        return clanakAktivnost;
    }

    @Override
    public Clanak_Aktivnost update(int clanakId, String aktivnostNaziv, Clanak_Aktivnost clanakAktivnost) {
        String sql = "UPDATE clanak_aktivnost SET aktivnost_naziv = ? WHERE clanak_id = ? AND aktivnost_naziv = ?";
        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, clanakAktivnost.getAktivnost_naziv());
            statement.setInt(2, clanakId);
            statement.setString(3, aktivnostNaziv);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating ClanakAktivnost", e);
        }
        return clanakAktivnost;
    }

    @Override
    public void delete(int clanakId, String aktivnostNaziv) {
        String sql = "DELETE FROM clanak_aktivnost WHERE clanak_id = ? AND aktivnost_naziv = ?";
        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clanakId);
            statement.setString(2, aktivnostNaziv);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting ClanakAktivnost", e);
        }
    }

    @Override
    public List<Clanak_Aktivnost> findByClanakId(int clanakId) {
        String sql = "SELECT * FROM clanak_aktivnost WHERE clanak_id = ?";
        List<Clanak_Aktivnost> results = new ArrayList<>();
        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clanakId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Clanak_Aktivnost ca = new Clanak_Aktivnost();
                    ca.setClanak_id(resultSet.getInt("clanak_id"));
                    ca.setAktivnost_naziv(resultSet.getString("aktivnost_naziv"));
                    results.add(ca);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching ClanakAktivnost by clanakId", e);
        }
        return results;
    }

    @Override
    public void deleteByClanakId(int clanakId) {
        String sql = "DELETE FROM clanak_aktivnost WHERE clanak_id = ?";
        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clanakId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting ClanakAktivnost by ClanakId", e);
        }
    }
}
