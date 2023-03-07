package grup.Domain;

import java.awt.datatransfer.FlavorEvent;
import java.time.LocalTime;

public class Excursie extends Entity<String>{
    private String idObiectiv;
    private String idFirmaTransport;
    private LocalTime ora;
    private Float pret;
    private Integer nrLocuriTotale;

    public Excursie(String id, String idObiectiv, String idFirmaTransport, LocalTime ora, Float pret, Integer nrLocuriTotale) {
        super(id);
        this.idObiectiv = idObiectiv;
        this.idFirmaTransport = idFirmaTransport;
        this.ora = ora;
        this.pret = pret;
        this.nrLocuriTotale = nrLocuriTotale;
    }

    public String getIdObiectiv() {
        return idObiectiv;
    }

    public void setIdObiectiv(String idObiectiv) {
        this.idObiectiv = idObiectiv;
    }

    public String getIdFirmaTransport() {
        return idFirmaTransport;
    }

    public void setIdFirmaTransport(String idFirmaTransport) {
        this.idFirmaTransport = idFirmaTransport;
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
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
}
