package entities;

import java.sql.Date;
import java.time.LocalDate;

public class Note {

    private long id;
    private String doseMedicament;
    private LocalDate startMedication;
    private LocalDate endMedication;
    private LocalDate intervalMedication;
    private long idDoctor;
    private long idMedicament;

    public Note(Long id, String doseMedicament, LocalDate startMedication, LocalDate endMedication, LocalDate intervalMedication, long idDoctor, long idMedicament) {
        this.id = id;
        this.doseMedicament = doseMedicament;
        this.startMedication = startMedication;
        this.endMedication = endMedication;
        this.intervalMedication = intervalMedication;
        this.idDoctor = idDoctor;
        this.idMedicament = idMedicament;
    }

    public Note() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoseMedicament() {
        return doseMedicament;
    }

    public void setDoseMedicament(String doseMedicament) {
        this.doseMedicament = doseMedicament;
    }

    public LocalDate getStartMedication() {
        return startMedication;
    }

    public void setStartMedication(LocalDate startMedication) {
        this.startMedication = startMedication;
    }

    public LocalDate getEndMedication() {
        return endMedication;
    }

    public void setEndMedication(LocalDate endMedication) {
        this.endMedication = endMedication;
    }

    public LocalDate getIntervalMedication() {
        return intervalMedication;
    }

    public void setIntervalMedication(LocalDate intervalMedication) {
        this.intervalMedication = intervalMedication;
    }

    public long getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(long idDoctor) {
        this.idDoctor = idDoctor;
    }

    public long getIdMedicament() {
        return idMedicament;
    }

    public void setIdMedicament(long idMedicament) {
        this.idMedicament = idMedicament;
    }

    public Date convertStartMedicationToSQLDate() {
        return Date.valueOf(startMedication);
    }

    public Date convertEndMedicationToSQLDate() {
        return Date.valueOf(endMedication);
    }

    public Date convertIntervalMedicationToSQLDate() {
        return Date.valueOf(intervalMedication);
    }
    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", doseMedicament='" + doseMedicament + '\'' +
                ", startMedication=" + startMedication +
                ", endMedication=" + endMedication +
                ", intervalMedication=" + intervalMedication +
                ", idDoctor=" + idDoctor +
                ", idMedicament=" + idMedicament +
                '}';
    }
}
