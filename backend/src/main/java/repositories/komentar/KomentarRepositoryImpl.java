package repositories.komentar;

import entities.Komentar;
import repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KomentarRepositoryImpl extends MySqlAbstractRepository implements KomentarRepository{
    @Override
    public List<Komentar> findByClanakId(int clanakId) {
        String sql = "SELECT * FROM komentar WHERE clanak_id = ?";
        List<Komentar> komentari = new ArrayList<>();
        try(Connection connection = this.newConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clanakId);
            try(ResultSet resultSet = statement.executeQuery()) {
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

        }catch (Exception e) {
            throw new RuntimeException("Error fetching komentari", e);
        }

        return komentari;
    }

    @Override
    public Komentar add(int clanakId, Komentar komentar) {
        String sql = "INSERT INTO komentar (autor_komentara, tekst, datum, clanak_id) VALUES (?, ?, ?, ?)";
        try(Connection connection = this.newConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, komentar.getAutor_komentara());
            statement.setString(2, komentar.getTekst());
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            statement.setInt(4, clanakId);
            statement.executeUpdate();
        }catch (Exception e) {
            throw new RuntimeException("Error adding komentar", e);
        }

        return komentar;
    }

    @Override
    public int updatePoseta(int clanakId) {
        String sql = "UPDATE clanak SET broj_poseta = broj_poseta + 1 WHERE id = ?";

        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, clanakId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating clanak poseta", e);
        }

        return clanakId+1;
    }
}
