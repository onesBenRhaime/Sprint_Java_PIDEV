/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Transaction;
import services.TransactionCRUD;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class SendMoneyClientController implements Initializable {

    @FXML
    private Label welcomeLb;
    @FXML
    private TableView<Transaction> tableview;
 
    @FXML
    private TableColumn<Transaction, String>  amountCol;
    @FXML
    private TableColumn<Transaction, String>  reqFromCol;
    @FXML
    private TableColumn<Transaction, String> reqToCol;
    @FXML
    private TableColumn<Transaction, String>  dateCol;

    
     TransactionCRUD transactionService = new TransactionCRUD();
     ObservableList<Transaction> obs;
    @FXML
    private ScrollPane scroll;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
      try {
            List<Transaction> requestMoney = transactionService.recupererSendMoney();
            obs = FXCollections.observableArrayList(requestMoney);
            tableview.setItems(obs);    
            System.out.println(" obs : "+obs);
            System.out.println(" tableview : "+tableview);
                 
            reqToCol.setCellValueFactory(new PropertyValueFactory<>("requestTo"));
                System.out.println(" reqToCol : "+reqToCol);
            reqFromCol.setCellValueFactory(new PropertyValueFactory<>("requestTo"));
            amountCol.setCellValueFactory(new PropertyValueFactory<>("montant"));
             System.out.println(" amountCol : "+amountCol);
            dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));    
            System.out.println("requestMoney:" +requestMoney );
           
          
         } catch (SQLException ex) {
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
    private void goToAjouter(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterSendMoneyClient.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void goToAdmin(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }


    
}
