/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Transaction;
import services.TransactionCRUD;
/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class AjouterWireTransferClientController implements Initializable {

    @FXML
    private Button Accueilfx1;
    @FXML
    private Button Accueilfx11;
    @FXML
    private Button Accueilfx14;
    @FXML
    private Button Accueilfx15;
    @FXML
    private Button Accueilfx16;
    @FXML
    private Button Accueilfx161;
    @FXML
    private TextField accountNumT;
    @FXML
    private TextField amountT;
    @FXML
    private TextField requestToT;
    @FXML
    private TextField requestFromT;
    @FXML
    private Button annuler;
    @FXML
    private TextField agenceNameT;
    TransactionCRUD transactionService = new TransactionCRUD();
    ObservableList<Transaction> obs;
    @FXML
    private Label welcomeLb;
   
      // Methode Num ou Non
         public boolean isNumeric (String s) {
           try {
               int x = Integer.parseInt(s);    
             }catch (NumberFormatException ex) {
                    return false;
             }
                    return true;
            //  return !s.matches("[^a-zA-Z]*");
          }//End

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void accueilAction(ActionEvent event) {
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ComptesClient.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void AjouterTransaction(ActionEvent event) {
        try {             
                if (agenceNameT.getText().isEmpty() || accountNumT.getText()== null
                                                    || amountT.getText().isEmpty()||requestFromT.getText().isEmpty() ||requestToT.getText().isEmpty()  ) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Failed to add");
                      alert.setHeaderText(null);
                      alert.setContentText("Attention ! Please enter all required data.");
                      alert.showAndWait();
                   return;
                }                                         
                         
                if (isNumeric(amountT.getText())==false ) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Failed to add");
                      alert.setHeaderText(null);
                      alert.setContentText("Attention! Please make sure that the amount is entered as a numerical value.");
                      alert.showAndWait();
                   return;
                }
                 
                 if (accountNumT.getText().length() != 14 || requestToT.getText().length() != 14 ){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Failed to add");
                      alert.setHeaderText(null);
                      alert.setContentText("Attention!  Account Number  , Request To   : must be 14 integer");
                      alert.showAndWait();
                   return;
                }
//                 if (accountNumT.getText() != requestFromT.getText() ){
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                      alert.setTitle("Failed to add");
//                      alert.setHeaderText(null);
//                      alert.setContentText("Attention! Account number must equal Request From (must be the same account)");
//                      alert.showAndWait();
//                   return;
//                }
                if (transactionService.AgenceExiste(agenceNameT.getText()) ==false ){
                    System.out.println( transactionService.AgenceExiste(agenceNameT.getText())); 
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Failed to add");
                      alert.setHeaderText(null);
                      alert.setContentText("Attention! No agence with this name)");
                      alert.showAndWait();
                   return;
                }
                String agenceName = agenceNameT.getText();                
            //   String accountNumStr = accountNumT.getText();
                String amount = amountT.getText();
                String requestFrom = requestFromT.getText();
                String requestTo = requestToT.getText();
                
           
            Transaction tc = new Transaction(23, amount,  requestFrom , requestTo, agenceName);
            transactionService.ajouter(tc);
            
                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                    alert0.setTitle("Information dialog box");
                    alert0.setHeaderText(null);
                    alert0.setContentText("Added successfully ");
                    alert0.show();
             
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }


    @FXML
    private void Annuler(ActionEvent event) {
             amountT.clear();
            requestFromT.clear();
            requestToT.clear();
            accountNumT.clear();
            agenceNameT.clear();
            
               
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
    
}
