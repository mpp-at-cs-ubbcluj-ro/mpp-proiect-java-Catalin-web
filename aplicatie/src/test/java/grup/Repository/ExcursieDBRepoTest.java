package grup.Repository;

import grup.Domain.Excursie;
import grup.Domain.FirmaTransport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class ExcursieDBRepoTest {

    private Repository<Integer, Excursie> repo;
    private List<Excursie> lst;

    @Test
    void adauga() {
        Excursie excursie= new Excursie(1,2, 1234, 5.5f,100);
        repo.adauga(excursie);
        assertEquals(1,repo.getAll().size());
        assertEquals(excursie,repo.getAll().get(0));
    }

    @Test
    void sterge() {
        Excursie excursie= new Excursie(1,2, 1234, 5.5f,100);
        repo.adauga(excursie);
        assertEquals(1,repo.getAll().size());
        excursie = repo.getAll().get(0);
        repo.sterge(excursie);
        assertEquals(0,repo.getAll().size());
    }

    @Test
    void cautaId() {
        Excursie excursie= new Excursie(1,2, 1234, 5.5f,100);
        repo.adauga(excursie);
        assertEquals(1,repo.getAll().size());
        excursie = repo.getAll().get(0);
        assertEquals(excursie,repo.cautaId(excursie.getId()));
    }

    @Test
    void getAll() {
        Excursie excursie= new Excursie(1,2, 1234, 5.5f,100);
        repo.adauga(excursie);
        repo.adauga(excursie);
        repo.adauga(excursie);
        repo.adauga(excursie);
        assertEquals(4,repo.getAll().size());
    }

    @Test
    void update() {
        Excursie excursie= new Excursie(1,2, 1234, 5.5f,100);
        Excursie newexcursie= new Excursie(2,2, 1234, 5.6f,200);
        repo.adauga(excursie);
        excursie = repo.getAll().get(0);
        repo.update(excursie,newexcursie);
        assertEquals(newexcursie,repo.getAll().get(0));
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
        repo = new ExcursieDBRepo(props);
        lst = repo.getAll();
        for(Excursie el:lst){
            repo.sterge(el);
        }

    }

    @AfterEach
    void tearDown() {
        for(Excursie entity:repo.getAll())
        {
            repo.sterge(entity);
        }
        for(Excursie el:lst){
            repo.sterge(el);
        }
        for(Excursie el: lst)
        {
            repo.adauga(el);
        }
    }
}