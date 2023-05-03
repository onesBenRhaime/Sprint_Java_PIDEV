/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import services.DemandeCarte;
import services.TypeCarteService;
import services.Upload;
import models.CarteBancaire;
import models.TypeCarte;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class ModifierfrontController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private Button Modifierfx;
    @FXML
    private ListView img;
    @FXML
    private Button insérer;
    @FXML
    private TextArea descriptionfx;
    @FXML
    private TextField Identifierfx;
    @FXML
    private ComboBox<TypeCarte> typefx;
    private TextField idfx;
    @FXML
    private TextField emailfx;
    @FXML
    private ImageView imgg;
    
    private CarteBancaire CarteBancaire;
    private CarteBancaire donnees;
    File selectedfile;
    File selectedfile2;

    String path_img;
    String path_img2;

    Upload u = new Upload();
    public static CarteBancaire carteB;
    private String identifier;
    @FXML
    private Button insérer1;
    @FXML
    private ImageView imgg1;
    @FXML
    private ListView img1;
    @FXML
    private ImageView imgg2;

    
    
    
     public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
  
    
    public void setCarteBancaire(CarteBancaire p) {
        carteB = p;
       this.img.setAccessibleText(carteB.getCin_s1());
         this.img1.setAccessibleText(carteB.getCin_s2());
        //String imageURI = new File("C:\\xampp\\htdocs\\images"+p.getImage().toString()).toURI().toString();
        //System.out.println(imageURI);
        this.emailfx.setText(carteB.getEmail());
        this.Identifierfx.setText(carteB.getIdentifier());
        this.descriptionfx.setText(carteB.getDescription());
   
        typefx.getSelectionModel().select(carteB.getIdtypecarte_id());
        
         String imagePath = "C:\\xampp\\htdocs\\images\\"+ carteB.getCin_s1().toString();
       String imagePath2 = "C:\\xampp\\htdocs\\images\\"+ carteB.getCin_s2().toString();
        // Create an ImageView object
        ImageView imageView = new ImageView();
        ImageView imageView2 = new ImageView();
        // Create a File object with the path of your image
        File file = new File(imagePath);
        File file2 = new File(imagePath2);
       
        // Check if the file exists
        if (file.exists()&&file2.exists()) {
            // Create an Image object with the file path
            
            
            Image image = new Image(file.toURI().toString());
            Image image2 = new Image(file2.toURI().toString());
            
            // Set the image to the ImageView
            this.imgg.setImage(image);
            this.imgg2.setImage(image);
        } else {
            System.out.println("Image not found.");
        }
 
 
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ListerCategorie();

        insérer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("image", "*.jpg", "*.png")
                );
                selectedfile = fc.showOpenDialog(null);
                if (selectedfile != null) {
                    try {
                        resizeFile(selectedfile.getAbsolutePath(), "C:\\xampp\\htdocs\\images\\" + selectedfile.getName(), 227, 207);

                        img.getItems().add(selectedfile.getName());

                        path_img = selectedfile.getAbsolutePath();
                        File file = new File(path_img);

                        // Check if the file exists
                        if (file.exists()) {

                            // Create an Image object with the file path
                            Image image = new Image(file.toURI().toString());

                            // Set the image to the ImageView
                            imgg.setImage(image);
                        } else {
                            System.out.println("Image not found.");
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(AjouterDemandeCarteFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    System.out.println("Fichier erroné");
                }

            }

        });
        insérer1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("image", "*.jpg", "*.png")
                );
                selectedfile2 = fc.showOpenDialog(null);
                if (selectedfile2 != null) {
                    try {
                        resizeFile(selectedfile2.getAbsolutePath(), "C:\\xampp\\htdocs\\images\\" + selectedfile2.getName(), 227, 207);

                        img1.getItems().add(selectedfile2.getName());

                        path_img2 = selectedfile2.getAbsolutePath();
                        File file = new File(path_img2);

                        // Check if the file exists
                        if (file.exists()) {

                            // Create an Image object with the file path
                            Image image = new Image(file.toURI().toString());

                            // Set the image to the ImageView
                            imgg2.setImage(image);
                        } else {
                            System.out.println("Image not found.");
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(AjouterDemandeCarteFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    System.out.println("Fichier erroné");
                }

            }

        });
        Modifierfx.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                DemandeCarte produitcrud = new DemandeCarte();

                

                TypeCarte typecarte = typefx.getSelectionModel().getSelectedItem();
                String imgN = img.getItems().toString();
                String imgN2 = img1.getItems().toString();
                imgN = imgN.substring(1, imgN.length() - 1);
                  imgN2 = imgN2.substring(1, imgN2.length() - 1);
                CarteBancaire p = new CarteBancaire(emailfx.getText(),Identifierfx.getText(), descriptionfx.getText(), selectedfile.getName(),selectedfile2.getName(), typecarte.getId());
                System.out.println(img.getItems().toString());
                System.out.println(img1.getItems().toString());
                if (!emailfx.getText().equalsIgnoreCase("")
                        && !descriptionfx.getText().equalsIgnoreCase("")
                        && !Identifierfx.getText().equalsIgnoreCase("")
                        
                        ) {

                    try {

                        produitcrud.modifier2(p);

                    } catch (SQLException ex) {
                        Logger.getLogger(ModifierfrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                    alert0.setTitle("information Dialog");
                    alert0.setHeaderText(null);
                    alert0.setContentText("Votre modification est enregistrée avec succes ");
                    alert0.show();
                    ((Node) event.getSource()).getScene().getWindow().hide();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Echec de la modification");
                    alert.setHeaderText(null);
                    alert.setContentText("Attention ! Verifier les données saisie (Pas de champs vides)");
                    alert.showAndWait();
                }
            }

        });
    }

    @FXML
    private void prefixe(MouseEvent event) {
    }

    private void ListerCategorie() {

        TypeCarteService categoriecrud = new TypeCarteService();
        ObservableList<TypeCarte> list = FXCollections.observableArrayList();
        try {
            String req = " select id,`nom`,`description` from `type_carte`  ";

            Connection conn = MyConnection.getInstance().getCon();

            PreparedStatement pst = conn.prepareStatement(req);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                TypeCarte c = new TypeCarte(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(c);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        typefx.setItems(null);
        typefx.setItems(list);
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
    private void modifier(ActionEvent event) {
    }

    void setData(CarteBancaire donnees) {
       this.donnees = donnees;
       
       typefx.getSelectionModel().getSelectedItem();
        emailfx.setText(donnees.getEmail());
        Identifierfx.setText(donnees.getIdentifier());
        descriptionfx.setText(donnees.getDescription());
        
        
    }

 
}
