package grup.Repository;

import grup.Domain.ObiectivTuristic;
import grup.Domain.Persoana;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class PersoanaDBRepoTest {

    private Repository<Integer, Persoana> repo;
    private List<Persoana> lst;


    @Test
    void adauga() {
        Persoana persoana = new Persoana("nume","numarTelefon");
        repo.adauga(persoana);
        assertEquals(1, repo.getAll().size());
        assertEquals(persoana,repo.getAll().get(0));
    }

    @Test
    void sterge() {
        Persoana persoana = new Persoana("nume","numarTelefon");
        repo.adauga(persoana);
        assertEquals(1, repo.getAll().size());
        persoana = repo.getAll().get(0);
        repo.sterge(persoana);
        assertEquals(0,repo.getAll().size());
    }

    @Test
    void cautaId() {
        Persoana persoana = new Persoana("nume","numarTelefon");
        repo.adauga(persoana);
        assertEquals(1, repo.getAll().size());
        persoana = repo.getAll().get(0);
        assertEquals(persoana,repo.cautaId(persoana.getId()));
    }

    @Test
    void getAll() {
        Persoana persoana = new Persoana("nume","numarTelefon");
        repo.adauga(persoana);
        repo.adauga(persoana);
        repo.adauga(persoana);
        repo.adauga(persoana);
        assertEquals(4, repo.getAll().size());
    }

    @Test
    void update() {
        Persoana persoana = new Persoana("nume","numarTelefon");
        Persoana newPersoana = new Persoana("altNume","altNumarTelefon");
        repo.adauga(persoana);
        assertEquals(1, repo.getAll().size());
        persoana = repo.getAll().get(0);
        repo.update(persoana,newPersoana);
        assertEquals(newPersoana,repo.getAll().get(0));
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
        repo = new PersoanaDBRepo(props);
        lst = repo.getAll();
        for(Persoana el:lst){
            repo.sterge(el);
        }

    }

    @AfterEach
    void tearDown() {
        for(Persoana el: lst)
        {
            repo.adauga(el);
        }
    }
}