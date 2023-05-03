/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import static views.TypecarteController.bnp;
import services.TypeCarteService;
import models.TypeCarte;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class ModifiertypecarteController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private Label welcomeLb;
    @FXML
    private Button Home1;
    @FXML
    private Button categorie1;
    @FXML
    private Button produit1;
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
    private TextArea descriptionfx;
    @FXML
    private Button annulerfx;
    @FXML
    private TextField nomfx;
    private TypeCarte bonplan1;
    public static TypeCarte bnp;

    private TypeCarte typeCarte;
    public static TypeCarte tp = new TypeCarte();
    @FXML
    private Button modifier;

    /**
     * Initializes the controller class.
     */
    private TypeCarte donnees;

// ... autres méthodes du contrôleur ...
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setData(TypeCarte donnees) {
        this.donnees = donnees;
        nomfx.setText(donnees.getNom());
        descriptionfx.setText(donnees.getDescription());
    }

    @FXML
    private void affichelistecarte(ActionEvent event) {
    }

    @FXML
    private void annuler(ActionEvent event) {
    }

    @FXML
    private void prefixe(MouseEvent event) {
    }

    @FXML
    private void ModifierBP(ActionEvent event) {

        String nom = nomfx.getText();
        String description = descriptionfx.getText();

        // Créer un nouvel objet TypeCarte avec les nouvelles valeurs entrées par l'utilisateur
        TypeCarte c = new TypeCarte();
        TypeCarteService f = new TypeCarteService();
        c.setId(donnees.getId());
        c.setNom(nom);
        c.setDescription(description);

        try {
            // Appeler la méthode "modifier" de votre classe de service de données pour enregistrer les modifications
            f.modifier(c, donnees.getId());

            // Afficher une alerte pour indiquer que la modification a été enregistrée avec succès
            Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("information Dialog");
            alert0.setHeaderText(null);
            alert0.setContentText("Votre modification a été enregistrée avec succés ");
            alert0.show();

            // Fermer la fenêtre de modification
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

//            try{
//            TypeCarteService bp= new TypeCarteService();
//            TypeCarte c = new TypeCarte();
//            c.setId(tp.getId());
//            c.setNom(nomfx.getText());
//            c.setDescription(descriptionfx.getText());
//            
//            
//            bp.modifier(c,donnees.getId());
//            
//            
//            Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
//            alert0.setTitle("information Dialog");
//            alert0.setHeaderText(null);
//            alert0.setContentText("Votre modification a été enregistrée avec succés ");
//            alert0.show();
//            ((Node) event.getSource()).getScene().getWindow().hide();
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
}
