package grup.Client;

import grup.Domain.Excursie;
import grup.Domain.ObiectivTuristic;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TripClient implements ITripClient{
    private String baseUrl;

    public TripClient(String baseUrl){
        this.baseUrl=baseUrl;
    }

    @Override
    public void login(String email, String parola) throws IOException {
        String path = "http://localhost:12500/v1/authenticate?email="+email+"&password="+parola;
        URL url = new URL(path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        int responseCode = con.getResponseCode();
    }

    @Override
    public void inregistrare(String email, String parola) {

    }

    @Override
    public void logout() {

    }

    @Override
    public List<Excursie> getAllExcursii() {
        return null;
    }

    @Override
    public String getNumeFirmaById(Integer idFirma) {
        return null;
    }

    @Override
    public String getNumeObiectivById(Integer idObiectiv) {
        return null;
    }

    @Override
    public Integer getNumarLocuriDisponibile(Integer idExcursie) {
        return null;
    }

    @Override
    public ObiectivTuristic getObiectivTuristicByNume(String numeObiectiv) {
        return null;
    }

    @Override
    public List<Excursie> getExursiiByObiectivTuristic(String numeObiectiv, Integer minOra, Integer maxOra) {
        return null;
    }

    @Override
    public Integer getIdPersoanaByNume(String numePersoana, String numarTelefon) {
        return null;
    }

    @Override
    public void adaugaRezervare(Integer idExcursie, String numeClient, String numarTelefonClient, Integer numarBileteDorite) {

    }
}
