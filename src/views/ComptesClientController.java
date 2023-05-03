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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Compte;
import services.CompteCRUD;
/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class ComptesClientController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private TableView<Compte> tableview;
    @FXML
    private Label welcomeLb;
    @FXML
    private TableColumn<Compte, String> accountType;
    @FXML
    private TableColumn<Compte, Date>  dateC;
    @FXML
    private TableColumn<Compte, Date> dateF;
    @FXML
    private TableColumn<Compte, String> soldefx;
    @FXML
    private TableColumn<Compte, String> redSfx;
    @FXML
    private TableColumn<Compte, String> minSfx;
    @FXML
    private TableColumn<Compte, String>  maxSfx;
    @FXML
    private TableColumn<Compte, String>  statuefx;
   
    
     CompteCRUD compteService = new  CompteCRUD();
     ObservableList<Compte> obs;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
                List<Compte> comptes = compteService.recuperer();
                System.out.println(comptes);
                obs = FXCollections.observableArrayList(comptes);
                tableview.setItems(obs); 
                accountType.setCellValueFactory(new PropertyValueFactory<>("idTypeId"));          
                dateC.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
                dateF.setCellValueFactory(new PropertyValueFactory<>("dateFermeture"));
                soldefx.setCellValueFactory(new PropertyValueFactory<>("solde"));
                redSfx.setCellValueFactory(new PropertyValueFactory<>("redSolde"));
                maxSfx.setCellValueFactory(new PropertyValueFactory<>("maxSolde"));
                minSfx.setCellValueFactory(new PropertyValueFactory<>("minSolde"));
                statuefx.setCellValueFactory(new PropertyValueFactory<>("staute"));               

            } catch (SQLException ex) {
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
    private void Delete(ActionEvent event) throws SQLException {
        int selectedIndex = obs.indexOf( tableview.getSelectionModel().getSelectedItem());
        if (selectedIndex >= 0) {
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
                compteService.supprimer(obs.get(selectedIndex));
                obs.remove(selectedIndex);
                
            } 
        }
    }
    private void Delails(ActionEvent event) {
        
        try {
            //1.Créer la fenetre
            Stage s=new Stage();
            //2.charger le fichier FXML
            FXMLLoader lo = new FXMLLoader (getClass().getResource("DetailsAccount.fxml"));
            //3.charger  le contenuer du fichier nouvelle.fxml
            Parent root = lo.load();
            //4.cree la scenne
            Scene sc =new Scene (root);
            //5 lier  la scenne  sc au stage a cree
            s.setScene(sc);
            //6.afficher la stage
            s.show();
        } catch (IOException ex) {
            Logger.getLogger(ComptesClientController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
}
