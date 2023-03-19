package grup.Repository;

import grup.Domain.FirmaTransport;
import grup.Domain.ObiectivTuristic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class ObiectivTuristicDBRepoTest {

    private Repository<Integer, ObiectivTuristic> repo;
    private List<ObiectivTuristic> lst;

    @Test
    void adauga() {
        ObiectivTuristic obiectivTuristic = new ObiectivTuristic("nume");
        repo.adauga(obiectivTuristic);
        assertEquals(1, repo.getAll().size());
        assertEquals(obiectivTuristic, repo.getAll().get(0));
    }

    @Test
    void sterge() {
        ObiectivTuristic obiectivTuristic = new ObiectivTuristic("nume");
        repo.adauga(obiectivTuristic);
        assertEquals(1, repo.getAll().size());
        obiectivTuristic = repo.getAll().get(0);
        repo.sterge(obiectivTuristic);

        assertEquals(0, repo.getAll().size());

    }

    @Test
    void cautaId() {
        ObiectivTuristic obiectivTuristic = new ObiectivTuristic("nume");
        repo.adauga(obiectivTuristic);
        assertEquals(1, repo.getAll().size());
        obiectivTuristic = repo.getAll().get(0);
        assertEquals(obiectivTuristic,repo.cautaId(obiectivTuristic.getId()));
    }

    @Test
    void getAll() {
        ObiectivTuristic obiectivTuristic = new ObiectivTuristic("nume");
        repo.adauga(obiectivTuristic);
        repo.adauga(obiectivTuristic);
        repo.adauga(obiectivTuristic);
        repo.adauga(obiectivTuristic);
        assertEquals(4, repo.getAll().size());
    }

    @Test
    void update() {
        ObiectivTuristic obiectivTuristic = new ObiectivTuristic("nume");
        ObiectivTuristic newObiectivTuristic = new ObiectivTuristic("altNume");
        repo.adauga(obiectivTuristic);
        obiectivTuristic = repo.getAll().get(0);
        repo.update(obiectivTuristic,newObiectivTuristic);
        assertEquals(newObiectivTuristic, repo.getAll().get(0));
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
        repo = new ObiectivTuristicDBRepo(props);
        lst = repo.getAll();
        for(ObiectivTuristic el:lst){
            repo.sterge(el);
        }

    }

    @AfterEach
    void tearDown() {
        for(ObiectivTuristic el: lst)
        {
            repo.adauga(el);
        }
    }
}