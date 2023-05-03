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
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Parent;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.control.ScrollPane;
    import javafx.scene.control.TableColumn;
    import javafx.scene.control.TableView;
    import javafx.scene.control.TextField;

    

    
    import models.Transaction;
    import services.TransactionCRUD;


    /**
    * FXML Controller class
    *
    * @author BAZINFO
    */
    public class AjouterSendMoneyClientController implements Initializable {
    @FXML
    private Label welcomeLb;
    private TextField description;
    @FXML
    private Button annuler;

    TransactionCRUD transactionService = new TransactionCRUD();
    @FXML
    private TextField reqTo;
    @FXML
    private TextField reqFrom;
    @FXML
    private TextField amount;
    private TextField tcode;

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
    private void Ajouter(ActionEvent event) {

        try {             
                if (amount.getText().isEmpty() || reqFrom.getText()== null
                                                    || reqTo.getText().isEmpty() ) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Failed to add");
                      alert.setHeaderText(null);
                      alert.setContentText("Attention ! Please enter all required data.");
                      alert.showAndWait();
                   return;
                }                                

                if (isNumeric(amount.getText())==false ) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Failed to add");
                      alert.setHeaderText(null);
                      alert.setContentText("Attention! Please make sure that the amount is entered as a numerical value.");
                      alert.showAndWait();
                   return;
                }
                 if (isNumeric(reqFrom.getText())==true || isNumeric(reqTo.getText())==true ){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Failed to add");
                      alert.setHeaderText(null);
                      alert.setContentText("Attention! Please make sure that  request from and your RIB  is entered as a numerical value.");
                      alert.showAndWait();
                   return;
                }
                 if (reqFrom.getText().length() != 14|| reqTo.getText().length() != 14 ){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Failed to add");
                      alert.setHeaderText(null);
                      alert.setContentText("Attention! Request From, Request To  : must be 14 integer");
                      alert.showAndWait();
                   return;
                }
                String amountT = amount.getText();                
                String requestFrom = reqFrom.getText();
                String requestTo = reqTo.getText();                

            Transaction tc = new Transaction(23, amountT,  requestFrom , requestTo);
            transactionService.ajouterSendMoney(tc);

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
        private void Annuler(ActionEvent event)  throws IOException {
                amount.clear();
                reqFrom.clear();
                reqTo.clear();

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
