package grup.Domain;

public class Rezervare extends Entity<String>{
    private String idExcursie;
    private String idPersoana;
    private Integer nrBilete;

    public Rezervare(String id, String idExcursie, String idPersoana, Integer nrBilete) {
        super(id);
        this.idExcursie = idExcursie;
        this.idPersoana = idPersoana;
        this.nrBilete = nrBilete;
    }

    public String getIdExcursie() {
        return idExcursie;
    }

    public void setIdExcursie(String idExcursie) {
        this.idExcursie = idExcursie;
    }

    public String getIdPersoana() {
        return idPersoana;
    }

    public void setIdPersoana(String idPersoana) {
        this.idPersoana = idPersoana;
    }

    public Integer getNrBilete() {
        return nrBilete;
    }

    public void setNrBilete(Integer nrBilete) {
        this.nrBilete = nrBilete;
    }
}
