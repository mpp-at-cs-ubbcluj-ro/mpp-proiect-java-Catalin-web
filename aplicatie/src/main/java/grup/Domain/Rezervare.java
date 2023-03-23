package grup.Domain;

import java.util.Objects;

public class Rezervare extends Entity<Integer>{
    private Integer idExcursie;
    private Integer idPersoana;
    private Integer nrBilete;

    public Rezervare(Integer idExcursie, Integer idPersoana, Integer nrBilete) {
        super(-1);
        this.idExcursie = idExcursie;
        this.idPersoana = idPersoana;
        this.nrBilete = nrBilete;
    }

    public Rezervare(Integer id, Integer idExcursie, Integer idPersoana, Integer nrBilete) {
        super(id);
        this.idExcursie = idExcursie;
        this.idPersoana = idPersoana;
        this.nrBilete = nrBilete;
    }

    public Integer getIdExcursie() {
        return idExcursie;
    }

    public void setIdExcursie(Integer idExcursie) {
        this.idExcursie = idExcursie;
    }

    public Integer getIdPersoana() {
        return idPersoana;
    }

    public void setIdPersoana(Integer idPersoana) {
        this.idPersoana = idPersoana;
    }

    public Integer getNrBilete() {
        return nrBilete;
    }

    public void setNrBilete(Integer nrBilete) {
        this.nrBilete = nrBilete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rezervare rezervare = (Rezervare) o;
        return Objects.equals(idExcursie, rezervare.idExcursie) && Objects.equals(idPersoana, rezervare.idPersoana) && Objects.equals(nrBilete, rezervare.nrBilete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idExcursie, idPersoana, nrBilete);
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "idExcursie=" + idExcursie +
                ", idPersoana=" + idPersoana +
                ", nrBilete=" + nrBilete +
                '}';
    }
}
