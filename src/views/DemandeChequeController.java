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
import java.io.ByteArrayInputStream;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class DemandeChequeController implements Initializable {

    @FXML
    private Button Home1;
    @FXML
    private Button categorie1;
    @FXML
    private Button demandcard;
    @FXML
    private Button comm1;
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
    private TableColumn<CarnetCheque, String> imgcol;
    @FXML
    private TableColumn<CarnetCheque, String> img2col;
    @FXML
    private TableColumn<CarnetCheque, String> statuscol;
    @FXML
    private Button modifierfx;
    @FXML
    private Button detailsimage;
    @FXML
    private TableColumn<CarnetCheque, String> documentfx;

     DemandeCheque ps = new DemandeCheque();
    ObservableList<CarnetCheque> obs;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            List<CarnetCheque> types = ps.recuperer();
            obs = FXCollections.observableArrayList(types);
            TableView.setItems(obs);
            typecol.setCellValueFactory(new PropertyValueFactory<>("idtypecarnet_id"));
            emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
            identifiercol.setCellValueFactory(new PropertyValueFactory<>("identifier"));
            descriptioncol.setCellValueFactory(new PropertyValueFactory<>("description"));
            imgcol.setCellValueFactory(new PropertyValueFactory<>("cin_s1"));
            img2col.setCellValueFactory(new PropertyValueFactory<>("cin_s2"));
            documentfx.setCellValueFactory(new PropertyValueFactory<>("document"));
            statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));

            // Ajouter un événement pour ouvrir l'interface detailsCard.fxml
            detailsimage.setOnAction(e -> {
                // Récupérer l'élément sélectionné dans la table
                CarnetCheque selectedCarte = TableView.getSelectionModel().getSelectedItem();
                if (selectedCarte != null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsCard.fxml"));
                        Parent root = loader.load();

                        // Récupérer le contrôleur de l'interface detailsCard.fxml
                        DetailsCardController controller = loader.getController();

                        // Convertir les images de String en ImageView
                        ImageView cin_s1 = new ImageView(new Image(new ByteArrayInputStream(selectedCarte.getCin_s1().getBytes())));
                        ImageView cin_s2 = new ImageView(new Image(new ByteArrayInputStream(selectedCarte.getCin_s2().getBytes())));

                        // Configurer les images et les labels dans le contrôleur
                        controller.setImages(cin_s1, cin_s2);
                        controller.setLabels(selectedCarte.getCin_s1(), selectedCarte.getCin_s2());

                        // Afficher l'interface detailsCard.fxml
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            });
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
    private void listedemandecard(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DemandeCarte.fxml"));
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

    @FXML
    private void avis(ActionEvent event) {
    }

    @FXML
    private void detailsimage(ActionEvent event) {
    }
    
}
