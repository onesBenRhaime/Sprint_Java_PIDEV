/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import models.CategoryCredit;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.CategoryCreditService;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CreditCategoriesController implements Initializable {

    @FXML
    private TableView<CategoryCredit> tbCategories;
    @FXML
    private TableColumn<CategoryCredit, String> colName;
    @FXML
    private TableColumn<CategoryCredit, String> colDesc;
    @FXML
    private TableColumn<CategoryCredit, String> ColActions;
    @FXML
    private Button btnAddCat;
    CategoryCreditService ps = new CategoryCreditService();

    CategoryCredit cat = null;

    ObservableList<CategoryCredit> obs;
    @FXML
    private Text lbcategories;
    @FXML
    private Pagination paginate;
    @FXML
    private Button profilfx;
    @FXML
    private Button lbcat;
    @FXML
    private Button lboffer;
    @FXML
    private Button lbdem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            List<CategoryCredit> categories = ps.recuperer();
            obs = FXCollections.observableArrayList(categories);
            tbCategories.setItems(obs);
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        Callback<TableColumn<CategoryCredit, String>, TableCell<CategoryCredit, String>> cellFoctory = (TableColumn<CategoryCredit, String> param) -> {
            final TableCell<CategoryCredit, String> cell = new TableCell<CategoryCredit, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                delCat();
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            upCat();

                        });
                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
      
       
          ColActions.setCellFactory(cellFoctory);
       
        paginate.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            tbCategories.setItems((ObservableList<CategoryCredit>) createPage(newIndex.intValue()));
        });
        paginate.setPageCount((int) Math.ceil((double) obs.size() / 2));
 

    }

    @FXML
    private void addCat(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateCategoryCredit.fxml"));
            Parent root = loader.load();
            btnAddCat.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }

    private void upCat() {
        try {
            cat = tbCategories.getSelectionModel().getSelectedItem();
                System.out.println(cat.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateCategoryCredit.fxml"));
            Parent root = loader.load();
            UpdateCategoryCreditController controller = loader.getController();

            // Passer les données à modifier au contrôleur de l'interface utilisateur
            controller.setTextField(cat);  
            // Afficher la nouvelle fenêtre de modification
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Récupérer la liste des objets TypeCarte à partir de votre classe de service de données
            List<CategoryCredit> liste;
            try {
                liste = ps.recuperer();
                // Parcourir la liste et mettre à jour l'objet TypeCarte correspondant à l'ID de l'objet modifié
                for (int i = 0; i < liste.size(); i++) {
                    if (liste.get(i).getId() == cat.getId()) {
                        liste.set(i, cat);
                        break;
                    }
                    // Rafraîchir le contenu du tableau en remplaçant la liste actuelle par la nouvelle liste modifiée
                    tbCategories.getItems().clear();
                    tbCategories.getItems().addAll(liste);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void delCat() throws SQLException {
        int cat = tbCategories.getSelectionModel().getSelectedIndex();
        if (cat >= 0) {
            // Créer une boîte de dialogue d'alerte
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirme suppression");
            alert.setHeaderText("are you sure you want to delete this category ?");

            // Ajouter les boutons "oui" et "non" à la boîte de dialogue
            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                ps.supprimer(obs.get(cat), obs.get(cat).getId());
                obs.remove(cat);

            } else {
                // L'utilisateur a cliqué sur "non" ou a fermé la boîte de dialogue
                // Ne rien faire
            }
        }

    }

public TableView<CategoryCredit> createPage(int pageIndex) {
    int itemsPerPage = 2;
    int fromIndex = pageIndex * itemsPerPage;
    int toIndex = Math.min(fromIndex + itemsPerPage, obs.size());
    tbCategories.setItems(FXCollections.observableArrayList(obs.subList(fromIndex, toIndex)));
    return tbCategories;
}

    @FXML
    private void categories(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreditCategories.fxml"));
            Parent root = loader.load();
            lbcat.getScene().setRoot(root);
        } catch (IOException ex) {
                System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void offers(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Loans.fxml"));
            Parent root = loader.load();
            lboffer.getScene().setRoot(root);
        } catch (IOException ex) {
                System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void demandes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DemandesCredit.fxml"));
            Parent root = loader.load();
            lbdem.getScene().setRoot(root);
        } catch (IOException ex) {
                System.out.println(ex.getMessage());

        }
    }
    
}
