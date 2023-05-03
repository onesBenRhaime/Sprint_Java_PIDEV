/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.sun.management.jmx.Trace;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import models.Transaction;
import services.TransactionCRUD;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class WireTransferClientController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private TableView<Transaction> tableview;

    @FXML
    private TableColumn<Transaction, String> amountCol;
    @FXML
    private TableColumn<Transaction, String> reqToCol;
    @FXML
    private TableColumn<Transaction, String> reqFromCol;
    @FXML
    private TableColumn<Transaction, String> statusCol;
    @FXML
    private TableColumn<Transaction, String> agNameCol;
    @FXML
    private TableColumn<Transaction, String> dateCol;
    @FXML
    private Label welcomeLb;
    /**
     * Initializes the controller class.
     */
    TransactionCRUD transactionService = new TransactionCRUD();
    ObservableList<Transaction> obs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            List<Transaction> transactions = transactionService.recuperer();
            obs = FXCollections.observableArrayList(transactions);
            tableview.setItems(obs);
            amountCol.setCellValueFactory(new PropertyValueFactory<>("montant"));
            reqFromCol.setCellValueFactory(new PropertyValueFactory<>("requestFrom"));
            reqToCol.setCellValueFactory(new PropertyValueFactory<>("requestTo"));
            statusCol.setCellValueFactory(new PropertyValueFactory<>("statue"));
            agNameCol.setCellValueFactory(new PropertyValueFactory<>("agenceName"));
            dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void goToTransactions(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WireTransferClient.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void goToSendMoney(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SendMoneyClient.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void goToAccounts(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TypeCompteClient.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void goToAjouter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterWireTransferClient.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void goToHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Client.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void Delete(ActionEvent event) throws SQLException {
        int selectedIndex = obs.indexOf(tableview.getSelectionModel().getSelectedItem());
       // System.out.println( obs.get(selectedIndex).getStatue().equalsIgnoreCase("in progress"));
        if ( (selectedIndex >= 0 )&& obs.get(selectedIndex).getStatue().equalsIgnoreCase("in progress")) {
            // Créer une boîte de dialogue d'alerte
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Do you really want to delete the selected line ?");

            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                // Supprimer la ligne sélectionnée                
                    transactionService.supprimer(obs.get(selectedIndex));
                    obs.remove(selectedIndex);             
            }
        }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Failed to delete");
                      alert.setHeaderText(null);
                      alert.setContentText("You cannot  remove this transaction");
                      alert.showAndWait();
                   return;
        }

    }

}
