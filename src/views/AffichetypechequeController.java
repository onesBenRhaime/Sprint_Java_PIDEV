/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import services.TypeCarteService;
import services.TypeChequeService;
import models.TypeCarte;
import models.TypeCheque;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class AffichetypechequeController implements Initializable {

    @FXML
    private Label welcome;
    @FXML
    private TableView<TypeCheque> TableView;
    @FXML
    private TableColumn<TypeCheque, String> nomfx;
    @FXML
    private TableColumn<TypeCheque, String> descriptionfx;
    @FXML
    private TableColumn<TypeCheque, Integer> startfx;
    @FXML
    private TableColumn<TypeCheque, Integer> endfx;
    @FXML
    private TableColumn<TypeCheque, Date> datec;
    @FXML
    private TableColumn<TypeCheque, Date> datev;
    @FXML
    private Button Home1;
    @FXML
    private Button categorie1;
    @FXML
    private Button demandcard;
    @FXML
    private Button btnSignout1;
    @FXML
    private Button btnSignout11112;
    @FXML
    private Button btnSignout111121;
    @FXML
    private Button aviss;
    @FXML
    private Button btnSignout11112111;
    @FXML
    private Button modifierfx;
     TypeChequeService ps = new TypeChequeService();
    ObservableList<TypeCheque> obs;
    @FXML
    private Button comm1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            // TODO

            List<TypeCheque> types = ps.recuperer();
            obs = FXCollections.observableArrayList(types);
            TableView.setItems(obs);
            nomfx.setCellValueFactory(new PropertyValueFactory<>("nom"));
            descriptionfx.setCellValueFactory(new PropertyValueFactory<>("description"));
            startfx.setCellValueFactory(new PropertyValueFactory<>("startnum"));
            endfx.setCellValueFactory(new PropertyValueFactory<>("endnum"));
            datec.setCellValueFactory(new PropertyValueFactory<>("datecreation"));
            datev.setCellValueFactory(new PropertyValueFactory<>("datevalidation"));

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }    

    @FXML
    private void Ajouterinterface(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterTypeCheque.fxml"));
            Parent root = loader.load();
            welcome.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


     @FXML
    private void listedemandecard(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DemandeCarte.fxml"));
            Parent root = loader.load();
            welcome.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void ConsulterTypeCheque(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichetypecheque.fxml"));
            Parent root = loader.load();
            welcome.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void avis(ActionEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException{
        
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
    private void modifier(ActionEvent event) {
         try {
            TypeCheque tc = TableView.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Modifiertypecheque.fxml"));
            Parent root = loader.load();
            ModifiertypechequeController controller = loader.getController();

            // Passer les données à modifier au contrôleur de l'interface utilisateur
            controller.setData(tc);
            // Afficher la nouvelle fenêtre de modification
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Récupérer la liste des objets TypeCarte à partir de votre classe de service de données
            List<TypeCheque> liste;
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
    private void Accueil(ActionEvent event) {
    }

    @FXML
    private void affichelistetypecarte(ActionEvent event) {
              try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Typecarte.fxml"));
            Parent root = loader.load();
            welcome.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

  

    @FXML
    private void listetypecheque(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichetypecheque.fxml"));
            Parent root = loader.load();
            welcome.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void listedemandecheque(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DemandeCheque.fxml"));
            Parent root = loader.load();
            welcome.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
