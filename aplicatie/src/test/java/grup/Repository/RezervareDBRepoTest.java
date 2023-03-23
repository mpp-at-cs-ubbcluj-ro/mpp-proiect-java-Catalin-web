package grup.Repository;

import grup.Domain.Excursie;
import grup.Domain.Persoana;
import grup.Domain.Rezervare;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class RezervareDBRepoTest {

    private Repository<Integer, Rezervare> repo;
    private List<Rezervare> lst;

    @Test
    void adauga() {
        Rezervare rezervare = new Rezervare(2,3,4);
        repo.adauga(rezervare);
        assertEquals(1,repo.getAll().size());
        assertEquals(rezervare,repo.getAll().get(0));
    }

    @Test
    void sterge() {
        Rezervare rezervare = new Rezervare(2,3,4);
        repo.adauga(rezervare);
        assertEquals(1,repo.getAll().size());
        rezervare= repo.getAll().get(0);
        repo.sterge(rezervare);
        assertEquals(0,repo.getAll().size());
    }

    @Test
    void cautaId() {
        Rezervare rezervare = new Rezervare(2,3,4);
        repo.adauga(rezervare);
        repo.adauga(rezervare);
        repo.adauga(rezervare);
        repo.adauga(rezervare);
        assertEquals(4,repo.getAll().size());
    }

    @Test
    void getAll() {
        Rezervare rezervare = new Rezervare(2,3,4);
        Rezervare newRezervare = new Rezervare(5,5,7);
        repo.adauga(rezervare);
        assertEquals(1,repo.getAll().size());
        rezervare= repo.getAll().get(0);
        repo.update(rezervare,newRezervare);
        assertEquals(newRezervare,repo.getAll().get(0));
    }

    @Test
    void update() {
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
        repo = new RezervareDBRepo(props);
        lst = repo.getAll();
        for(Rezervare el:lst){
            repo.sterge(el);
        }

    }

    @AfterEach
    void tearDown() {
        for(Rezervare entity:repo.getAll())
        {
            repo.sterge(entity);
        }

        for(Rezervare el: lst)
        {
            repo.adauga(el);
        }
    }
}