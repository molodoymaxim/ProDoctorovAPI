package repositories;

import entities.Note;
import help.ConnectionBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoteRepository {

    private static final Logger logger = Logger.getLogger(MedicamentRepository.class.getName());
    private final Connection connection;

    public NoteRepository(ConnectionBuilder connectionBuilder) {
        this.connection = connectionBuilder.getConnection();
    }

    public Note find(Long id) {
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection.prepareStatement("SELECT note_id, dose_medicament, start_medication, end_medication, interval_medication, doctor_id, medication_id FROM note WHERE note_id = ?"));
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getNote(resultSet);
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, "Find doctor in doctorstore exception", e);
        }
        return null;
    }

    public void update(Note note) throws SQLException {
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection.prepareStatement("UPDATE note SET note_id = ?, dose_medicament = ?,  start_medication = ?," +
                    " end_medication = ?, interval_medication = ?, doctor_id = ?, medication_id = ?  WHERE note_id = ? "));
            preparedStatement.setLong(1, note.getId());
            preparedStatement.setString(2, note.getDoseMedicament());
            preparedStatement.setDate(3, note.convertStartMedicationToSQLDate());
            preparedStatement.setDate(4, note.convertEndMedicationToSQLDate());
            preparedStatement.setDate(5, note.convertIntervalMedicationToSQLDate());
            preparedStatement.setLong(6, note.getIdDoctor());
            preparedStatement.setLong(7, note.getIdMedicament());
            preparedStatement.setLong(8, note.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.INFO, "Update doctor in doctorstore exception", e);
            throw new SQLException(e);
        }
    }

    public void insert(Note note) throws SQLException {
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection.prepareStatement("INSERT INTO note (note_id, dose_medicament, start_medication, end_medication, interval_medication, doctor_id, medication_id)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)"));
            preparedStatement.setLong(1, note.getId());
            preparedStatement.setString(2, note.getDoseMedicament());
            preparedStatement.setDate(3, note.convertStartMedicationToSQLDate());
            preparedStatement.setDate(4, note.convertEndMedicationToSQLDate());
            preparedStatement.setDate(5, note.convertIntervalMedicationToSQLDate());
            preparedStatement.setLong(6, note.getIdDoctor());
            preparedStatement.setLong(7, note.getIdMedicament());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.INFO, "Insert doctor in doctorstore exception", e);
            throw new SQLException(e);
        }
    }

    public void delete(Note note) throws SQLException {
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection.prepareStatement("DELETE FROM note WHERE note_id = ?"));
            preparedStatement.setLong(1, note.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.INFO, "Delete doctor in doctorstore exception", e);
            throw new SQLException(e);
        }
    }

    private static Note getNote(ResultSet resultSet) throws SQLException {
        return new Note(
                resultSet.getLong("note_id"),
                resultSet.getString("dose_medicament"),
                resultSet.getDate("start_medication").toLocalDate(),
                resultSet.getDate("end_medication").toLocalDate(),
                resultSet.getDate("interval_medication").toLocalDate(),
                resultSet.getLong("doctor_id"),
                resultSet.getLong("medication_id")
        );
    }
}
