/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import static views.ModifierfrontController.carteB;
import services.DemandeCarte;
import services.Upload;
import models.CarteBancaire;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
public class DetailsCardController implements Initializable {

    
    @FXML
    private Button retourfx;
 private CarteBancaire bonplan1;
    @FXML
    private ImageView img1;
    @FXML
    private Label cins1;
    @FXML
    private Label cins2;
    @FXML
    private ImageView img2;
  
    File selectedfile;
    File selectedfile2;
String imagePath2;
    String path_img;
   @FXML
    private ListView imgg;
    @FXML
    private ListView imgg2;
    
    Upload u = new Upload();
    /**
     * Initializes the controller class.
     */
    DemandeCarte ps = new DemandeCarte();
    ObservableList<CarteBancaire> obs;
 public void setbonplan(CarteBancaire bonplan11) {
        this.bonplan1 = bonplan11;
        cins1.setText(bonplan11.getCin_s1());
         cins2.setText(bonplan11.getCin_s2());
      
    

    String imagePath = "C:\\xampp\\htdocs\\images\\"+ bonplan1.getCin_s1();
    String imagePath2 = "C:\\xampp\\htdocs\\images\\"+ bonplan1.getCin_s2();   
        // Create an ImageView object
        ImageView imageView = new ImageView();
        ImageView imageView2 = new ImageView();
        // Create a File object with the path of your image
        File file = new File(imagePath);
        File file2 = new File(imagePath2);
        // Check if the file exists
        if (file.exists()&& file2.exists()) {
            // Create an Image object with the file path
            Image image = new Image(file.toURI().toString());
            Image image2 = new Image(file2.toURI().toString());
            // Set the image to the ImageView
            this.img1.setImage(image);
            this.img2.setImage(image2);
            System.out.println("dshgsdghc"+ image);
                  
        } else {
            System.out.println("Image not found.");
        }

    }
 public void setImages(ImageView cinS1, ImageView cinS2) {
        img1.setImage(cinS1.getImage());
        img2.setImage(cinS2.getImage());
        
    }

    public void setLabels(String labelText1, String labelText2) {
        cins1.setText(labelText1);
        cins2.setText(labelText2);
    }
    @FXML
    private void back(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DemandeCarte.fxml"));
        Parent root = loader.load();
        retourfx.getScene().setRoot(root);
    }
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    img1.setVisible(true);
    img2.setVisible(true);
           

                  

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

    
}
