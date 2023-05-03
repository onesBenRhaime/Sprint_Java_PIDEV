/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.CarteBancaire;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class DetailtestController implements Initializable {

    @FXML
    private AnchorPane lkolha;
    @FXML
    private ImageView image;
    @FXML
    private Label welcome;
   public static CarteBancaire carte;
   
     private CarteBancaire bonplan1;
    /**
     * Initializes the controller class.
     */
  
    
    
    public void setbonplan(CarteBancaire bonplan11) {
        this.bonplan1 = bonplan11;
       
             
      File f = new File("C:\\xampp\\htdocs\\images\\" + bonplan1.getCin_s1());

          image.setImage(new Image(f.toURI().toString()));
//          
//  String imagePath = "C:\\xampp\\htdocs\\images\\"+ bonplan1.getCin_s1().toString();
//       
//        // Create an ImageView object
//        ImageView imageView = new ImageView();
//        // Create a File object with the path of your image
//        File file = new File(imagePath);
//       
//        // Check if the file exists
//        if (file.exists()) {
//            // Create an Image object with the file path
//            Image image = new Image(file.toURI().toString());
//            // Set the image to the ImageView
//            this.image.setImage(image);
//        } else {
//            System.out.println("Image not found.");
//        }

    }
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }  
    
     
    
}
