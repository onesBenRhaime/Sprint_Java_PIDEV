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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import models.TypeCompte;
import services.TypeCompteCRUD;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class AjouterTypeCompteAdminController implements Initializable {

    @FXML
    private Button profilfx;
    @FXML
    private TextField type;
    @FXML
    private TextField description;
    @FXML
    private TextField id;
    @FXML
    private Button annuler;
    @FXML
    private Label welcomeLb;
    TypeCompteCRUD typeCompteService = new TypeCompteCRUD();

    ObservableList<TypeCompte> obs;
    
    /**
     * Initializes the controller class.
     */
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
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

   

    @FXML
    private void AjouterTypeCompte(ActionEvent event) {
         try {             
                if (type.getText().isEmpty() || description.getText().isEmpty()  ) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Echec de l'ajout");
                      alert.setHeaderText(null);
                      alert.setContentText("Attention ! Veuillez saisir toutes les données requises.");
                      alert.showAndWait();
                   return;
                }  
                 if (type.getText().matches(".*\\d.*")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Echec de l'ajout");
                      alert.setHeaderText(null);
                      alert.setContentText("Attention ! Type doit etre de type String.");
                      alert.showAndWait();
                   return;
                } 
                if (typeCompteService.Existe(type.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Echec de l'ajout");
                      alert.setHeaderText(null);
                      alert.setContentText("Attention ! un type de compte portant le même nom existe déjà dans votre base de données");
                      alert.showAndWait();
                   return;
                }  
                String typec = type.getText();
                String descriptionc = description.getText();
           
            TypeCompte tc = new TypeCompte(typec,descriptionc);
            typeCompteService.ajouter(tc);
            
                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                    alert0.setTitle("Boîte de dialogue d'information");
                    alert0.setHeaderText(null);
                    alert0.setContentText("Ajoutée avec succés ");
                    alert0.show();
             
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void Annuler(ActionEvent event) throws IOException {
           type.clear();
         description.clear();
       
       
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
    
}
