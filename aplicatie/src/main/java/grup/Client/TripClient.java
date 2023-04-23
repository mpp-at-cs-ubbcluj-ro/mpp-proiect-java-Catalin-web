package grup.Client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import grup.Domain.Excursie;
import grup.Domain.FirmaTransport;
import grup.Domain.ObiectivTuristic;
import grup.Domain.Persoana;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class TripClient implements ITripClient{
    private String baseUrl;

    public TripClient(String baseUrl){
        this.baseUrl=baseUrl;
    }

    @Override
    public void login(String email, String parola) throws IOException {
        RequestBuilder requestBuilder = new RequestBuilder(baseUrl,"/v1/login");
        requestBuilder.addValue("email",email);
        requestBuilder.addValue("password",parola);
        URL url = requestBuilder.build();

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        int responseCode = con.getResponseCode();
    }

    @Override
    public void inregistrare(String email, String parola) throws IOException {
        RequestBuilder requestBuilder = new RequestBuilder(baseUrl,"/v1/authenticate");
        requestBuilder.addValue("email",email);
        requestBuilder.addValue("password",parola);
        URL url = requestBuilder.build();

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        int responseCode = con.getResponseCode();
    }

    @Override
    public void logout() throws IOException {
        RequestBuilder requestBuilder = new RequestBuilder(baseUrl,"/v1/logout");
        URL url = requestBuilder.build();

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        int responseCode = con.getResponseCode();
    }

    @Override
    public List<Excursie> getAllExcursii() throws IOException {
        RequestBuilder requestBuilder = new RequestBuilder(baseUrl,"/v1/query/trips");
        URL url = requestBuilder.build();

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        int responseCode = con.getResponseCode();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String output;
        List<Excursie> lst = new ArrayList<>();
        while ((output = br.readLine()) != null) {
            Type type = new TypeToken<List<Map<String, Object>>>(){}.getType();
            List<Map<String, Object>> hashMapList = new Gson().fromJson(output, type);

            // Print the list of hash maps
            for (Map<String, Object> map : hashMapList) {
                Excursie excursie = Excursie.deserialize(map);
                lst.add(excursie);
            }
        }
        return lst;
    }

    @Override
    public String getNumeFirmaById(Integer idFirma) throws IOException {
        RequestBuilder requestBuilder = new RequestBuilder(baseUrl,"/v1/query/firma/id");
        requestBuilder.addValue("idFirma", idFirma.toString());
        URL url = requestBuilder.build();

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        int responseCode = con.getResponseCode();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String output;
        while ((output = br.readLine()) != null) {
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            Map<String, Object> map = new Gson().fromJson(output, type);

            FirmaTransport excursie = FirmaTransport.deserialize(map);
            return excursie.getNume();
        }
        return null;
    }

    @Override
    public String getNumeObiectivById(Integer idObiectiv) throws IOException {
        RequestBuilder requestBuilder = new RequestBuilder(baseUrl,"/v1/query/obiectiv/id");
        requestBuilder.addValue("idObiectiv", idObiectiv.toString());
        URL url = requestBuilder.build();

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        int responseCode = con.getResponseCode();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String output;
        while ((output = br.readLine()) != null) {
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            Map<String, Object> map = new Gson().fromJson(output, type);

            ObiectivTuristic obiectivTuristic = ObiectivTuristic.deserialize(map);
            return obiectivTuristic.getNume();
        }
        return null;
    }

    @Override
    public Integer getNumarLocuriDisponibile(Integer idExcursie) throws IOException {
        RequestBuilder requestBuilder = new RequestBuilder(baseUrl,"/v1/query/trip/left");
        requestBuilder.addValue("idExcursie", idExcursie.toString());
        URL url = requestBuilder.build();

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        int responseCode = con.getResponseCode();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String output;
        while ((output = br.readLine()) != null) {
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            Map<String, Object> map = new Gson().fromJson(output, type);

            return ((Double) map.get("left")).intValue();
        }
        return null;
    }

    @Override
    public ObiectivTuristic getObiectivTuristicByNume(String numeObiectiv) throws IOException {
        RequestBuilder requestBuilder = new RequestBuilder(baseUrl,"/v1/query/obiectiv/nume");
        requestBuilder.addValue("numeObiectiv", numeObiectiv);
        URL url = requestBuilder.build();

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        int responseCode = con.getResponseCode();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String output;
        while ((output = br.readLine()) != null) {
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            Map<String, Object> map = new Gson().fromJson(output, type);

            return ObiectivTuristic.deserialize(map);
        }
        return null;
    }

    @Override
    public List<Excursie> getExursiiByObiectivTuristic(String numeObiectiv, Integer minOra, Integer maxOra) throws IOException {
        RequestBuilder requestBuilder = new RequestBuilder(baseUrl,"/v1/query/trips/obiectiv");
        requestBuilder.addValue("numeObiectiv",numeObiectiv);
        requestBuilder.addValue("oraMinim",minOra.toString());
        requestBuilder.addValue("oraMaxim",maxOra.toString());
        URL url = requestBuilder.build();

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        int responseCode = con.getResponseCode();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String output;
        List<Excursie> lst = new ArrayList<>();
        while ((output = br.readLine()) != null) {
            Type type = new TypeToken<List<Map<String, Object>>>(){}.getType();
            List<Map<String, Object>> hashMapList = new Gson().fromJson(output, type);

            // Print the list of hash maps
            for (Map<String, Object> map : hashMapList) {
                Excursie excursie = Excursie.deserialize(map);
                lst.add(excursie);
            }
        }
        return lst;
    }

    @Override
    public Integer getIdPersoanaByNume(String numePersoana, String numarTelefon) throws IOException {
        RequestBuilder requestBuilder = new RequestBuilder(baseUrl,"/v1/query/persoana/nume");
        requestBuilder.addValue("numePersoana", numePersoana);
        requestBuilder.addValue("numarTelefon", numarTelefon);
        URL url = requestBuilder.build();

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        int responseCode = con.getResponseCode();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String output;
        while ((output = br.readLine()) != null) {
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            Map<String, Object> map = new Gson().fromJson(output, type);

            return Persoana.deserialize(map).getId();
        }
        return null;
    }

    @Override
    public void adaugaRezervare(Integer idExcursie, String numeClient, String numarTelefonClient, Integer numarBileteDorite) throws IOException {
        RequestBuilder requestBuilder = new RequestBuilder(baseUrl,"/v1/add/rezervare");
        requestBuilder.addValue("numeClient",numeClient);
        requestBuilder.addValue("numarTelefon", numarTelefonClient);
        requestBuilder.addValue("numarBileteDorite", numarBileteDorite.toString());
        requestBuilder.addValue("idExcursie", idExcursie.toString());
        URL url = requestBuilder.build();

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        int responseCode = con.getResponseCode();
    }

    public StompSession handleWebSocket(Runnable callback) throws Exception {
        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        ClientOneSessionHandler clientOneSessionHandler = new ClientOneSessionHandler();
        clientOneSessionHandler.setCallBack(callback);

        ListenableFuture<StompSession> sessionAsync =
                stompClient.connect("ws://localhost:12500/websocket-server", clientOneSessionHandler);
        StompSession session = sessionAsync.get();
        session.subscribe("/topic/messages", clientOneSessionHandler);
        return session;
    }
}

class ClientOneSessionHandler extends StompSessionHandlerAdapter {
    private Runnable callback;

    public void setCallBack(Runnable callback){
        this.callback=callback;
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        callback.run();
        return String.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        callback.run();
    }
}
