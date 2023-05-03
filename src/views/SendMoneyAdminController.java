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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Transaction;
import services.TransactionCRUD;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class SendMoneyAdminController implements Initializable {

    @FXML
    private Label welcomeLb;
    @FXML
    private Button profilfx;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TableView<Transaction> tableview;
    @FXML
    private TableColumn<Transaction, Date> dateCol;
    @FXML
    private TableColumn<Transaction, String> amountCol;
    @FXML
    private TableColumn<Transaction, String> reqFromCol;
    @FXML
    private TableColumn<Transaction, String>reqToCol;
  TransactionCRUD transactionService = new TransactionCRUD();
    ObservableList<Transaction> obs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try {
            List<Transaction> transactions = transactionService.recupererSendMoney();
            obs = FXCollections.observableArrayList(transactions);
            tableview.setItems(obs); 
            
            dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
            amountCol.setCellValueFactory(new PropertyValueFactory<>("montant"));
            reqFromCol.setCellValueFactory(new PropertyValueFactory<>("requestFrom"));
            reqToCol.setCellValueFactory(new PropertyValueFactory<>("requestTo"));
          
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
    private void goToHome(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
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

}
