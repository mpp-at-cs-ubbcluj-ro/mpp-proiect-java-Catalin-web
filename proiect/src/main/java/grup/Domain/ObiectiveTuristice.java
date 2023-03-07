package grup.Domain;

public class ObiectiveTuristice extends Entity<String>{
    private String nume;

    public ObiectiveTuristice(String id, String nume) {
        super(id);
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
