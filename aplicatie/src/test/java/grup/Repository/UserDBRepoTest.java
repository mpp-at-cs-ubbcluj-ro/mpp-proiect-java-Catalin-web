package grup.Repository;

import grup.Domain.Excursie;
import grup.Domain.Rezervare;
import grup.Domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class UserDBRepoTest {

    private Repository<Integer, User> repo;
    private List<User> lst;

    @Test
    void adauga() {
        User user = new User("email","parola");
        repo.adauga(user);
        assertEquals(1,repo.getAll().size());
        assertEquals(user,repo.getAll().get(0));
    }

    @Test
    void sterge() {
        User user = new User("email","parola");
        repo.adauga(user);
        assertEquals(1,repo.getAll().size());
        user = repo.getAll().get(0);
        repo.sterge(user);
        assertEquals(0,repo.getAll().size());
    }

    @Test
    void cautaId() {
        User user = new User("email","parola");
        repo.adauga(user);
        assertEquals(1,repo.getAll().size());
        user = repo.getAll().get(0);
        assertEquals(user,repo.cautaId(user.getId()));
    }

    @Test
    void getAll() {
        User user = new User("email","parola");
        repo.adauga(user);
        repo.adauga(user);
        repo.adauga(user);
        repo.adauga(user);
        assertEquals(4,repo.getAll().size());
    }

    @Test
    void update() {
        User user = new User("email","parola");
        User newUser = new User("newEmail","newParola");
        repo.adauga(user);
        assertEquals(1,repo.getAll().size());
        user = repo.getAll().get(0);
        repo.update(user,newUser);
        assertEquals(newUser,repo.getAll().get(0));
    }

    @BeforeEach
    void setUp() {
        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (
                IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }
        repo = new UserDBRepo(props);
        lst = repo.getAll();
        for(User el:lst){
            repo.sterge(el);
        }

    }

    @AfterEach
    void tearDown() {
        for(User entity:repo.getAll())
        {
            repo.sterge(entity);
        }

        for(User el: lst)
        {
            repo.adauga(el);
        }
    }
}