package com.example.tripService.Domain;

import java.util.Map;
import java.util.Objects;

public class FirmaTransport extends Entity<Integer>{
    public FirmaTransport(String nume){
        super(-1);
        this.nume=nume;
    }

    public FirmaTransport(Integer id, String nume) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirmaTransport that = (FirmaTransport) o;
        return Objects.equals(nume, that.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume);
    }

    @Override
    public String toString() {
        return "FirmaTransport{" +
                "nume='" + nume + '\'' +
                '}';
    }

    public static FirmaTransport deserialize(Map<String, Object> map)
    {
        Integer id = ((Double) map.get("id")).intValue();
        String nume = (String) map.get("nume");
        return new FirmaTransport(id,nume);
    }
}
