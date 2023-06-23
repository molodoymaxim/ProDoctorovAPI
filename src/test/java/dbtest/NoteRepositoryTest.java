package dbtest;

import entities.Note;
import help.ConnectionBuilderTest;
import org.junit.Test;
import repositories.MedicamentRepository;
import repositories.NoteRepository;
import repositories.DoctorRepository;

import java.sql.SQLException;

import static dbtest.TestData.*;
import static org.junit.Assert.*;

public class NoteRepositoryTest {
    ConnectionBuilderTest connectionBuilderTest = new ConnectionBuilderTest();
    DoctorRepository doctorRepository = new DoctorRepository(connectionBuilderTest);
    MedicamentRepository medicamentRepository = new MedicamentRepository(connectionBuilderTest);
    NoteRepository noteRepository = new NoteRepository(connectionBuilderTest);
    @Test
    public void findManufacturer() throws SQLException {
        doctorRepository.insert(DOCTOR);
        medicamentRepository.insert(MEDICAMENT);
        noteRepository.insert(NOTE);
        Long id = NOTE.getId();
        Note findedNote = noteRepository.find(id);
        assertEquals(NOTE.toString(), findedNote.toString());
        noteRepository.delete(findedNote);
        medicamentRepository.delete(MEDICAMENT);
        doctorRepository.delete(DOCTOR);
    }
    @Test
    public void updateManufacturer() throws SQLException {
        doctorRepository.insert(DOCTOR);
        medicamentRepository.insert(MEDICAMENT);
        noteRepository.insert(NOTE);
        Long idDoctor = DOCTOR.getId();
        Long idMedicament = MEDICAMENT.getId();
        Note updatedNote = new Note(1L, "3 tablets", startMedication, endMedication, intervalMedication, idDoctor, idMedicament);
        noteRepository.update(updatedNote);
        assertEquals(updatedNote.toString(), noteRepository.find(idMedicament).toString());
        noteRepository.delete(updatedNote);
        medicamentRepository.delete(MEDICAMENT);
        doctorRepository.delete(DOCTOR);
    }
    @Test
    public void deleteManufacturer() throws SQLException {
        doctorRepository.insert(DOCTOR);
        medicamentRepository.insert(MEDICAMENT);
        noteRepository.insert(NOTE);
        Long id = NOTE.getId();
        Note findedNote = noteRepository.find(id);
        noteRepository.delete(findedNote);
        assertNull(noteRepository.find(id));
        medicamentRepository.delete(MEDICAMENT);
        doctorRepository.delete(DOCTOR);
    }
    @Test
    public void insertManufacturer() throws SQLException {
        doctorRepository.insert(DOCTOR);
        medicamentRepository.insert(MEDICAMENT);
        noteRepository.insert(NOTE);
        Long id = NOTE.getId();
        assertNotNull(noteRepository.find(id));
        Note findedNote = noteRepository.find(id);
        noteRepository.delete(findedNote);
        medicamentRepository.delete(MEDICAMENT);
        doctorRepository.delete(DOCTOR);
    }

}
