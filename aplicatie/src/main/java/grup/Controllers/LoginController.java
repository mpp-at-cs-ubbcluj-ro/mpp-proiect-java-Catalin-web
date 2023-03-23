package grup.Controllers;

import grup.Service.Service;
import grup.aplicatie.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController implements Controller{
    private Service srv;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passField;


    @FXML
    public void initialize() {
    }

    public void onLogin(ActionEvent actionEvent) throws IOException {
        srv.login(emailField.getText(),passField.getText());
        travelPage(actionEvent);
    }

    public void onRegister(ActionEvent actionEvent) throws IOException {
        srv.inregistrare(emailField.getText(),passField.getText());
        travelPage(actionEvent);
    }

    @Override
    public void setService(Service srv) {
        this.srv = srv;
    }

    private void travelPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("excursii.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        Controller loginController = (ExcursiiControler)fxmlLoader.getController();

        loginController.setService(srv);
        stage.show();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
