/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import services.DemandeCarte;
import services.DemandeCheque;
import models.CarnetCheque;
import models.CarteBancaire;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class ListedemandchequefrontController implements Initializable {

    @FXML
    private Label welcome;
    @FXML
    private TableView<CarnetCheque> TableView;
    @FXML
    private TableColumn<CarnetCheque, Integer> typecol;
    @FXML
    private TableColumn<CarnetCheque, String> emailcol;
    @FXML
    private TableColumn<CarnetCheque, String> identifiercol;
    @FXML
    private TableColumn<CarnetCheque, String> descriptioncol;
    @FXML
    private TableColumn<CarnetCheque, String> statuscol;
    @FXML
    private Button Accueilfx1;
    @FXML
    private Button Accueilfx11;
    @FXML
    private Button demandecarte;
    @FXML
    private Button demandecheque;
    @FXML
    private Button Accueilfx15;
    @FXML
    private Button Accueilfx16;
    @FXML
    private Button deletefx;
    @FXML
    private Button modifierfx;
    @FXML
    private Button ajouterdemande;
    DemandeCheque ps = new DemandeCheque();
    ObservableList<CarnetCheque> obs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            List<CarnetCheque> types = ps.afficher();
            obs = FXCollections.observableArrayList(types);
            TableView.setItems(obs);
            typecol.setCellValueFactory(new PropertyValueFactory<>("idtypecarnet_id"));
            emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
            identifiercol.setCellValueFactory(new PropertyValueFactory<>("identifier"));
            descriptioncol.setCellValueFactory(new PropertyValueFactory<>("description"));
            statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }    

    @FXML
    private void accueilAction(ActionEvent event) {
    }

    @FXML
    private void demandecarte(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichedemandecartefront.fxml"));
            Parent root = loader.load();
            welcome.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void demandecheque(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listedemandchequefront.fxml"));
            Parent root = loader.load();
            welcome.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void goToTransactions(ActionEvent event) {
    }

    @FXML
    private void goToSendMoney(ActionEvent event) {
    }

    @FXML
    private void deletedemandecard(ActionEvent event)throws SQLException{
        int selectedIndex = TableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Créer une boîte de dialogue d'alerte
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmer la suppression");
            alert.setHeaderText("Voulez-vous vraiment supprimer la ligne sélectionnée ?");

            // Ajouter les boutons "oui" et "non" à la boîte de dialogue
            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                // Supprimer la ligne sélectionnée
                ps.supprimer(obs.get(selectedIndex));
                obs.remove(selectedIndex);
            } else {
                // L'utilisateur a cliqué sur "non" ou a fermé la boîte de dialogue
                // Ne rien faire
            }
        }
    }

    @FXML
    private void modifierdemandecard(ActionEvent event) {
    }

    @FXML
    private void ajouterdemandecard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajouterdemandecheque.fxml"));
            Parent root = loader.load();
            welcome.getScene().setRoot(root);
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
