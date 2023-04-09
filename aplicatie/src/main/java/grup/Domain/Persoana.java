package grup.Domain;

import java.util.Map;
import java.util.Objects;

public class Persoana extends Entity<Integer>{
    private String nume;
    private String numarTelefon;

    public Persoana(String nume, String numarTelefon) {
        super(-1);
        this.nume = nume;
        this.numarTelefon = numarTelefon;
    }

    public Persoana(Integer id, String nume, String numarTelefon) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persoana persoana = (Persoana) o;
        return Objects.equals(nume, persoana.nume) && Objects.equals(numarTelefon, persoana.numarTelefon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, numarTelefon);
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "nume='" + nume + '\'' +
                ", numarTelefon='" + numarTelefon + '\'' +
                '}';
    }

    public static Persoana deserialize(Map<String, Object> map)
    {
        Integer id = ((Double) map.get("id")).intValue();
        String nume = (String) map.get("nume");
        String numarTelefon = (String) map.get("numarTelefon");
        return new Persoana(id,nume,numarTelefon);
    }
}
