package grup.Repository;

import grup.Domain.Excursie;
import grup.Domain.FirmaTransport;
import grup.Domain.ObiectivTuristic;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class FirmaTransportDBRepoTest {

    private Repository<Integer, FirmaTransport> repo;
    private List<FirmaTransport> lst;

    @Test
    void adauga() {
        FirmaTransport el = new FirmaTransport("nume");
        repo.adauga(el);
        assertEquals(1, repo.getAll().size());
        assertEquals("nume",repo.getAll().get(0).getNume());
    }

    @Test
    void sterge() {
        FirmaTransport el = new FirmaTransport("nume");
        repo.adauga(el);
        assertEquals(1, repo.getAll().size());
        el = repo.getAll().get(0);
        repo.sterge(el);
        assertEquals(0,repo.getAll().size());
    }

    @Test
    void cautaId() {
        FirmaTransport el = new FirmaTransport("nume");
        repo.adauga(el);
        assertEquals(1, repo.getAll().size());
        el = repo.getAll().get(0);
        assertEquals(el, repo.cautaId(el.getId()));
    }

    @Test
    void getAll() {
        FirmaTransport el = new FirmaTransport("nume");
        repo.adauga(el);
        repo.adauga(el);
        repo.adauga(el);
        repo.adauga(el);
        assertEquals(4, repo.getAll().size());
    }

    @Test
    void update() {
        FirmaTransport el = new FirmaTransport("nume");
        repo.adauga(el);
        FirmaTransport newEl = new FirmaTransport("Alt nume");
        el.setId(repo.getAll().get(0).getId());
        repo.update(el,newEl);
        assertEquals(newEl, repo.getAll().get(0));
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
        repo = new FirmaTransportDBRepo(props);
        lst = repo.getAll();
        for(FirmaTransport el:lst){
            repo.sterge(el);
        }

    }

    @AfterEach
    void tearDown() {
        for(FirmaTransport entity:repo.getAll())
        {
            repo.sterge(entity);
        }
        for(FirmaTransport el: lst)
        {
            repo.adauga(el);
        }
    }
}