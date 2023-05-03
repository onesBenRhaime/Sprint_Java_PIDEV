/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import services.DemandeCarte;
import services.PdfOrderS;
import models.CarteBancaire;
import models.TypeCarte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class AffichedemandecartefrontController implements Initializable {

    @FXML
    private Label welcome;
    @FXML
    private TableView<CarteBancaire> TableView;
    @FXML
    private TableColumn<CarteBancaire, Integer> typecol;
    @FXML
    private TableColumn<CarteBancaire, String> emailcol;
    @FXML
    private TableColumn<CarteBancaire, String> identifiercol;
    @FXML
    private TableColumn<CarteBancaire, String> descriptioncol;
    @FXML
    private TableColumn<CarteBancaire, String> imgcol;
    @FXML
    private TableColumn<CarteBancaire, String> img2col;
    @FXML
    private TableColumn<CarteBancaire, String> statuscol;
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
    private Button Accueilfx161;
    @FXML
    private Button ajouterdemande;
    DemandeCarte ps = new DemandeCarte();
    ObservableList<CarteBancaire> obs;
    @FXML
    private Button deletefx;
    @FXML
    private Button modifierfx;
    private CarteBancaire produitt;
    
     private List<CarteBancaire> listeProduit = new ArrayList<>();
    @FXML
    private Button pdf;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 
        
        try {
            List<CarteBancaire> types = ps.recuperer();
            obs = FXCollections.observableArrayList(types);
            TableView.setItems(obs);
            typecol.setCellValueFactory(new PropertyValueFactory<>("idtypecarte_id"));
            emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
            identifiercol.setCellValueFactory(new PropertyValueFactory<>("identifier"));
            descriptioncol.setCellValueFactory(new PropertyValueFactory<>("description"));
            imgcol.setCellValueFactory(new PropertyValueFactory<>("cin_s1"));
            img2col.setCellValueFactory(new PropertyValueFactory<>("cin_s2"));
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
    private void goToAccounts(ActionEvent event) {
    }

    @FXML
    private void ajouterdemandecard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterDemandeCarteFront.fxml"));
            Parent root = loader.load();
            welcome.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void deletedemandecard(ActionEvent event) throws SQLException{
        
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
       
            
        try {
            CarteBancaire tc = TableView.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierfront.fxml"));
            Parent root = loader.load();
            ModifierfrontController controller = loader.getController();

            // Passer les données à modifier au contrôleur de l'interface utilisateur
            controller.setData(tc);
            // Afficher la nouvelle fenêtre de modification
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Récupérer la liste des objets TypeCarte à partir de votre classe de service de données
            List<CarteBancaire> liste;
            try {
                liste = ps.recuperer();
                // Parcourir la liste et mettre à jour l'objet TypeCarte correspondant à l'ID de l'objet modifié
                for (int i = 0; i < liste.size(); i++) {
                    if (liste.get(i).getId() == tc.getId()) {
                        liste.set(i, tc);
                        break;
                    }
                    // Rafraîchir le contenu du tableau en remplaçant la liste actuelle par la nouvelle liste modifiée
                    TableView.getItems().clear();
                    TableView.getItems().addAll(liste);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TypecarteController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void PDF(ActionEvent event) throws SQLException {
        
        
     
             int selectedIndex = obs.indexOf(TableView.getSelectionModel().getSelectedItem());
                
            PdfOrderS pdf = new PdfOrderS();       
            pdf.orderPdf(obs.get(selectedIndex));
             Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("Information dialog box");
            alert0.setHeaderText(null);
            alert0.setContentText("The pdf file has been exported successfully. ");
            alert0.show();
        
    
    }
       


}
