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
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class ModifiertypechequeController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private Label welcomeLb;
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
    private TextArea descriptionfx;
    @FXML
    private Button annulerfx;
    @FXML
    private TextField nomfx;
    private TypeCheque donnees;
    @FXML
    private Button Ajouterfx;
    @FXML
    private Label nomlabel;
    @FXML
    private Label descriptionlabel;
    @FXML
    private DatePicker datecfx;
    @FXML
    private DatePicker datevfx;
    @FXML
    private Label descriptionlabel1;
    @FXML
    private Label descriptionlabel11;
    @FXML
    private Label nomlabel1;
    @FXML
    private TextField startnumfx;
    @FXML
    private Label nomlabel11;
    @FXML
    private TextField endnumfx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(TypeCheque donnees) {
        this.donnees = donnees;
        nomfx.setText(donnees.getNom());
        descriptionfx.setText(donnees.getDescription());
  
    // Affectation des valeurs entières aux labels correspondants
    int startnum = donnees.getStartnum();
    startnumfx.setText(Integer.toString(startnum));
    
    int endnum = donnees.getEndnum();
    endnumfx.setText(Integer.toString(endnum));
 
    }

    @FXML
    private void Accueil(ActionEvent event) {
    }

    @FXML
    private void affichelistetypecarte(ActionEvent event) {
    }

    @FXML
    private void listedemandecard(ActionEvent event) {
    }

    @FXML
    private void listetypecheque(ActionEvent event) {
    }

    @FXML
    private void listedemandecheque(ActionEvent event) {
    }

    @FXML
    private void avis(ActionEvent event) {
    }

    @FXML
    private void annuler(ActionEvent event) {
    }

    @FXML
    private void prefixe(MouseEvent event) {
    }

    @FXML
    private void affichertypecarte(ActionEvent event) {
    }

    @FXML
    private void Modifier(ActionEvent event) {
        String nom = nomfx.getText();
        String description = descriptionfx.getText();
        int startnum = Integer.parseInt(startnumfx.getText());
        int endnum = Integer.parseInt(endnumfx.getText());
        Date datecreation = Date.valueOf(datecfx.getValue());
        Date datevalidation = Date.valueOf(datevfx.getValue());
        // Créer un nouvel objet TypeCarte avec les nouvelles valeurs entrées par l'utilisateur
        TypeCheque c = new TypeCheque();
        TypeChequeService f = new TypeChequeService();
        c.setId(donnees.getId());
        c.setNom(nom);
        c.setDescription(description);
        c.setStartnum(startnum);
        c.setStartnum(endnum);
        c.setDatecreation(datecreation);
        c.setDatevalidation(datevalidation);
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

}
