package grup.Domain;

public class Persoana extends Entity<String>{
    public Persoana(String id) {
        super(id);
    }

    private String nume;
    private String numarTelefon;

    public Persoana(String id, String nume, String numarTelefon) {
        super(id);
        this.nume = nume;
        this.numarTelefon = numarTelefon;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }
}
