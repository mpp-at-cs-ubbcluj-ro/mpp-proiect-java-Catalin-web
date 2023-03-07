package grup.Domain;

public class Entity<Type> {
    public Entity(Type id) {
        this.id = id;
    }

    public Type getId() {
        return id;
    }

    public void setId(Type id) {
        this.id = id;
    }

    private Type id;
}
