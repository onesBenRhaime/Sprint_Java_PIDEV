/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import services.DemandeCarte;
import services.MailerService;
import services.TypeCarteService;
import services.Upload;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.pdf.qrcode.QRCodeWriter;
import com.itextpdf.text.pdf.qrcode.WriterException;
import static com.itextpdf.xmp.XMPMetaFactory.reset;
import models.CarteBancaire;
import models.TypeCarte;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class DemandeCarteController implements Initializable {

    @FXML
    private Label welcome;
    @FXML
    private TableView<CarteBancaire> TableView;
    @FXML
    private TableColumn<CarteBancaire, Integer> typecol;
    @FXML
    private TableColumn<CarteBancaire, String> emailcol;
    @FXML
    private TableColumn<CarteBancaire, String> identifiercol;
    @FXML
    private TableColumn<CarteBancaire, String> descriptioncol;
    @FXML
    private TableColumn<CarteBancaire, ImageView> imgcol;
    @FXML
    private TableColumn<CarteBancaire, ImageView> img2col;
    @FXML
    private TableColumn<CarteBancaire, String> statuscol;
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
    private Button modifierfx;
    DemandeCarte ps = new DemandeCarte();
    ObservableList<CarteBancaire> obs;
    @FXML
    private Button detailsimage;

    CarteBancaire carte = new CarteBancaire();

    @FXML
    private Button demandcard;
    private CarteBancaire CarteBancaire;
    private CarteBancaire donnees;
    File selectedfile;
    int userid = 1;
    String usernom = "kacem.salma@esprit.tn";
    String path_img;

    Upload u = new Upload();
    @FXML
    private ImageView qrView;
    @FXML
    private Button refus;
    public static CarteBancaire cartebancaire;

    Random rand = new Random();
    int randomcode = rand.nextInt(9999);
    String code = String.valueOf(randomcode);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            List<CarteBancaire> types = ps.recuperer();
            obs = FXCollections.observableArrayList(types);
            TableView.setItems(obs);
            typecol.setCellValueFactory(new PropertyValueFactory<>("idtypecarte_id"));
            emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
            identifiercol.setCellValueFactory(new PropertyValueFactory<>("identifier"));
            descriptioncol.setCellValueFactory(new PropertyValueFactory<>("description"));
            imgcol.setCellValueFactory(new PropertyValueFactory<>("cin_s1"));
            img2col.setCellValueFactory(new PropertyValueFactory<>("cin_s2"));
            statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));

            // Ajouter un événement pour ouvrir l'interface detailsCard.fxml
            detailsimage.setOnAction(e -> {
                // Récupérer l'élément sélectionné dans la table
                CarteBancaire selectedCarte = TableView.getSelectionModel().getSelectedItem();
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

    public void resizeFile(String imagePathToRead,
            String imagePathToWrite, int resizeWidth, int resizeHeight)
            throws IOException {

        File fileToRead = new File(imagePathToRead);
        BufferedImage bufferedImageInput = ImageIO.read(fileToRead);

        BufferedImage bufferedImageOutput = new BufferedImage(resizeWidth,
                resizeHeight, bufferedImageInput.getType());

        Graphics2D g2d = bufferedImageOutput.createGraphics();
        g2d.drawImage(bufferedImageInput, 0, 0, resizeWidth, resizeHeight, null);
        g2d.dispose();

        String formatName = imagePathToWrite.substring(imagePathToWrite
                .lastIndexOf(".") + 1);

        ImageIO.write(bufferedImageOutput, formatName, new File(imagePathToWrite));
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
    private void detailsimage(ActionEvent event) {
        try {
            DemandeCarteController.cartebancaire = cartebancaire;
            System.out.println(DemandeCarteController.cartebancaire);
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("DetailsCard.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(DemandeCarteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Accueil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("statistiques.fxml"));
            Parent root = loader.load();
            welcome.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

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
    private void avis(ActionEvent event) {
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
    private void modifier(ActionEvent event) {
    }

    private void refresh() throws SQLException {
        try {
            List<CarteBancaire> cartes = ps.recuperer();
            obs = FXCollections.observableArrayList(cartes);
            TableView.setItems(obs);
            emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
            identifiercol.setCellValueFactory(new PropertyValueFactory<>("identifier"));
            descriptioncol.setCellValueFactory(new PropertyValueFactory<>("description"));
            imgcol.setCellValueFactory(new PropertyValueFactory<>("cin_s1"));
            img2col.setCellValueFactory(new PropertyValueFactory<>("cin_s2"));
            statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

//       @FXML
//    private void accepter(ActionEvent event) throws Exception {
//         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Evoyer mail");
//        alert.setContentText("Envoyer mail a "+ "kacem.salma@esprit.tn");
//        Optional<ButtonType> action = alert.showAndWait();
//        if (action.get() == ButtonType.OK) {
//            MailerService ps = new MailerService();
//            ps.sendMail(usernom);
//            reset();
//        }
//        
//    }
    @FXML
    private void reject(ActionEvent event) throws SQLException {

        int selectedIndex = obs.indexOf(TableView.getSelectionModel().getSelectedItem());
        // System.out.println( obs.get(selectedIndex).getStatue().equalsIgnoreCase("in progress"));
        if (selectedIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed ");
            alert.setHeaderText(null);
            alert.setContentText("no selected ");
            alert.showAndWait();
            return;
        } else if (!obs.get(selectedIndex).getStatus().equalsIgnoreCase("Refuser")) {
            // Créer une boîte de dialogue d'alerte
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Reject");
            alert.setHeaderText("Do you really want to Reject this request ?");

            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                ps.refuser(obs.get(selectedIndex));

                String message = "Votre Demande à ete réfusé";

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed to reject");
            alert.setHeaderText(null);
            alert.setContentText("already Rejected");
            alert.showAndWait();
            return;
        }
        refresh();

    }

    @FXML
    private void accept(ActionEvent event) throws SQLException {

        int selectedIndex = obs.indexOf(TableView.getSelectionModel().getSelectedItem());

        System.out.println("hdsdjhsj : " + obs.get(selectedIndex));
        if (selectedIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed ");
            alert.setHeaderText(null);
            alert.setContentText("no selected ");
            alert.showAndWait();
            return;
        } else if (!obs.get(selectedIndex).getStatus().equalsIgnoreCase("Accepter")) {
            // Créer une boîte de dialogue d'alerte
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Accept");
            alert.setHeaderText("Do you really want to Accept this request ?");
            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                ps.accepter(obs.get(selectedIndex));
                String message = "Votre Demande à ete acceptée";

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed to Accept");
            alert.setHeaderText(null);
            alert.setContentText("already accepted");
            alert.showAndWait();
            return;
        }

        DemandeCarte ths = new DemandeCarte();
        CarteBancaire e = CarteBancaire;
        MailerService mail = new MailerService();
        System.out.println(e);

        mail.sendmail("Confirmation de demande!", "kacem.salmai@esprit.tn");
        qr();
        refresh();
    }

    public void qr() {
        int selectedIndex = obs.indexOf(TableView.getSelectionModel().getSelectedItem());

        System.out.println(obs.get(selectedIndex));

        com.google.zxing.qrcode.QRCodeWriter qrCodeWriter = new com.google.zxing.qrcode.QRCodeWriter();

        String myqr = "Bonjour Mme/Mr: email " + obs.get(selectedIndex).getEmail() + "Votre demande est : Accepter " + " et votre code est " + code + ".";
        int width = 300;
        int height = 300;
        String fileType = "png";

        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myqr, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            System.out.println("Success...");

        } catch (com.google.zxing.WriterException ex) {
            Logger.getLogger(com.google.zxing.qrcode.QRCodeWriter.class.getName()).log(Level.SEVERE, null, ex);
        }

        //ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

    }

}
