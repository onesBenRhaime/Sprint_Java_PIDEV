/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.Credit;
import models.DemandeCredit;
import models.User;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.DemandeCreditService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CreateDemandeCreditController implements Initializable {

    @FXML
    private Text lbAddPage;
    @FXML
    private Text lbAddDemande;
    @FXML
    private Label lbCat;
    @FXML
    private TextField tfCat;
    @FXML
    private Label lbAmount;
    @FXML
    private TextField tfAmount;
    @FXML
    private Label lbNote;
    @FXML
    private TextArea tfNote;
    @FXML
    private Button annonce;
    @FXML
    private Label lbId;
    @FXML
    private Label lbss;
    @FXML
    private Label lbfs;
    @FXML
    private Button btnBf;
    @FXML
    private Button btnBs;
    @FXML
    private ImageView Im1;
    @FXML
    private ImageView Im2;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnBack;
    private Credit selectedCredit;

    private File f = null;
    private File l = null;

    DemandeCreditService ddd = new DemandeCreditService();
    UserService s = new UserService();
    @FXML
    private Button lboffers;
    @FXML
    private Button lbdem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void brouseFirst(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG and PNG files", "*.jpg", "*.png"));
        f = fc.showOpenDialog(null);
        if (f != null) {
            btnBf.setText(f.getName());
            Im1.setImage(new Image("file:" + f));
        }
    }

    @FXML
    private void brouseSecond(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG and PNG files", "*.jpg", "*.png"));
        l = fc.showOpenDialog(null);
        if (l != null) {
            btnBs.setText(l.getName());
            Im2.setImage(new Image("file:" + l));
        }
    }

    @FXML
    private void add(ActionEvent event) throws IOException, SQLException {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateDemandeCredit.fxml"));

            Parent root = loader.load();
            CreateDemandeCreditController controller = loader.getController();
            controller.setCredit(selectedCredit);
            System.out.println(selectedCredit);
            User user = s.getById(1);
            int amount = 0;
            try {
                amount = Integer.parseInt(tfAmount.getText());
            } catch (NumberFormatException e) {
                showAlert("Please enter a valid amount", false);
                return; // exit the method if amount is invalid
            }
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
            String note = tfNote.getText().trim();
            if (note.isEmpty()) {
                showAlert("Please enter a note", false);
                return; // exit the method if note is empty
            } else if (!note.matches("[a-zA-Z ]+")) {
                showAlert("Note must only contain letters and spaces", false);
                return; // exit the method if note contains invalid characters
            }

            if (f == null || l == null) {
                showAlert("Both files are required", false);
                return; // exit the method if either file is missing
            }
            DemandeCredit d = new DemandeCredit(selectedCredit, user, amount, sqlDate, note, "in progress", imageEncoderDecoder(f.getAbsolutePath()), imageEncoderDecoder(l.getAbsolutePath()));
            System.out.println(d);
            ddd.ajouter(d);
            System.out.println("ttt");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            System.out.println("ttt");
            alert.setContentText("DEMANDE Credit added successfully.");
            FXMLLoader l = new FXMLLoader(getClass().getResource("MyDemandes.fxml"));
            Parent r = l.load();
            btnAdd.getScene().setRoot(r);
            alert.showAndWait();
        } catch (SQLException ex) {
            // Show an error message if an SQL exception occurs
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while adding the  DEMANDE credit. Please try again.");
            alert.showAndWait();
        }

    }

    private void showAlert(String message, boolean b) {
        Alert alert;
        if (b) {
            alert = new Alert(Alert.AlertType.INFORMATION);
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
        }

        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

  public String imageEncoderDecoder(String path) throws IOException {
    // read image from file
    FileInputStream stream = new FileInputStream(path);

    // get byte array from image stream
    int bufLength = 512; // reduce buffer length to limit encoded string size
    byte[] buffer = new byte[bufLength];
    byte[] data;

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int readLength;
    while ((readLength = stream.read(buffer, 0, bufLength)) != -1) {
        out.write(buffer, 0, readLength);
        if (out.size() >= 5000) { // break loop if encoded string size reaches 1000 characters
            break;
        }
    }

    data = out.toByteArray();
    String imageString = Base64.getEncoder().withoutPadding().encodeToString(data);

    out.close();
    stream.close();
    return imageString;
}


    @FXML
    private void back(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MyDemandes.fxml"));
            Parent root = loader.load();
            btnBack.getScene().setRoot(root);
        } catch (IOException ex) {
                System.out.println(ex.getMessage());

        }
    }

    public void setCredit(Credit credit) {

        tfCat.setText(credit.getCategoryName());
        selectedCredit = credit;
    }

    @FXML
    private void offers(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Offers.fxml"));
            Parent root = loader.load();
            btnBack.getScene().setRoot(root);
        } catch (IOException ex) {
                System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void mydemandes(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MyDemandes.fxml"));
            Parent root = loader.load();
            btnBack.getScene().setRoot(root);
        } catch (IOException ex) {
                System.out.println(ex.getMessage());

        }
    }
}
