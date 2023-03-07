package grup.Domain;

public class FirmaTransport extends Entity<String>{
    public FirmaTransport(String id, String nume) {
        super(id);
        this.nume = nume;
    }

    private String nume;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
