package dbtest;

import entities.Medicament;
import help.ConnectionBuilderTest;
import org.junit.Test;
import repositories.DoctorRepository;
import repositories.MedicamentRepository;

import java.sql.SQLException;

import static dbtest.TestData.DOCTOR;
import static dbtest.TestData.MEDICAMENT;
import static org.testng.AssertJUnit.*;
import static org.testng.AssertJUnit.assertEquals;

public class MedicamentRepositoryTest {
    ConnectionBuilderTest connectionBuilderTest = new ConnectionBuilderTest();
    MedicamentRepository medicamentRepository = new MedicamentRepository(connectionBuilderTest);
    DoctorRepository doctorRepository = new DoctorRepository(connectionBuilderTest);

    @Test
    public void findManufacturer() throws SQLException {
        doctorRepository.insert(DOCTOR);
        medicamentRepository.insert(MEDICAMENT);
        Long id = MEDICAMENT.getId();
        Medicament findedMedicament = medicamentRepository.find(id);
        assertEquals(MEDICAMENT.toString(), findedMedicament.toString());
        medicamentRepository.delete(findedMedicament);
        doctorRepository.delete(DOCTOR);
    }
    @Test
    public void updateManufacturer() throws SQLException {
        doctorRepository.insert(DOCTOR);
        medicamentRepository.insert(MEDICAMENT);
        Long idMedicament = MEDICAMENT.getId();
        Medicament updatedMedicament = new Medicament(idMedicament, "Paracetamol", "Nurofen", "2 tablets per day");
        medicamentRepository.update(updatedMedicament);
        assertEquals(updatedMedicament.toString(), medicamentRepository.find(idMedicament).toString());
        medicamentRepository.delete(updatedMedicament);
        doctorRepository.delete(DOCTOR);
    }
    @Test
    public void deleteManufacturer() throws SQLException {
        doctorRepository.insert(DOCTOR);
        medicamentRepository.insert(MEDICAMENT);
        Long id = MEDICAMENT.getId();
        Medicament findedMedicament = medicamentRepository.find(id);
        medicamentRepository.delete(findedMedicament);
        assertNull(medicamentRepository.find(id));
        doctorRepository.delete(DOCTOR);
    }
    @Test
    public void insertManufacturer() throws SQLException {
        doctorRepository.insert(DOCTOR);
        medicamentRepository.insert(MEDICAMENT);
        Long id = MEDICAMENT.getId();
        assertNotNull(medicamentRepository.find(id));
        Medicament findedMedicament = medicamentRepository.find(id);
        medicamentRepository.delete(findedMedicament);
        doctorRepository.delete(DOCTOR);
    }
    @Test
    public void findByNameManufacturer() throws SQLException {
        doctorRepository.insert(DOCTOR);
        medicamentRepository.insert(MEDICAMENT);
        Medicament findedMedicament = medicamentRepository.findByName(MEDICAMENT.getNameMedicament());
        assertEquals(MEDICAMENT.toString(), findedMedicament.toString());
        medicamentRepository.delete(findedMedicament);
        doctorRepository.delete(DOCTOR);
    }
}
