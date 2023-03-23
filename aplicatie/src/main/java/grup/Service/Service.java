package grup.Service;

import grup.Domain.*;
import grup.Repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private Integer userId = -1;
    private Repository<Integer, Excursie> excursii;
    private Repository<Integer, FirmaTransport> firmeTransport;
    private Repository<Integer, ObiectivTuristic> obiectiveTuristice;
    private Repository<Integer, Persoana> persoane;
    private Repository<Integer, User> useri;
    private Repository<Integer, Rezervare> rezervari;

    private static final Logger logger = LogManager.getLogger();

    public Service(Repository<Integer, Excursie> excursii, Repository<Integer, FirmaTransport> firmeTransport, Repository<Integer, ObiectivTuristic> obiectiveTuristice, Repository<Integer, Persoana> persoane, Repository<Integer, User> useri, Repository<Integer, Rezervare> rezervari) {
        this.excursii = excursii;
        this.firmeTransport = firmeTransport;
        this.obiectiveTuristice = obiectiveTuristice;
        this.persoane = persoane;
        this.useri = useri;
        this.rezervari = rezervari;
    }

    public void login(String email, String parola) {
        List<User> lst = useri.getAll();

        boolean ok = false;
        for (User u : lst) {
            if (u.getEmail().equals(email) && u.getParola().equals(parola)) {
                ok = true;
                userId = u.getId();
                break;
            }
        }
        if (ok == false) {
            throw new Error("Nu exista un utilizator cu aceast parol si email");
        }
    }

    public void inregistrare(String email, String parola) {
        User user = new User(email, parola);
        useri.adauga(user);
    }

    public void logout() {
        userId = -1;
    }

    public List<Excursie> getAllExcursii() {
        return excursii.getAll();
    }

    public String getNumeFirmaById(Integer idFirma) {
        return firmeTransport.cautaId(idFirma).getNume();
    }

    public String getNumeObiectivById(Integer idObiectiv) {
        return obiectiveTuristice.cautaId(idObiectiv).getNume();
    }

    public Integer getNumarLocuriDisponibile(Integer idExcursie) {
        Excursie excursie = excursii.cautaId(idExcursie);
        Integer cnt = excursie.getNrLocuriTotale();
        for (Rezervare r : rezervari.getAll()) {
            if (r.getIdExcursie().equals(idExcursie)) {
                cnt -= r.getNrBilete();
            }
        }
        return cnt;
    }

    private ObiectivTuristic getObiectivTuristicByNume(String numeObiectiv) {
        for (ObiectivTuristic o : obiectiveTuristice.getAll()) {
            if (o.getNume().equals(numeObiectiv)) {
                return o;
            }
        }
        return null;
    }

    public List<Excursie> getExursiiByObiectivTuristic(String numeObiectiv, Integer minOra, Integer maxOra) {
        ObiectivTuristic obiectivTuristic = getObiectivTuristicByNume(numeObiectiv);
        List<Excursie> lst = new ArrayList<>();
        for (Excursie excursie : excursii.getAll()) {
            if (excursie.getOra() >= minOra && excursie.getOra() <= maxOra) {
                lst.add(excursie);
            }
        }
        return lst;
    }

    private Integer getIdPersoanaByNume(String numePersoana, String numarTelefon)
    {
        for(Persoana p: persoane.getAll())
        {
            if(p.getNume().equals(numePersoana) && p.getNumarTelefon().equals(numarTelefon))
            {
                return p.getId();
            }
        }
        return null;
    }

    public void adaugaRezervare(Integer idExcursie,String numeClient, String numarTelefonClient, Integer numarBileteDorite)
    {
        Integer idPersoana = getIdPersoanaByNume(numeClient, numarTelefonClient);
        Rezervare rezervare = new Rezervare(idExcursie,idPersoana, numarBileteDorite);
        if(rezervare.getNrBilete() <= getNumarLocuriDisponibile(rezervare.getIdExcursie()))
        {
        rezervari.adauga(rezervare);
        }
    }
}
