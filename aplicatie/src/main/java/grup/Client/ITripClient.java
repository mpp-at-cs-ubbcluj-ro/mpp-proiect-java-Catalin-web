package grup.Client;

import grup.Domain.Excursie;
import grup.Domain.ObiectivTuristic;
import org.springframework.messaging.simp.stomp.StompSession;

import java.io.IOException;
import java.util.List;

public interface ITripClient {
    void login(String email, String parola) throws IOException;

    void inregistrare(String email, String parola) throws IOException;

    void logout() throws IOException;

    List<Excursie> getAllExcursii() throws IOException;

    String getNumeFirmaById(Integer idFirma) throws IOException;

    String getNumeObiectivById(Integer idObiectiv) throws IOException;

    Integer getNumarLocuriDisponibile(Integer idExcursie) throws IOException;

    ObiectivTuristic getObiectivTuristicByNume(String numeObiectiv) throws IOException;

    List<Excursie> getExursiiByObiectivTuristic(String numeObiectiv, Integer minOra, Integer maxOra) throws IOException;

    Integer getIdPersoanaByNume(String numePersoana, String numarTelefon) throws IOException;

    void adaugaRezervare(Integer idExcursie,String numeClient, String numarTelefonClient, Integer numarBileteDorite) throws IOException;

    StompSession handleWebSocket(Runnable callback) throws Exception;
}
