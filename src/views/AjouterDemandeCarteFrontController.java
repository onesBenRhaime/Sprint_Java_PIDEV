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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class AjouterDemandeCarteFrontController implements Initializable {

    @FXML
    private TextField description;
    @FXML
    private Label typefx;
    @FXML
    private Label identifierfx;
    @FXML
    private Label descriptionfx;
    @FXML
    private Label cin1;
   
    @FXML
    private TextField identifier;
    @FXML
    private Button Accueilfx1;
    @FXML
    private Button Accueilfx11;
    @FXML
    private Button Accueilfx14;
    @FXML
    private Button Accueilfx15;
    @FXML
    private Button Accueilfx16;
    @FXML
    private Button Accueilfx161;
    @FXML
    private Button annuler;
   
    @FXML
    private ListView img;
    @FXML
    private Button insérer;

    File selectedfile;

    String path_img;
    File selectedfile2;

    String path_img2;

    Upload u = new Upload();
    @FXML
    private Button ajouterfx;
    @FXML
    private Label emailfx1;
    @FXML
    private ComboBox<TypeCarte> typecartee;
    @FXML
    private TextField email;
    @FXML
    private Label cin12;
    @FXML
    private ListView img2;
    @FXML
    private Button insérer2;
    @FXML
    private Label welcome;
 

    /**
     * Initializes the controller class.
     */
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
                    } catch (IOException ex) {
                        Logger.getLogger(AjouterDemandeCarteFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    System.out.println("Fichier erroné");
                }

            }

        });
        insérer2.setOnAction(new EventHandler<ActionEvent>() {
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

                        img2.getItems().add(selectedfile2.getName());

                        path_img2 = selectedfile2.getAbsolutePath();
                    } catch (IOException ex) {
                        Logger.getLogger(AjouterDemandeCarteFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    System.out.println("Fichier erroné");
                }

            }

        });


        ajouterfx.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    TypeCarte typecarte = typecartee.getSelectionModel().getSelectedItem();
                    System.out.println("...."+ typecarte);
                    CarteBancaire pp = new CarteBancaire(email.getText(), identifier.getText(), description.getText(),
                            selectedfile.getName(),selectedfile2.getName(), typecarte.getId());
                    
                    DemandeCarte PROD = new DemandeCarte();
                    try {
                        PROD.ajouter(pp);
                    } catch (SQLException ex) {
                        Logger.getLogger(AjouterDemandeCarteFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                    alert0.setTitle("information Dialog");
                    alert0.setHeaderText(null);
                    alert0.setContentText("Ajout avec succes ");
                    alert0.show();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichedemandecartefront.fxml"));
                    Parent root = loader.load();
                    welcome.getScene().setRoot(root);
                    //((Node) event.getSource()).getScene().getWindow().hide();
                    
                } catch (IOException ex) {
                    Logger.getLogger(AjouterDemandeCarteFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                email.clear();
                description.clear();
                identifier.clear();
                typecartee.getItems().clear();
                img.getItems().clear();

            }
        });
    }
        
        
        
        
        
    
//        ListerCategorie();
//        insérer.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//
//                FileChooser fc = new FileChooser();
//                fc.getExtensionFilters().addAll(
//                        new FileChooser.ExtensionFilter("image", "*.jpg", "*.png")
//                );
//                selectedfile = fc.showOpenDialog(null);
//                if (selectedfile != null) {
//                    try {
//                        resizeFile(selectedfile.getAbsolutePath(), "C:\\xampp\\htdocs\\images\\" + selectedfile.getName(), 227, 207);
//
//                        img.getItems().add(selectedfile.getName());
//
//                        path_img = selectedfile.getAbsolutePath();
//                    } catch (IOException ex) {
//                        Logger.getLogger(AjouterDemandeCarteFrontController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                } else {
//                    System.out.println("Fichier erroné");
//                }
//
//            }
//
//        });
//        insérer2.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//
//                FileChooser fc = new FileChooser();
//                fc.getExtensionFilters().addAll(
//                        new FileChooser.ExtensionFilter("image", "*.jpg", "*.png")
//                );
//                selectedfile2 = fc.showOpenDialog(null);
//                if (selectedfile2 != null) {
//                    try {
//                        resizeFile(selectedfile2.getAbsolutePath(), "C:\\xampp\\htdocs\\images\\" + selectedfile2.getName(), 227, 207);
//
//                        img2.getItems().add(selectedfile2.getName());
//
//                        path_img2 = selectedfile2.getAbsolutePath();
//                    } catch (IOException ex) {
//                        Logger.getLogger(AjouterDemandeCarteFrontController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                } else {
//                    System.out.println("Fichier erroné");
//                }
//
//            }
//
//        });
//
//
//        ajouterfx.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                TypeCarte typecarte = typecartee.getSelectionModel().getSelectedItem();
//
//                cartebanc pp = new cartebanc(email.getText(), identifier.getText(), description.getText(),
//                        selectedfile.getName(),selectedfile2.getName(), typecarte);
//
//                DemandeCarte PROD = new DemandeCarte();
//                try {
//                    PROD.ajouter(pp);
//                } catch (SQLException ex) {
//                    Logger.getLogger(AjouterDemandeCarteFrontController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
//                alert0.setTitle("information Dialog");
//                alert0.setHeaderText(null);
//                alert0.setContentText("Ajout avec succes ");
//                alert0.show();
//                ((Node) event.getSource()).getScene().getWindow().hide();
//
//            }
//        });
//        annuler.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                email.clear();
//                description.clear();
//                identifier.clear();
//                typecartee.getItems().clear();
//                img.getItems().clear();
//
//            }
//        });
//    }

    private void ListerCategorie() {

     //   TypeCarteService categoriecrud = new TypeCarteService();
        ObservableList<TypeCarte> list = FXCollections.observableArrayList();
        try {
            String req = " select * from type_carte ";

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

        typecartee.setItems(null);
        typecartee.setItems(list);
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
    private void accueilAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherDemandeCarteFront.fxml"));
            Parent root = loader.load();
            typefx.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void goToTransactions(ActionEvent event) {
    }

    @FXML
    private void goToSendMoney(ActionEvent event) {
    }

    @FXML
    private void goToAccounts(ActionEvent event) {
    }

    private void importer(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listedemandchequefront.fxml"));
            Parent root = loader.load();
            typefx.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

