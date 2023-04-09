package grup.aplicatie;

import grup.Client.ITripClient;
import grup.Client.TripClient;
import grup.Controllers.Controller;
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


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);

        ITripClient client = new TripClient("http://localhost:12500");

        Controller controller = fxmlLoader.getController();
        controller.setClient(client);

        stage.setTitle("Cea mai tare aplicatie");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}