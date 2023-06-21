package dbtest;

import entities.Doctor;
import help.ConnectionBuilderTest;
import org.junit.Test;
import repositories.DoctorRepository;

import java.sql.SQLException;

import static dbtest.TestData.*;
import static org.testng.AssertJUnit.*;


public class DoctorRepositoryTest {

    ConnectionBuilderTest connectionBuilderTest = new ConnectionBuilderTest();
    DoctorRepository doctorRepository = new DoctorRepository(connectionBuilderTest);

    @Test
    public void findDoctor() throws SQLException {
        doctorRepository.insert(DOCTOR);
        Long id = DOCTOR.getId();
        Doctor findedDoctor = doctorRepository.find(id);
        assertEquals(DOCTOR.toString(), findedDoctor.toString());
        doctorRepository.delete(findedDoctor);
    }
    @Test
    public void updateDoctor() throws SQLException {
        doctorRepository.insert(DOCTOR);
        Long id = DOCTOR.getId();
        Doctor updatedDoctor = new Doctor(id, "Зубенко Михаил Петрович", "VSU", "Brain", workExperience);
        doctorRepository.update(updatedDoctor);
        assertEquals(updatedDoctor.toString(), doctorRepository.find(id).toString());
        doctorRepository.delete(updatedDoctor);
    }
    @Test
    public void deleteDoctor() throws SQLException {
        doctorRepository.insert(DOCTOR);
        Long id = DOCTOR.getId();
        Doctor findedDoctor = doctorRepository.find(id);
        doctorRepository.delete(findedDoctor);
        assertNull(doctorRepository.find(id));
    }
    @Test
    public void insertDoctor() throws SQLException {
        doctorRepository.insert(DOCTOR);
        Long id = DOCTOR.getId();
        assertNotNull(doctorRepository.find(id));
        Doctor findedDoctor = doctorRepository.find(id);
        doctorRepository.delete(findedDoctor);
    }
    @Test
    public void findByfio() throws SQLException{
        doctorRepository.insert(DOCTOR);
        Doctor findedDoctor = doctorRepository.findByfio(DOCTOR.getFio());
        assertEquals(DOCTOR.toString(), findedDoctor.toString());
        doctorRepository.delete(findedDoctor);
    }
}
