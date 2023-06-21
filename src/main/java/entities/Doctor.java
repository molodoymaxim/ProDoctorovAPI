package entities;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class Doctor {
    private long id; // -> long
    private String fio;
    private String nameUniversity;
    private String specialization;
    public LocalDate workExperience;

    public Doctor(long id, String fio, String nameUniversity, String specialization, LocalDate workExperience) {
        this.id = id;
        this.fio = fio;
        this.nameUniversity = nameUniversity;
        this.specialization = specialization;
        this.workExperience = workExperience;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getNameUniversity() {
        return nameUniversity;
    }

    public void setNameUniversity(String nameUniversity) {
        this.nameUniversity = nameUniversity;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public LocalDate getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(LocalDate workExperience) {
        this.workExperience = workExperience;
    }

    public Doctor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date convertWorkExperienceToSQLDate() {
        return Date.valueOf(workExperience);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", nameUniversity='" + nameUniversity + '\'' +
                ", specialization='" + specialization + '\'' +
                ", workExperience=" + workExperience +
                '}';
    }
}
