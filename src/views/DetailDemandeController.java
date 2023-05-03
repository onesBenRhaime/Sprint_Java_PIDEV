/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.DemandeCredit;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DetailDemandeController implements Initializable {

    @FXML
    private Text lbCat;
    @FXML
    private Text lbStatus;
    @FXML
    private Text lbCin2;
    @FXML
    private Text lbCin1;
    @FXML
    private Text lbDate;
    @FXML
    private Text lbNote;
    @FXML
    private Text lbAmount;
    @FXML
    private Text Cat;
    @FXML
    private Text amount;
    @FXML
    private Text note;
    @FXML
    private Text date;
    @FXML
    private ImageView im1;
    @FXML
    private ImageView im2;
    @FXML
    private Text status;
    private DemandeCredit donnees;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setTextField(DemandeCredit c) {
        this.donnees = c;
        Cat.setText(donnees.getCredit().getCategoryName()); // assuming that getMinAmount() returns a numeric value
        amount.setText(String.valueOf(donnees.getAmount())); // assuming that getMinAmount() returns a numeric value
        note.setText(String.valueOf(donnees.getNote())); // assuming that getMaxAmount() returns a numeric value
        date.setText(String.valueOf(donnees.getCreatedAt()));
        try {
            String imageData = donnees.getCin1();


            im1.setImage(decodeImage(imageData));
            String image2 = donnees.getCin2();


            im2.setImage(decodeImage(image2));


        } catch (Exception e) {
            e.printStackTrace();
        }
        status.setText(String.valueOf(donnees.getStatus()));

    }
public Image decodeImage(String encodedImage) {
    
    byte[] imageData = Base64.getDecoder().decode(encodedImage);
    ByteArrayInputStream stream = new ByteArrayInputStream(imageData);
    return new Image(stream);
}
}
