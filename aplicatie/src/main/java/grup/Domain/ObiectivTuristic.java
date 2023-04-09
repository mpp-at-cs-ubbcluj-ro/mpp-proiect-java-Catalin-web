package grup.Domain;

import java.util.Map;
import java.util.Objects;

public class ObiectivTuristic extends Entity<Integer>{
    private String nume;

    public ObiectivTuristic(String nume) {
        super(-1);
        this.nume = nume;
    }

    public ObiectivTuristic(Integer id, String nume) {
        super(id);
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObiectivTuristic that = (ObiectivTuristic) o;
        return Objects.equals(nume, that.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume);
    }

    @Override
    public String toString() {
        return "ObiectivTuristic{" +
                "nume='" + nume + '\'' +
                '}';
    }

    public static ObiectivTuristic deserialize(Map<String, Object> map)
    {
        Integer id = ((Double) map.get("id")).intValue();
        String nume = (String) map.get("nume");
        return new ObiectivTuristic(id,nume);
    }
}
