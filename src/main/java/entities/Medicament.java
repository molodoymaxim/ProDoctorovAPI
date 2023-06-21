package entities;

public class Medicament {
    private long id;
    private String nameMedicament;
    private String nameSubstance;
    private String Recomendation;
    public Medicament() {
    }

    public Medicament(Long id, String nameMedicament, String nameSubstance,String recomendation) {
        this.id = id;
        this.nameMedicament = nameMedicament;
        this.nameSubstance = nameSubstance;
        Recomendation = recomendation;
    }

    public String getNameMedicament() {
        return nameMedicament;
    }

    public void setNameMedicament(String nameMedicament) {
        this.nameMedicament = nameMedicament;
    }

    public String getNameSubstance() {
        return nameSubstance;
    }

    public void setNameSubstance(String nameSubstance) {
        this.nameSubstance = nameSubstance;
    }

    public String getRecomendation() {
        return Recomendation;
    }

    public void setRecomendation(String recomendation) {
        Recomendation = recomendation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "id=" + id +
                ", nameMedicament='" + nameMedicament + '\'' +
                ", nameMedicament='" + nameSubstance + '\'' +
                ", Recomendation='" + Recomendation + '\'' +
                '}';
    }
}
