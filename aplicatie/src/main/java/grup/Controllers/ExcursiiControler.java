package grup.Controllers;

import grup.Client.ITripClient;
import grup.Domain.Excursie;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.util.List;

public class ExcursiiControler implements Controller {

    private ObservableList<Excursie> model= FXCollections.observableArrayList();
    private ObservableList<Excursie> model2= FXCollections.observableArrayList();
    @FXML
    TableView<Excursie> tabelExcursii;
    @FXML
    TableColumn<Excursie, String> numeObiectivColumn;
    @FXML
    TableColumn<Excursie, String> numeFirmaColumn;
    @FXML
    TableColumn<Excursie, Integer> oraColumn;
    @FXML
    TableColumn<Excursie, Float> pretColumn;
    @FXML
    TableColumn<Excursie, Integer> locuriDisponibileColumn;

    @FXML
    TableView<Excursie> tabelExcursii2;
    @FXML
    TableColumn<Excursie, String> numeObiectivColumn2;
    @FXML
    TableColumn<Excursie, String> numeFirmaColumn2;
    @FXML
    TableColumn<Excursie, Integer> oraColumn2;
    @FXML
    TableColumn<Excursie, Float> pretColumn2;
    @FXML
    TableColumn<Excursie, Integer> locuriDisponibileColumn2;

    @FXML
    TextField numeObiectiv;
    @FXML
    TextField minOra;
    @FXML
    TextField maxOra;

    @FXML
    TextField numeClient;
    @FXML
    TextField numarBilete;
    @FXML
    TextField numarTelefon;

    @FXML
    public void initialize() {
        numeObiectivColumn.setCellValueFactory(c -> {
            Excursie entity=c.getValue();
            String valoareCelula = null;
            try {
                valoareCelula = client.getNumeObiectivById(entity.getIdObiectiv());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return new ReadOnlyObjectWrapper<String>(valoareCelula);}
        );
        numeFirmaColumn.setCellValueFactory(c -> {
            Excursie entity=c.getValue();
            String valoareCelula = null;
            try {
                valoareCelula = client.getNumeFirmaById(entity.getIdFirmaTransport());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return new ReadOnlyObjectWrapper<String>(valoareCelula);}
        );

        oraColumn.setCellValueFactory(c -> {
            Excursie entity=c.getValue();
            return new ReadOnlyObjectWrapper<Integer>(entity.getOra());}
        );

        pretColumn.setCellValueFactory(c -> {
            Excursie entity=c.getValue();
            return new ReadOnlyObjectWrapper<Float>(entity.getPret());}
        );

        locuriDisponibileColumn.setCellValueFactory(c -> {
            Excursie entity=c.getValue();
            Integer locuriDisponibile = null;
            try {
                locuriDisponibile = client.getNumarLocuriDisponibile(entity.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return new ReadOnlyObjectWrapper<Integer>(locuriDisponibile);}
        );

        tabelExcursii.setItems(model);

        numeFirmaColumn2.setCellValueFactory(c -> {
            Excursie entity=c.getValue();
            String valoareCelula = null;
            try {
                valoareCelula = client.getNumeFirmaById(entity.getIdFirmaTransport());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return new ReadOnlyObjectWrapper<String>(valoareCelula);}
        );

        oraColumn2.setCellValueFactory(c -> {
            Excursie entity=c.getValue();
            return new ReadOnlyObjectWrapper<Integer>(entity.getOra());}
        );

        pretColumn2.setCellValueFactory(c -> {
            Excursie entity=c.getValue();
            return new ReadOnlyObjectWrapper<Float>(entity.getPret());}
        );

        locuriDisponibileColumn2.setCellValueFactory(c -> {
            Excursie entity=c.getValue();
            Integer locuriDisponibile = null;
            try {
                locuriDisponibile = client.getNumarLocuriDisponibile(entity.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return new ReadOnlyObjectWrapper<Integer>(locuriDisponibile);}
        );
        tabelExcursii2.setItems(model2);
        red();
    }

    private void red(){
        locuriDisponibileColumn.setCellFactory(column -> {
            return new TableCell<Excursie, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {

                        setText(item.toString());

                        if (item.equals(0)) {
                            setTextFill(Paint.valueOf("red"));
                        }
                    }
                }
            };
        });

        locuriDisponibileColumn2.setCellFactory(column -> {
            return new TableCell<Excursie, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {

                        setText(item.toString());

                        if (item.equals(0)) {
                            setTextFill(Paint.valueOf("red"));
                        }
                    }
                }
            };
        });
    }

    private ITripClient client;

    @Override
    public void setClient(ITripClient client) throws IOException {
        this.client = client;
        List<Excursie> lst = client.getAllExcursii();
        model.setAll(lst);
    }

    public void cautaOviectiv(ActionEvent actionEvent) throws IOException {
        reloadTables();
    }

    public void rezervaBilet(ActionEvent actionEvent) throws IOException {
        String nume = numeClient.getText();
        Integer nrBilete = Integer.parseInt(numarBilete.getText());
        String nrTelefon = numarTelefon.getText();
        Excursie excursie = tabelExcursii2.getSelectionModel().getSelectedItem();
        client.adaugaRezervare(excursie.getId(),nume,nrTelefon,nrBilete);

        model.setAll(client.getAllExcursii());
        reloadTables();
    }

    private void reloadTables() throws IOException {
        String nume = numeObiectiv.getText();
        Integer min = Integer.parseInt(minOra.getText());
        Integer max =Integer.parseInt(maxOra.getText());
        model2.setAll(client.getExursiiByObiectivTuristic(nume,min,max));
        model.setAll(client.getAllExcursii());
    }
}
