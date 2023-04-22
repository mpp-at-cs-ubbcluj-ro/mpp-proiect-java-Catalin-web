package com.example.tripService.Service;

import com.example.tripService.Domain.*;
import com.example.tripService.Repository.FirmaTransportDBRepo;
import com.example.tripService.Repository.ObiectivTuristicDBRepo;
import com.example.tripService.Repository.Repository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    private Integer userId = -1;

    @Autowired
    private Repository<Integer, Excursie> excursii;
    @Autowired
    private Repository<Integer, FirmaTransport> firmeTransport;
    @Autowired
    private Repository<Integer, ObiectivTuristic> obiectiveTuristice;
    @Autowired
    private Repository<Integer, Persoana> persoane;
    @Autowired
    private Repository<Integer, User> useri;
    @Autowired
    private Repository<Integer, Rezervare> rezervari;

    private static final Logger logger = LogManager.getLogger();

//    public Service(Repository<Integer, Excursie> excursii, Repository<Integer, FirmaTransport> firmeTransport, Repository<Integer, ObiectivTuristic> obiectiveTuristice, Repository<Integer, Persoana> persoane, Repository<Integer, User> useri, Repository<Integer, Rezervare> rezervari) {
//        this.excursii = excursii;
//        this.firmeTransport = firmeTransport;
//        this.obiectiveTuristice = obiectiveTuristice;
//        this.persoane = persoane;
//        this.useri = useri;
//        this.rezervari = rezervari;
//    }

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

    public FirmaTransport getNumeFirmaById(Integer idFirma) {
        return firmeTransport.cautaId(idFirma);
    }

    public FirmaTransport getFirmaByNume(String numeFirma) {
        for (FirmaTransport firmaTransport: firmeTransport.getAll()) {
            if(firmaTransport.getNume().equals(numeFirma)){
                return firmaTransport;
            }
        }
        return null;
    }

    public ObiectivTuristic getNumeObiectivById(Integer idObiectiv) {
        return obiectiveTuristice.cautaId(idObiectiv);
    }

    public ObiectivTuristic getObiectivNume(String numeObiectiv) {
        for (ObiectivTuristic obiectiv:obiectiveTuristice.getAll()) {
            if(numeObiectiv.equals(obiectiv.getNume()))
            {
                return obiectiv;
            }
        }
        return null;
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

    public Persoana getPersoanaByNume(String numePersoana, String numarTelefon)
    {
        for(Persoana p: persoane.getAll())
        {
            if(p.getNume().equals(numePersoana) && p.getNumarTelefon().equals(numarTelefon))
            {
                return p;
            }
        }
        return null;
    }

    public void adaugaRezervare(Integer idExcursie,String numeClient, String numarTelefonClient, Integer numarBileteDorite)
    {
        Integer idPersoana = getPersoanaByNume(numeClient, numarTelefonClient).getId();
        Rezervare rezervare = new Rezervare(idExcursie,idPersoana, numarBileteDorite);
        if(rezervare.getNrBilete() <= getNumarLocuriDisponibile(rezervare.getIdExcursie()))
        {
        rezervari.adauga(rezervare);
        }
    }
}
