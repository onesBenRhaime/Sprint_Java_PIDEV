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
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
public class AjouterTypeChequeController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private Label welcomeLb;
    @FXML
    private Button Ajouterfx;
    @FXML
    private Label nomlabel;
    @FXML
    private Label descriptionlabel;
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
    private TextArea descriptionfx;
    @FXML
    private Button annulerfx;
    @FXML
    private TextField nomfx;
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
    TypeCheque typecheque = new TypeCheque();

    TypeChequeService ps = new TypeChequeService();
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjouterType(ActionEvent event) {

        try {
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
            if (startnumfx.getText().isEmpty()) {
                Tooltip tooltip = new Tooltip("Le champ startnum est obligatoire.");
                startnumfx.setTooltip(tooltip);
                tooltip.show(startnumfx, startnumfx.getLayoutX(), startnumfx.getLayoutY() + startnumfx.getHeight());
                startnumfx.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.isEmpty()) {
                        tooltip.hide();
                    }
                });
                return;
            }
            if (endnumfx.getText().isEmpty()) {
                Tooltip tooltip = new Tooltip("Le champ startnum est obligatoire.");
                startnumfx.setTooltip(tooltip);
                tooltip.show(startnumfx, startnumfx.getLayoutX(), startnumfx.getLayoutY() + startnumfx.getHeight());
                startnumfx.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.isEmpty()) {
                        tooltip.hide();
                    }
                });
                return;
            }
            if (datecfx.getValue() == null) {
                Tooltip tooltip = new Tooltip("Le champ date est obligatoire.");
                datecfx.setTooltip(tooltip);
                tooltip.show(datecfx, datecfx.getLayoutX(), datecfx.getLayoutY() + datecfx.getHeight());
                datecfx.valueProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        tooltip.hide();
                    }
                });
                return;
            }
            if (datevfx.getValue() == null) {
                Tooltip tooltip = new Tooltip("Le champ date est obligatoire.");
                datevfx.setTooltip(tooltip);
                tooltip.show(datevfx, datevfx.getLayoutX(), datevfx.getLayoutY() + datevfx.getHeight());
                datevfx.valueProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
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
                
            if (isNumeric(startnumfx.getText())==false ) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Failed to add");
                      alert.setHeaderText(null);
                      alert.setContentText("Veuillez noter que le champ doit contenir une valeur numérique. Merci de ne saisir que des chiffres");
                      alert.showAndWait();
                   return;
                }
              if (isNumeric(endnumfx.getText())==false ) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                      alert.setTitle("Failed to add");
                      alert.setHeaderText(null);
                      alert.setContentText("Veuillez noter que le champ doit contenir une valeur numérique. Merci de ne saisir que des chiffres");
                      alert.showAndWait();
                   return;
                }

            String nom = nomfx.getText();
            String description = descriptionfx.getText();
            int startnum = Integer.parseInt(startnumfx.getText());
            int endnum = Integer.parseInt(endnumfx.getText());
            Date datecreation = Date.valueOf(datecfx.getValue());
            Date datevalidation = Date.valueOf(datevfx.getValue());

            TypeCheque p = new TypeCheque(startnum, endnum, nom, description, datecreation, datevalidation);
            ps.ajouter(p);
            Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("information Dialog");
            alert0.setHeaderText(null);
            alert0.setContentText("Ajout avec succes ");
            alert0.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichetypecheque.fxml"));
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
        startnumfx.clear();
        endnumfx.clear();
        datecfx.setValue(null);
        datevfx.setValue(null);

//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("AfficherTypeCarte.fxml"));
//            Parent root = loader.load();
//            annulerfx.getScene().setRoot(root);
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void prefixe(MouseEvent event) {
    }

    @FXML
    private void affichertypecarte(ActionEvent event) {
    }

    @FXML
    private void Accueil(ActionEvent event) {
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
    private void listedemandecard(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DemandeCarte.fxml"));
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
    private void listedemandecheque(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DemandeCheque.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void avis(ActionEvent event) {
    }

}
