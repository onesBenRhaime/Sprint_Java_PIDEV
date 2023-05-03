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
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.CategoryCreditService;
import services.CreditService;

/**
 *
 * @author ASUS
 */
public class LoansController implements Initializable {

    @FXML
    private Text lbCredits;
    @FXML
    private TableView<Credit> tbCredits;
    @FXML
    private TableColumn<Credit, String> colCat;
    @FXML
    private TableColumn<Credit, Integer> colMinAmount;
    @FXML
    private TableColumn<Credit, Integer> ColMaxAmount;
    @FXML
    private TableColumn<Credit, Integer> ColWithdraw;
    @FXML
    private TableColumn<Credit, Integer> ColMonths;
    @FXML
    private TableColumn<Credit, Integer> ColLoanRate;
    @FXML
    private Button btnAddCredit;
    @FXML
    private TableColumn<Credit, String> ColActions;

    CreditService ps = new CreditService();

    Credit c = null;

    ObservableList<Credit> obs;
    @FXML
    private Button profilfx;
    @FXML
    private Button lbcat;
    @FXML
    private Button lboffer;
    @FXML
    private Button lbdem;
    @FXML
    private Pagination paginate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            List<Credit> credits = ps.recuperer();
            obs = FXCollections.observableArrayList(credits);
            tbCredits.setItems(obs);
            colCat.setCellValueFactory(cell -> {
                CategoryCredit category = cell.getValue().getCategory();
                String categoryName = category != null ? category.getName() : "";
                return new SimpleStringProperty(categoryName);
            });
            colMinAmount.setCellValueFactory(new PropertyValueFactory<>("minAmount"));
            ColMaxAmount.setCellValueFactory(new PropertyValueFactory<>("maxAmount"));
            ColWithdraw.setCellValueFactory(new PropertyValueFactory<>("withdrawMonthly"));
            ColMonths.setCellValueFactory(new PropertyValueFactory<>("months"));
            ColLoanRate.setCellValueFactory(new PropertyValueFactory<>("loanRate"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        Callback<TableColumn<Credit, String>, TableCell<Credit, String>> cellFoctory = (TableColumn<Credit, String> param) -> {
            final TableCell<Credit, String> cell = new TableCell<Credit, String>() {
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
                                delCredit();
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            upCredit();

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
            tbCredits.setItems((ObservableList<Credit>) createPage(newIndex.intValue()));
        });
        paginate.setPageCount((int) Math.ceil((double) obs.size() / 2));
      

    }

    private void upCredit() {
        try {
            c = tbCredits.getSelectionModel().getSelectedItem();
            System.out.println(c.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateLoan.fxml"));
            Parent root = loader.load();
            UpdateLoanController controller = loader.getController();

            // Passer les données à modifier au contrôleur de l'interface utilisateur
            controller.setTextField(c);
            // Afficher la nouvelle fenêtre de modification
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Récupérer la liste des objets TypeCarte à partir de votre classe de service de données
            List<Credit> liste;
            try {
                liste = ps.recuperer();
                // Parcourir la liste et mettre à jour l'objet TypeCarte correspondant à l'ID de l'objet modifié
                for (int i = 0; i < liste.size(); i++) {
                    if (liste.get(i).getId() == c.getId()) {
                        liste.set(i, c);
                        break;
                    }
                    // Rafraîchir le contenu du tableau en remplaçant la liste actuelle par la nouvelle liste modifiée
                    tbCredits.getItems().clear();
                    tbCredits.getItems().addAll(liste);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void addCredit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateLoan.fxml"));
            Parent root = loader.load();
            btnAddCredit.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }
    public TableView<Credit> createPage(int pageIndex) {
    int itemsPerPage = 2;
    int fromIndex = pageIndex * itemsPerPage;
    int toIndex = Math.min(fromIndex + itemsPerPage, obs.size());
    tbCredits.setItems(FXCollections.observableArrayList(obs.subList(fromIndex, toIndex)));
    return tbCredits;
}
    private void delCredit() throws SQLException {
        int c = tbCredits.getSelectionModel().getSelectedIndex();
        if (c >= 0) {
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
                ps.supprimer(obs.get(c), obs.get(c).getId());
                obs.remove(c);

            } else {
                // L'utilisateur a cliqué sur "non" ou a fermé la boîte de dialogue
                // Ne rien faire
            }
        }

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
