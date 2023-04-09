package grup.aplicatie;

import grup.Client.ITripClient;
import grup.Client.TripClient;
import grup.Controllers.Controller;
import grup.Controllers.ExcursiiControler;
import grup.Domain.*;
import grup.Repository.*;
import grup.Service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (
                IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }
        Repository<Integer, Excursie> excursii = new ExcursieDBRepo(props);
        Repository<Integer, FirmaTransport> firmeTransport = new FirmaTransportDBRepo(props);
        Repository<Integer, ObiectivTuristic> obiectiveTuristice = new ObiectivTuristicDBRepo(props);
        Repository<Integer, Persoana> persoane = new PersoanaDBRepo(props);
        Repository<Integer, Rezervare> rezervari = new RezervareDBRepo(props);
        Repository<Integer, User> useri = new UserDBRepo(props);
        Service srv = new Service(excursii,firmeTransport,obiectiveTuristice,persoane,useri,rezervari);


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);

        ITripClient client = new TripClient("host");

        Controller controller = fxmlLoader.getController();
        controller.setService(srv);
        controller.setClient(client);

        stage.setTitle("Cea mai tare aplicatie");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}