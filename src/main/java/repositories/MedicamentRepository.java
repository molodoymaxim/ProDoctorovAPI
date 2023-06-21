package repositories;

import entities.Medicament;
import help.ConnectionBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicamentRepository {

    private static final Logger logger = Logger.getLogger(MedicamentRepository.class.getName());

    private final Connection connection;

    public MedicamentRepository(ConnectionBuilder connectionBuilder) {
        this.connection = connectionBuilder.getConnection();
    }

    public Medicament find(Long id) throws SQLException {
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection.prepareStatement("SELECT medicament_id, medicament_name, name_substance" +
                    "recomendation FROM medicament WHERE medicament_id = ?"));
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getMedicament(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, "Find manufacturer exception", e);
            throw new SQLException(e);
        }
        return null;
    }

    public void update(Medicament medicament) throws SQLException {
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection.prepareStatement("UPDATE medicament SET medicament_id = ?, medicament_name = ?, name_substance = ?, recomendation = ?" +
                    " WHERE medicament_id = ? "));
            preparedStatement.setLong(1, medicament.getId());
            preparedStatement.setString(2, medicament.getNameMedicament());
            preparedStatement.setString(3, medicament.getNameSubstance());
            preparedStatement.setString(4, medicament.getRecomendation());
            preparedStatement.setLong(5, medicament.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.INFO, "Update manufacturer exception", e);
            throw new SQLException(e);
        }
    }

    public void insert(Medicament medicament) throws SQLException {
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection.prepareStatement("INSERT INTO medicament (medicament_id, medicament_name, name_substance, recomendation)" +
                    "VALUES (?, ?, ?, ?)"));
            preparedStatement.setLong(1, medicament.getId());
            preparedStatement.setString(2, medicament.getNameMedicament());
            preparedStatement.setString(3, medicament.getNameSubstance() );
            preparedStatement.setString(4, medicament.getRecomendation());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //пробросить ошибку выше -> вывести в сервлете
            logger.log(Level.INFO, "Insert manufacturer exception", e);
            throw new SQLException(e);
        }
    }

    public void delete(Medicament medicament) throws SQLException {
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection.prepareStatement("DELETE FROM medicament WHERE medicament_id = ?"));
            preparedStatement.setLong(1, medicament.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.INFO, "Delete manufacturer exception", e);
            throw new SQLException(e);
        }
    }

    private static Medicament getMedicament(ResultSet resultSet) throws SQLException {
        return new Medicament(
                resultSet.getLong("medicament_id"),
                resultSet.getString("medicament_name"),
                resultSet.getString("name_substance"),
                resultSet.getString("recomendation")
        );
    }

    public Medicament findByName(String medicamentName) {
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection.prepareStatement("SELECT medicament_id, medicament_name, name_substance," +
                    "recomendation FROM medicament WHERE medicament_name = ?"));
            preparedStatement.setString(1, medicamentName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getMedicament(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, "Find by name manufacturer exception", e);
        }
        return null;
    }

}
