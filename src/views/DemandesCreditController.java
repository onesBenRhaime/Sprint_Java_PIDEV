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
import java.util.Properties;
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
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.DemandeCreditService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DemandesCreditController implements Initializable {

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
    private Button profilfx;
    @FXML
    private Button lbcat;
    @FXML
    private Button lboffer;
    @FXML
    private Button lbdem;
    @FXML
    private Pagination paginate;

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
                String userName = user != null ? user.getName() : "";
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

                        FontAwesomeIconView acceptIcon = new FontAwesomeIconView(FontAwesomeIcon.CHECK);
                        FontAwesomeIconView rejectIcon = new FontAwesomeIconView(FontAwesomeIcon.TIMES);
                        FontAwesomeIconView detailIcon = new FontAwesomeIconView(FontAwesomeIcon.EYE);

                        acceptIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        rejectIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        detailIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#84CBF0;"
                        );
                        acceptIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                acceptDemande();
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            } catch (MessagingException ex) {
                                System.out.println(ex.getMessage());
                            }
                        });
                        rejectIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                rejectDemande();
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            } catch (MessagingException ex) {
                                System.out.println(ex.getMessage());
                            }

                        });
                         detailIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                details();
                            } catch (IOException ex) {
                                Logger.getLogger(DemandesCreditController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        });
                        HBox managebtn = new HBox(rejectIcon, acceptIcon,detailIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(acceptIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(rejectIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(detailIcon, new Insets(2, 4, 0, 1));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        ColActions.setCellFactory(cellFoctory);
          paginate.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            tbDemandes.setItems((ObservableList<DemandeCredit>) createPage(newIndex.intValue()));
        });
        paginate.setPageCount((int) Math.ceil((double) obs.size() / 2));
      
        tbDemandes.setItems(obs);

        tbDemandes.refresh();

    }

   public TableView<DemandeCredit> createPage(int pageIndex) {
    int itemsPerPage = 2;
    int fromIndex = pageIndex * itemsPerPage;
    int toIndex = Math.min(fromIndex + itemsPerPage, obs.size());
    tbDemandes.setItems(FXCollections.observableArrayList(obs.subList(fromIndex, toIndex)));
    return tbDemandes;
   }
    private void acceptDemande() throws SQLException, MessagingException{
        int c = tbDemandes.getSelectionModel().getSelectedIndex();
        if (c >= 0) {
            // Créer une boîte de dialogue d'alerte
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update Status");
            alert.setHeaderText("are you sure you want to accept this Demande ?");

            // Ajouter les boutons "oui" et "non" à la boîte de dialogue
            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                sendEmailAccepted(obs.get(c).getUser().getEmail());

                ps.modifierStatusToAccepted(obs.get(c), obs.get(c).getId());

                obs.clear();

                obs.addAll(ps.recuperer());
            } else {
                // L'utilisateur a cliqué sur "non" ou a fermé la boîte de dialogue
                // Ne rien faire
            }
        }

    }

    private void rejectDemande() throws SQLException, MessagingException {
        int c = tbDemandes.getSelectionModel().getSelectedIndex();
        if (c >= 0) {
            // Créer une boîte de dialogue d'alerte
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update Status");
            alert.setHeaderText("are you sure you want to reject this Demande ?");

            // Ajouter les boutons "oui" et "non" à la boîte de dialogue
            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                sendEmailRejected(obs.get(c).getUser().getEmail());

                ps.modifierStatusToRejected(obs.get(c), obs.get(c).getId());
                obs.clear();
                obs.addAll(ps.recuperer());

            } else {
                // L'utilisateur a cliqué sur "non" ou a fermé la boîte de dialogue
                // Ne rien faire
            }
        }

    }

    private void sendEmailAccepted(String recipientEmail) throws MessagingException{
        if (recipientEmail == null || recipientEmail.isEmpty()) {
            System.out.println("Recipient email is empty or null. Email not sent.");
            return;
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mazebank199@gmail.com", "mmgbwjvnafbavjfk");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mazebank199@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Demande Accepted");
            message.setText("Your demande has been accepted!");

            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            throw e;
        }
    }
     private void sendEmailRejected(String recipientEmail) throws MessagingException{
        if (recipientEmail == null || recipientEmail.isEmpty()) {
            System.out.println("Recipient email is empty or null. Email not sent.");
            return;
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mazebank199@gmail.com", "mmgbwjvnafbavjfk");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mazebank199@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Demande Rejected");
            message.setText("Your demande has been rejected!");

            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            throw e;
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
