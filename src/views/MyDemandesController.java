/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import models.CategoryCredit;
import models.Credit;
import models.DemandeCredit;
import models.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.mail.MessagingException;
import services.DemandeCreditService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MyDemandesController implements Initializable {
 @FXML
    private Text lbdemandes;
    @FXML
    private TableColumn<DemandeCredit, String> colCat;
    @FXML
    private TableColumn<DemandeCredit, Integer> colAmount;
    @FXML
    private TableColumn<DemandeCredit, String> ColActions;
    @FXML
    private TableColumn<DemandeCredit, String> userNameCol;
    @FXML
    private TableColumn<DemandeCredit, Date> dateCol;
    @FXML
    private TableColumn<DemandeCredit, String> ColStatus;
    @FXML
    private TableView<DemandeCredit> tbDemandes;
    DemandeCreditService ps = new DemandeCreditService();

    DemandeCredit c = null;

    ObservableList<DemandeCredit> obs;
    @FXML
    private Button btnApplyCredit;
    @FXML
    private Button lboffers;
    @FXML
    private Button lbdem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<DemandeCredit> demandes = ps.recuperer();
            obs = FXCollections.observableArrayList(demandes);
            tbDemandes.setItems(obs);

            colCat.setCellValueFactory(cellData -> {
                Credit credit = cellData.getValue().getCredit();
                String categoryName = "";
                if (credit != null) {
                    System.out.println(credit); // make sure this prints a non-null object
                    CategoryCredit category = credit.getCategory();
                    if (category != null) {
                        System.out.println(category); // make sure this prints a non-null object
                        categoryName = category.getName();
                    }
                }
                return new SimpleStringProperty(categoryName);
            });

            userNameCol.setCellValueFactory(cell -> {
                User user = cell.getValue().getUser();
                String userName = user != null ? user.getName(): "";
                return new SimpleStringProperty(userName);
            });
            dateCol.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
            colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
            ColStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        Callback<TableColumn<DemandeCredit, String>, TableCell<DemandeCredit, String>> cellFoctory = (TableColumn<DemandeCredit, String> param) -> {
            final TableCell<DemandeCredit, String> cell = new TableCell<DemandeCredit, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView updateIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
                        FontAwesomeIconView detailIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        updateIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        detailIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#84CBF0;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                deleteDemande();
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            } 
                        });
                        updateIcon.setOnMouseClicked((MouseEvent event) -> {
                            updateDemande(); 

                        });
                         detailIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                details();
                            } catch (IOException ex) {
                                Logger.getLogger(DemandesCreditController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        });
                        HBox managebtn = new HBox(deleteIcon, updateIcon,detailIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(updateIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(detailIcon, new Insets(2, 4, 0, 1));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        ColActions.setCellFactory(cellFoctory);
        tbDemandes.setItems(obs);

        tbDemandes.refresh();

    }
   

    @FXML
    private void applyCredit(ActionEvent event) {
        
               try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Offers.fxml"));
            Parent root = loader.load();
            btnApplyCredit.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }
     private void details() throws IOException {
            DemandeCredit d = tbDemandes.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailDemande.fxml"));
            Parent root = loader.load();
            DetailDemandeController controller = loader.getController();

            // Passer les données à modifier au contrôleur de l'interface utilisateur
            controller.setTextField(d);
            // Afficher la nouvelle fenêtre de modification
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

           
    }
     private void updateDemande() {
        try {
            DemandeCredit d = tbDemandes.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateDemande.fxml"));
            Parent root = loader.load();
            UpdateDemandeController controller = loader.getController();

            // Passer les données à modifier au contrôleur de l'interface utilisateur
            controller.setTextField(d);
            // Afficher la nouvelle fenêtre de modification
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Récupérer la liste des objets TypeCarte à partir de votre classe de service de données
            List<DemandeCredit> liste;
            try {
                liste = ps.recuperer();
                // Parcourir la liste et mettre à jour l'objet TypeCarte correspondant à l'ID de l'objet modifié
                for (int i = 0; i < liste.size(); i++) {
                    if (liste.get(i).getId() == c.getId()) {
                        liste.set(i, c);
                        break;
                    }
                    // Rafraîchir le contenu du tableau en remplaçant la liste actuelle par la nouvelle liste modifiée
                    tbDemandes.getItems().clear();
                    tbDemandes.getItems().addAll(liste);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    
    private void deleteDemande() throws SQLException {
        int c = tbDemandes.getSelectionModel().getSelectedIndex();
        if (c >= 0) {
            // Créer une boîte de dialogue d'alerte
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirme suppression");
            alert.setHeaderText("are you sure you want to delete this demande ?");

            // Ajouter les boutons "oui" et "non" à la boîte de dialogue
            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                ps.supprimer(obs.get(c), obs.get(c).getId());
                obs.remove(c);

            } else {
                // L'utilisateur a cliqué sur "non" ou a fermé la boîte de dialogue
                // Ne rien faire
            }
        }

    }

    @FXML
    private void offers(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Offers.fxml"));
            Parent root = loader.load();
            lboffers.getScene().setRoot(root);
        } catch (IOException ex) {
                System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void mydemandes(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MyDemandes.fxml"));
            Parent root = loader.load();
            lbdem.getScene().setRoot(root);
        } catch (IOException ex) {
                System.out.println(ex.getMessage());

        }
    }
}
