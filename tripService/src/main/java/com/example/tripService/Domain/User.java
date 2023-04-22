package com.example.tripService.Domain;

import java.util.Map;
import java.util.Objects;

public class User extends Entity<Integer>{
    private String email;
    private String parola;

    public User(String email, String parola) {
        super(-1);
        this.email = email;
        this.parola = parola;
    }

    public User(Integer id, String email, String parola) {
        super(id);
        this.email = email;
        this.parola = parola;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(parola, user.parola);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, parola);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }

    public static Persoana deserialize(Map<String, Object> map)
    {
        Integer id = ((Double) map.get("id")).intValue();
        String email = (String) map.get("email");
        String parola = (String) map.get("parola");
        return new Persoana(id,email,parola);
    }
}
