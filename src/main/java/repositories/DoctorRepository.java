package repositories;

import entities.Doctor;
import help.ConnectionBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorRepository {

    private static final Logger logger = Logger.getLogger(MedicamentRepository.class.getName());

    private final Connection connection;

    public DoctorRepository(ConnectionBuilder connectionBuilder) {
        this.connection = connectionBuilder.getConnection();
    }

    public Doctor find(Long id) throws SQLException {
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection.prepareStatement("SELECT doctor_id, fio, name_university, specialization, work_experience FROM doctor WHERE doctor_id = ?"));
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getDoctor(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, "Find doctor exception", e);
            throw new SQLException(e);
        }
        return null;
    }

    public void update(Doctor doctor) throws SQLException {
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection.prepareStatement("UPDATE doctor SET fio = ?, name_university = ?, specialization = ?, work_experience = ? WHERE doctor_id = ? "));
            preparedStatement.setString(1, doctor.getFio());
            preparedStatement.setString(2, doctor.getNameUniversity());
            preparedStatement.setString(3, doctor.getSpecialization());
            preparedStatement.setDate(4, doctor.convertWorkExperienceToSQLDate());
            preparedStatement.setLong(5, doctor.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.INFO, "Update doctor exception", e);
            throw new SQLException(e);
        }
    }

    public void insert(Doctor doctor) throws SQLException {
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection.prepareStatement("INSERT INTO doctor (doctor_id, fio, name_university," +
                    "specialization, work_experience) VALUES (?, ?, ?, ?, ?)"));
            preparedStatement.setLong(1, doctor.getId());
            preparedStatement.setString(2, doctor.getFio());
            preparedStatement.setString(3, doctor.getNameUniversity());
            preparedStatement.setString(4, doctor.getSpecialization());
            preparedStatement.setDate(5, doctor.convertWorkExperienceToSQLDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.INFO, "Insert doctor exception", e);
            throw new SQLException(e);
        }
    }

    public void delete(Doctor doctor) throws SQLException {
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection.prepareStatement("DELETE FROM doctor WHERE doctor_id = ?"));
            preparedStatement.setLong(1, doctor.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.INFO, "Delete doctor exception", e);
            throw new SQLException(e);
        }
    }

    private static Doctor getDoctor(ResultSet resultSet) throws SQLException {
        return new Doctor(
                resultSet.getLong("doctor_id"),
                resultSet.getString("fio"),
                resultSet.getString("name_university"),
                resultSet.getString("specialization"),
                resultSet.getDate("work_experience").toLocalDate()
        );
    }

    public Doctor findByfio(String fio) {
        try  {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection.prepareStatement("SELECT doctor_id, fio, name_university," +
                    "specialization, work_experience FROM doctor WHERE fio = ?"));
            preparedStatement.setString(1, fio);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getDoctor(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, "Find doctor by name exception", e);
        }
        return null;
    }

}
