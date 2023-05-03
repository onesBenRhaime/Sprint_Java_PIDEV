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
import javafx.scene.control.cell.PropertyValueFactory;
import models.Compte;
import models.Transaction;
import services.CompteCRUD;
import services.TypeCompteCRUD;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class ComptesAdminController implements Initializable {

    @FXML
    private Label welcomeLb;
    @FXML
    private ScrollPane scroll;
    @FXML
    private TableView<Compte> tableview;
    @FXML
    private TableColumn<Compte, Integer> type;
    @FXML
    private TableColumn<Compte, Date> dateCol;
    @FXML
    private TableColumn<Compte, String> usercol;
    @FXML
    private TableColumn<Compte, String> statuecol;
    @FXML
    private Button profilfx;
    @FXML
    private TableColumn<Compte, String> maxSoldefx;
    @FXML
    private TableColumn<Compte, String> minSoldefx;
    @FXML
    private TableColumn<Compte, String> redSoldefx;


    /**
     * Initializes the controller class.
     */
    CompteCRUD compteService = new CompteCRUD();
    TypeCompteCRUD typeCompteService=new TypeCompteCRUD();
    ObservableList<Compte> obs;
    @FXML
    private Button showC;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Compte> comptes = compteService.recuperer();
            System.out.println(comptes);
            obs = FXCollections.observableArrayList(comptes);
            tableview.setItems(obs);
        //    typeCompteService.NameTypeByID(comptes);
            type.setCellValueFactory(new PropertyValueFactory<>("idTypeId"));
            dateCol.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
            usercol.setCellValueFactory(new PropertyValueFactory<>("idUserId"));
            statuecol.setCellValueFactory(new PropertyValueFactory<>("staute"));
            maxSoldefx.setCellValueFactory(new PropertyValueFactory<>("maxSolde"));
            minSoldefx.setCellValueFactory(new PropertyValueFactory<>("minSolde"));
            redSoldefx.setCellValueFactory(new PropertyValueFactory<>("redSolde"));

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
    private void details(ActionEvent event) {
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
        } else if (!obs.get(selectedIndex).getStaute().equalsIgnoreCase("rejected")) {
            // Créer une boîte de dialogue d'alerte
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Reject");
            alert.setHeaderText("Do you really want to Reject this Account Demand ?");

            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                compteService.reject(obs.get(selectedIndex));
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed to reject");
            alert.setHeaderText(null);
            alert.setContentText("This  Demand is already Rejected");
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
        } else if (obs.get(selectedIndex).getStaute().equalsIgnoreCase("in progress")) {
            // Créer une boîte de dialogue d'alerte
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Accept");
            alert.setHeaderText("Do you really want to Accept this Demand ?");

            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                compteService.accept(obs.get(selectedIndex));
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed to Accept");
            alert.setHeaderText(null);
            alert.setContentText("This Account Demand  is already accepted");
            alert.showAndWait();
            return;
        }

    }

    
    @FXML
    private void refresh(ActionEvent event) throws SQLException {
        try {
            List<Compte> comptes = compteService.recuperer();
            System.out.println(comptes);
            obs = FXCollections.observableArrayList(comptes);
            tableview.setItems(obs);
            type.setCellValueFactory(new PropertyValueFactory<>("idTypeId"));
            dateCol.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
            usercol.setCellValueFactory(new PropertyValueFactory<>("idUserId"));
            statuecol.setCellValueFactory(new PropertyValueFactory<>("staute"));
            maxSoldefx.setCellValueFactory(new PropertyValueFactory<>("maxSolde"));
            minSoldefx.setCellValueFactory(new PropertyValueFactory<>("minSolde"));
            redSoldefx.setCellValueFactory(new PropertyValueFactory<>("redSolde"));
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
    }
    
    
    
}
