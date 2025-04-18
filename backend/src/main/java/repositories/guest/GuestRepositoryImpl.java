package repositories.guest;

import entities.Clanak;
import repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestRepositoryImpl extends MySqlAbstractRepository implements GuestRepository{
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
}
