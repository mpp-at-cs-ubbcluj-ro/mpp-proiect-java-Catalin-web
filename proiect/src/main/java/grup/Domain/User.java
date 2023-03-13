package grup.Domain;

public class User extends Entity<String>{
    private String email;
    private String parola;

    public User(String id, String email, String parola) {
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
}
