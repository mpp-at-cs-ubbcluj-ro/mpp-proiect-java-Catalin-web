package grup.Client;

import grup.Domain.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public interface ITripClient {
    void login(String email, String parola) throws IOException;

    void inregistrare(String email, String parola);

    void logout();

    List<Excursie> getAllExcursii();

    String getNumeFirmaById(Integer idFirma);

    String getNumeObiectivById(Integer idObiectiv);

    Integer getNumarLocuriDisponibile(Integer idExcursie);

    ObiectivTuristic getObiectivTuristicByNume(String numeObiectiv);

    List<Excursie> getExursiiByObiectivTuristic(String numeObiectiv, Integer minOra, Integer maxOra);

    Integer getIdPersoanaByNume(String numePersoana, String numarTelefon);

    void adaugaRezervare(Integer idExcursie,String numeClient, String numarTelefonClient, Integer numarBileteDorite);
}
