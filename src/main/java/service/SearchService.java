package service;

import help.ConnectionBuilderPostgres;
import jakarta.servlet.ServletException;
import repositories.DoctorRepository;
import repositories.MedicamentRepository;
import repositories.NoteRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchService {

    private final List<String> result;

    ConnectionBuilderPostgres connectionBuilderPostgres = new ConnectionBuilderPostgres();

    DoctorRepository doctorRepository = new DoctorRepository(connectionBuilderPostgres);
    NoteRepository noteRepository = new NoteRepository(connectionBuilderPostgres);
    MedicamentRepository medicamentRepository = new MedicamentRepository(connectionBuilderPostgres);


    public SearchService() {
        result = new ArrayList<>();
    }


    public List<String> searchByDoctor(String s) throws ServletException {
        if (doctorRepository.findByfio(s) != null) {
            result.add(doctorRepository.findByfio(s).toString());
        }
        return result;
    }

    public List<String> searchByNote(String s) throws ServletException {
        if (noteRepository.find(Long.valueOf(s)) != null) {
            result.add(noteRepository.find(Long.valueOf(s)).toString());
        }
        return result;
    }

    public List<String> searchByMedicament(String s) throws ServletException {
        if (medicamentRepository.findByName(s) != null) {
            result.add(medicamentRepository.findByName(s).toString());
        }
        return result;
    }

}
