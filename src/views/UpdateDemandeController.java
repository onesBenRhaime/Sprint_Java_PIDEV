/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.Credit;
import models.DemandeCredit;
import java.io.ByteArrayInputStream;
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

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UpdateDemandeController implements Initializable {

    @FXML
    private Text lbAddPage;
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
    private DemandeCredit donnees;
    DemandeCreditService ddd = new DemandeCreditService();
private File f = null;
    private File l = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   @FXML
private void brouseFirst(ActionEvent event) {
    FileChooser fc = new FileChooser();
    fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG and PNG files", "*.jpg", "*.png"));
    f = fc.showOpenDialog(null);
    if (f != null) {
        btnBf.setText(f.getName());
        try {
            String encodedImage = imageToBase64(f.getAbsolutePath());
            donnees.setCin1(encodedImage);
            Im1.setImage(new Image("file:" + f));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
public String imageToBase64(String imagePath) throws IOException {
    FileInputStream fileInputStream = new FileInputStream(imagePath);
    byte[] bytes = new byte[(int) imagePath.length()];
    fileInputStream.read(bytes);
    return Base64.getEncoder().encodeToString(bytes);
}


@FXML
private void brouseSecond(ActionEvent event) {
    FileChooser fc = new FileChooser();
    fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG and PNG files", "*.jpg", "*.png"));
    l = fc.showOpenDialog(null);
    if (l != null) {
        btnBs.setText(l.getName());
        try {
            String encodedImage = imageToBase64(l.getAbsolutePath());
            donnees.setCin2(encodedImage);
            Im2.setImage(new Image("file:" + l));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



    @FXML
    private void add(ActionEvent event) throws IOException {
        int amount = Integer.parseInt(tfAmount.getText());
        String note =tfNote.getText();
          String imageData = donnees.getCin1();


            Im1.setImage(decodeImage(imageData));
            String image2 = donnees.getCin2();


            Im2.setImage(decodeImage(image2));



        DemandeCredit c = new DemandeCredit();
        c.setCredit(donnees.getCredit());
        c.setId(donnees.getId());
        c.setAmount(amount);
        c.setNote(note);
        c.setCin1( imageEncoderDecoder(f.getAbsolutePath()));
        c.setCin2( imageEncoderDecoder(l.getAbsolutePath()));

        try {

            ddd.modifier(c, donnees.getId());
            Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("information Dialog");
            alert0.setHeaderText(null);
            alert0.setContentText("demande modified successfully");
            //((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MyDemandes.fxml"));
            Parent root = loader.load();
            btnAdd.getScene().setRoot(root);
            alert0.showAndWait();

        } catch (SQLException ex) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Failed to modify demande");
            alert1.showAndWait();
        }
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
     public void setTextField(DemandeCredit c) {
        this.donnees = c;
        tfCat.setText(donnees.getCredit().getCategoryName()); // assuming that getMinAmount() returns a numeric value
        tfAmount.setText(String.valueOf(donnees.getAmount())); // assuming that getMinAmount() returns a numeric value
        tfNote.setText(String.valueOf(donnees.getNote())); // assuming that getMaxAmount() returns a numeric value
        try {
            String imageData = donnees.getCin1();


            Im1.setImage(decodeImage(imageData));
            String image2 = donnees.getCin2();


            Im2.setImage(decodeImage(image2));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
public Image decodeImage(String encodedImage) {
    byte[] imageData = Base64.getDecoder().decode(encodedImage);
    ByteArrayInputStream stream = new ByteArrayInputStream(imageData);
    return new Image(stream);
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

}
