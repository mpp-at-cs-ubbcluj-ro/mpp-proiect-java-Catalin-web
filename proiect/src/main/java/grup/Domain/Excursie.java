package grup.Domain;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Objects;

public class Excursie extends Entity<Integer>{
    private Integer idObiectiv;
    private Integer idFirmaTransport;
    private Timestamp ora;
    private Float pret;
    private Integer nrLocuriTotale;

    public Excursie(Integer idObiectiv, Integer idFirmaTransport, Timestamp ora, Float pret, Integer nrLocuriTotale) {
        super(-1);
        this.idObiectiv = idObiectiv;
        this.idFirmaTransport = idFirmaTransport;
        this.ora = ora;
        this.pret = pret;
        this.nrLocuriTotale = nrLocuriTotale;
    }

    public Excursie(Integer id, Integer idObiectiv, Integer idFirmaTransport, Timestamp ora, Float pret, Integer nrLocuriTotale) {
        super(id);
        this.idObiectiv = idObiectiv;
        this.idFirmaTransport = idFirmaTransport;
        this.ora = ora;
        this.pret = pret;
        this.nrLocuriTotale = nrLocuriTotale;
    }

    public Integer getIdObiectiv() {
        return idObiectiv;
    }

    public void setIdObiectiv(Integer idObiectiv) {
        this.idObiectiv = idObiectiv;
    }

    public Integer getIdFirmaTransport() {
        return idFirmaTransport;
    }

    public void setIdFirmaTransport(Integer idFirmaTransport) {
        this.idFirmaTransport = idFirmaTransport;
    }

    public Timestamp getOra() {
        return ora;
    }

    public void setOra(Timestamp ora) {
        this.ora = ora;
    }

    public Float getPret() {
        return pret;
    }

    public void setPret(Float pret) {
        this.pret = pret;
    }

    public Integer getNrLocuriTotale() {
        return nrLocuriTotale;
    }

    public void setNrLocuriTotale(Integer nrLocuriTotale) {
        this.nrLocuriTotale = nrLocuriTotale;
    }

    @Override
    public String toString() {
        return "Excursie{" +
                "idObiectiv=" + idObiectiv +
                ", idFirmaTransport=" + idFirmaTransport +
                ", ora=" + ora +
                ", pret=" + pret +
                ", nrLocuriTotale=" + nrLocuriTotale +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Excursie excursie = (Excursie) o;
        return Objects.equals(idObiectiv, excursie.idObiectiv) && Objects.equals(idFirmaTransport, excursie.idFirmaTransport) && Objects.equals(ora, excursie.ora) && Objects.equals(pret, excursie.pret) && Objects.equals(nrLocuriTotale, excursie.nrLocuriTotale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idObiectiv, idFirmaTransport, ora, pret, nrLocuriTotale);
    }
}
