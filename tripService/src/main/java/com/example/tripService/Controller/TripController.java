package com.example.tripService.Controller;

import com.example.tripService.Domain.*;
import com.example.tripService.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("v1")
@EnableWebSocket
public class TripController extends TextWebSocketHandler implements WebSocketHandler {

    @Autowired
    Service srv;


    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/authenticate")
    public void authenticate(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        srv.inregistrare(email,password);
    }

    @PostMapping("/logout")
    public void logout() {
        // Authenticate
        srv.logout();
    }

    @PostMapping("/query/trips")
    public List<Excursie> queryTrips() {
        return srv.getAllExcursii();
    }

    @PostMapping("/query/trips/obiectiv")
    public List<Excursie> queryTripsObiectiv(@RequestParam(name = "numeObiectiv") String numeObiectiv, @RequestParam(name = "oraMinim") Integer oraMinim, @RequestParam(name = "oraMaxim") Integer oraMaxim) {
        // Query trips

        return srv.getExursiiByObiectivTuristic(numeObiectiv,oraMinim,oraMaxim);
    }

    @PostMapping("/query/firma/id")
    public FirmaTransport queryFirmaId(@RequestParam(name = "idFirma") Integer idFirma) {
        return srv.getNumeFirmaById(idFirma);
    }

    @PostMapping("/query/firma/nume")
    public FirmaTransport queryFirmaNume(@RequestParam(name = "numeFirma") String numeFirma) {

        return srv.getFirmaByNume(numeFirma);
    }

    @PostMapping("/query/trip/left")
    public Left tripsLeft(@RequestParam(name = "idExcursie") Integer idExcursie) {
        Left left = new Left(srv.getNumarLocuriDisponibile(idExcursie));
        return left;
    }
    @PostMapping("/query/obiectiv/id")
    public ObiectivTuristic queryObiectivId(@RequestParam(name = "idObiectiv") Integer idObiectiv) {
        return srv.getNumeObiectivById(idObiectiv);
    }

    @PostMapping("/query/obiectiv/nume")
    public ObiectivTuristic queryObiectivNume(@RequestParam(name = "numeObiectiv") String numeObiectiv) {
        return srv.getObiectivNume(numeObiectiv);
    }

    @PostMapping("/query/persoana/nume")
    public Persoana queryPersoanaNume(@RequestParam(name = "numeClient") String numeClient, @RequestParam(name = "numarTelefon") String numarTelefon) {
        return srv.getPersoanaByNume(numeClient, numarTelefon);
    }

    @PostMapping("/add/rezervare")
    public void addRezervare(@RequestParam(name = "numeClient") String numeClient, @RequestParam(name = "numarTelefon") String numarTelefon, @RequestParam(name ="numarBileteDorite") Integer numarBileteDorite, @RequestParam(name = "idExcursie") Integer idExcursie) {
        srv.adaugaRezervare(idExcursie,numeClient,numarTelefon,numarBileteDorite);
    }
}
