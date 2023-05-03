/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import services.PdfTransaction;
import services.SmsServicee;
import services.TransactionCRUD;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class WireTransferAdminController implements Initializable {

    @FXML
    private Label welcomeLb;
    @FXML
    private Button profilfx;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TableView<Transaction> tableview;
    @FXML
    private TableColumn<Transaction, String> amountCol;
    @FXML
    private TableColumn<Transaction, String> reqFromCol;
    @FXML
    private TableColumn<Transaction, String> reqToCol;
    @FXML
    private TableColumn<Transaction, String> statusCol;
    @FXML
    private TableColumn<Transaction, String> agNameCol;
    @FXML
    private TableColumn<Transaction, Date> dateCol;
    @FXML
    private TableColumn<Transaction, String> ribcol;
    @FXML
    private TextField searchField;

    TransactionCRUD transactionService = new TransactionCRUD();
    ObservableList<Transaction> obs;
    @FXML
    private Button showC;
    @FXML
    private Button imprimer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Transaction> transactions = transactionService.recuperer();
            obs = FXCollections.observableArrayList(transactions);
            tableview.setItems(obs);
            ribcol.setCellValueFactory(new PropertyValueFactory<>("compteid"));
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
    private void goToAccounts(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TypeCompteAdmin.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void goToTransactions(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WireTransferAdmin.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void goToSendMoney(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SendMoneyAdmin.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void goToAccountsDemonds(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ComptesAdmin.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void goToHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
        }

    }

    @FXML
    private void Reject(ActionEvent event) throws SQLException {

        int selectedIndex = obs.indexOf(tableview.getSelectionModel().getSelectedItem());
        // System.out.println( obs.get(selectedIndex).getStatue().equalsIgnoreCase("in progress"));
        if (selectedIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed ");
            alert.setHeaderText(null);
            alert.setContentText("no selected ");
            alert.showAndWait();
            return;
        } else if (!obs.get(selectedIndex).getStatue().equalsIgnoreCase("rejected")) {
            // Créer une boîte de dialogue d'alerte
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Reject");
            alert.setHeaderText("Do you really want to Reject this transaction ?");

            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                transactionService.reject(obs.get(selectedIndex));
                   SmsServicee sms = new SmsServicee();
                    
                    String message ="Votre Transaction à ete réfusé";
                      sms.sendSms(message);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed to reject");
            alert.setHeaderText(null);
            alert.setContentText("This transaction is already Rejected");
            alert.showAndWait();
            return;
        }

    }

    @FXML
    private void Accept(ActionEvent event) throws SQLException {

        int selectedIndex = obs.indexOf(tableview.getSelectionModel().getSelectedItem());
        if (selectedIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed ");
            alert.setHeaderText(null);
            alert.setContentText("no selected ");
            alert.showAndWait();
            return;
        } else if (!obs.get(selectedIndex).getStatue().equalsIgnoreCase("in progress")) {
            // Créer une boîte de dialogue d'alerte
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Accept");
            alert.setHeaderText("Do you really want to Accept this transaction ?");

            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                transactionService.accept(obs.get(selectedIndex));                
                   //MailerService ms = new MailerService();
                  //ms.sendLivraisonMail(livrai, livrai.getC1());
                    SmsServicee sms = new SmsServicee();
                
                    String message ="Votre Transaction à ete accepté";
                    
                  sms.sendSms( message);
                
                
                
                
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed to Accept");
            alert.setHeaderText(null);
            alert.setContentText("This transaction is already accepted");
            alert.showAndWait();
            return;
        }

    }

    
    @FXML
    private void refresh(ActionEvent event) throws SQLException {
        try {
            List<Transaction> transactions = transactionService.recuperer();
            obs = FXCollections.observableArrayList(transactions);
            tableview.setItems(obs);
            ribcol.setCellValueFactory(new PropertyValueFactory<>("compteid"));
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
    private void Pdf(ActionEvent event) {
             int selectedIndex = obs.indexOf(tableview.getSelectionModel().getSelectedItem());
                
            PdfTransaction pdf = new PdfTransaction();       
            pdf.orderPdf(obs.get(selectedIndex));
             Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("Information dialog box");
            alert0.setHeaderText(null);
            alert0.setContentText("The pdf file has been exported successfully. ");
            alert0.show();
        
    }
    
    
}
