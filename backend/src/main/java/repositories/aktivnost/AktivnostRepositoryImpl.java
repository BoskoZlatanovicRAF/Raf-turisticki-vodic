package repositories.aktivnost;

import entities.Aktivnost;
import repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AktivnostRepositoryImpl extends MySqlAbstractRepository implements AktivnostRepository{
    @Override
    public List<Aktivnost> findAll() {
        List<Aktivnost> aktivnosti = new ArrayList<>();
        String sql = "SELECT * FROM aktivnost";

        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Aktivnost aktivnost = new Aktivnost();
                aktivnost.setNaziv(resultSet.getString("naziv"));
                aktivnosti.add(aktivnost);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching aktivnosti", e);
        }

        return aktivnosti;
    }

    @Override
    public Aktivnost add(Aktivnost aktivnost) {
        String sql = "INSERT INTO aktivnost (naziv) VALUES (?)";

        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, aktivnost.getNaziv());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding aktivnost", e);
        }

        return aktivnost;
    }

    @Override
    public void delete(String naziv) {
        String sql = "DELETE FROM aktivnost WHERE naziv = ?";

        try (Connection connection = this.newConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, naziv);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting aktivnost", e);
        }
    }
}
