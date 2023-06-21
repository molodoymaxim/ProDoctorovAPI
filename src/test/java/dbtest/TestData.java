package dbtest;

import entities.Doctor;
import entities.Note;
import entities.Medicament;

import java.time.LocalDate;

public class TestData {

    public static final LocalDate workExperience = LocalDate.of(2010, 10, 20);
    public static final LocalDate startMedication = LocalDate.of(2023, 10, 20);
    public static final LocalDate endMedication = LocalDate.of(2023, 11, 20);
    public static final LocalDate intervalMedication = LocalDate.of(2023, 10, 22);
    public static final Doctor DOCTOR = new Doctor(2L, "A234B", "VSU", "Gastroenterology", workExperience);
    public static final Medicament MEDICAMENT = new Medicament(1L, "Tramadol", "Nurofen", "WestStar");
    public static final Note NOTE = new Note(1L, "2 tablets", startMedication, endMedication, intervalMedication, DOCTOR.getId(), MEDICAMENT.getId());
}
