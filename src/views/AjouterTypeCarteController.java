/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import services.TypeCarteService;
import models.TypeCarte;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class AjouterTypeCarteController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private Button Ajouterfx;
    @FXML
    private TextArea descriptionfx;
    @FXML
    private Button annulerfx;
    @FXML
    private TextField nomfx;

    @FXML
    private Label welcomeLb;

    TypeCarte typecarte = new TypeCarte();

    TypeCarteService ps = new TypeCarteService();
    @FXML
    private Button Home1;
    @FXML
    private Button categorie1;
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
    private Label nomlabel;
    @FXML
    private Label descriptionlabel;
    @FXML
    private Button demandcard;

    /**
     * Initializes the controller class.
     */
    public boolean isAlphabetic(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        return s.matches("[a-zA-Z]+");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void prefixe(MouseEvent event) {
    }

   

    @FXML
    private void AjouterType(ActionEvent event) {
        try {

            if (ps.Existe(nomfx.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Echec de l'ajout");
                alert.setHeaderText(null);
                alert.setContentText("Attention ! type existant");
                alert.showAndWait();
                return;
            }

            if (nomfx.getText().isEmpty()) {
                Tooltip tooltip = new Tooltip("Le champ nom est obligatoire.");
                nomfx.setTooltip(tooltip);
                tooltip.show(nomfx, nomfx.getLayoutX(), nomfx.getLayoutY() + nomfx.getHeight());
                nomfx.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.isEmpty()) {
                        tooltip.hide();
                    }
                });
                return;
            }
            if (descriptionfx.getText().isEmpty()) {
                Tooltip tooltip = new Tooltip("Le champ description est obligatoire.");
                descriptionfx.setTooltip(tooltip);
                tooltip.show(descriptionfx, descriptionfx.getLayoutX(), descriptionfx.getLayoutY() + descriptionfx.getHeight());
                descriptionfx.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.isEmpty()) {
                        tooltip.hide();
                    }
                });
                return;
            }
            if (!isAlphabetic(nomfx.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed to add");
                alert.setHeaderText(null);
                alert.setContentText("Attention! Please make sure that the name is entered using only alphabetic characters.");
                alert.showAndWait();
                return;
            }

            String nom = nomfx.getText();
            String description = descriptionfx.getText();
            TypeCarte p = new TypeCarte(nom, description);
            ps.ajouter(p);
            Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("information Dialog");
            alert0.setHeaderText(null);
            alert0.setContentText("Ajout avec succes ");
            alert0.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Typecarte.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
            //((Node) event.getSource()).getScene().getWindow().hide();
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }

    }

    @FXML
    private void annuler(ActionEvent event) {

        nomfx.clear();
        descriptionfx.clear();

//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("AfficherTypeCarte.fxml"));
//            Parent root = loader.load();
//            annulerfx.getScene().setRoot(root);
        ((Node) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    private void affichertypecarte(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Typecarte.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void reset() {
//                   Evenement e = new Evenement();
//    LocalDate date ;
//    date=LocalDate.of(00, 00, 00);
        nomfx.setText("");
        descriptionfx.setText("");
        // mydatedebut.setValue(date);

    }

    public void setData() {

        String b = typecarte.getNom();
        String c = typecarte.getDescription();
        nomfx.setText(b);
        descriptionfx.setText(c);

    }

    private void affichelistecarte(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Typecarte.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Accueil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("statistiques.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void listedemandecard(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DemandeCarte.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void ConsulterTypeCheque(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichetypecheque.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void affichelistetypecarte(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Typecarte.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void listetypecheque(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichetypecheque.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void avis(ActionEvent event) {
    }

    @FXML
    private void listedemandecheque(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DemandeCheque.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
