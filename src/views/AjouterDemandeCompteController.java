/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package views;

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import models.Compte;
import models.TypeCompte;
import services.CompteCRUD;
import services.TransactionCRUD;
import services.TypeCompteCRUD;
import services.Upload;
import utils.MyConnection;

public class AjouterDemandeCompteController implements Initializable {

    @FXML
    private Label welcomeLb;
    @FXML
    private Label welcomeLb1;
    @FXML
    private Label welcomeLb11;
    @FXML
    private TextField maxAfx;
    @FXML
    private Label side1fx;
    @FXML
    private Label side2fx;
    @FXML
    private Label otherfx;
    @FXML
    private TextField minAfx;
    @FXML
    private TextField redAfx;

    File selectedfile;

    String path_img;

    Upload u = new Upload();
    @FXML
    private Button insérer1;
    @FXML
    private Button insérer2;
    @FXML
    private Button insérer3;
    @FXML
    private ComboBox<TypeCompte> typesfx;

    CompteCRUD compteService = new CompteCRUD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListerCategorie();

    }

    public void resizeFile(String imagePathToRead, String imagePathToWrite, int resizeWidth, int resizeHeight) throws IOException {

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

    private void ListerCategorie() {

        //   TypeCompteCRUD typecompteservices = new TypeCompteCRUD();
        ObservableList<TypeCompte> list = FXCollections.observableArrayList();
        try {
            String req = " select * from type_compte  ";
            Connection cnx = MyConnection.getInstance().getCon();

            PreparedStatement pst = cnx.prepareStatement(req);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                TypeCompte t = new TypeCompte(rs.getInt(1),rs.getString(2),rs.getString(3));
                list.add(t);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        typesfx.setItems(null);
        typesfx.setItems(list);
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        try {
            insérer1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    FileChooser fc = new FileChooser();
                    fc.getExtensionFilters().addAll(
                            new FileChooser.ExtensionFilter("image", "*.jpg", "*.png")
                    );
                    selectedfile = fc.showOpenDialog(null);
                    if (selectedfile != null) {
                        try {
                            resizeFile(
                                    selectedfile.getAbsolutePath(), "C:\\xampp\\htdocs\\img\\" + selectedfile.getName(), 227, 207
                            );

                            side1fx.setText(selectedfile.getName());
                            System.out.println("side1fx : " + side1fx);
                            //   path_img = selectedfile.getAbsolutePath();
                        } catch (IOException ex) {
                            Logger.getLogger(AjouterDemandeCompteController.class.getName()).log(Level.SEVERE, null, ex);
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
                    selectedfile = fc.showOpenDialog(null);
                    if (selectedfile != null) {
                        try {
                            resizeFile(
                                    selectedfile.getAbsolutePath(), "C:\\xampp\\htdocs\\img\\" + selectedfile.getName(), 227, 207
                            );

                            side2fx.setText(selectedfile.getName());
                            System.out.println("side2fx : " + side2fx);
                            path_img = selectedfile.getAbsolutePath();
                        } catch (IOException ex) {
                            Logger.getLogger(AjouterDemandeCompteController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        System.out.println("Fichier erroné");
                    }

                }

            });

            insérer3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    FileChooser fc = new FileChooser();
                    fc.getExtensionFilters().addAll(
                            new FileChooser.ExtensionFilter("image", "*.jpg", "*.png")
                    );
                    selectedfile = fc.showOpenDialog(null);
                    if (selectedfile != null) {
                        try {
                            resizeFile(
                                    selectedfile.getAbsolutePath(), "C:\\xampp\\htdocs\\img\\" + selectedfile.getName(), 227, 207
                            );

                            otherfx.setText(selectedfile.getName());
                            System.out.println("otherfx : " + otherfx);
                            path_img = selectedfile.getAbsolutePath();
                        } catch (IOException ex) {
                            Logger.getLogger(AjouterDemandeCompteController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        System.out.println("Fichier erroné");
                    }

                }

            });

            String redaccount = redAfx.getText();
            String maxAmount = maxAfx.getText();
            String minAmount = minAfx.getText();
            /// String user = this.user.getText();
            String side1 = side1fx.getText();
            String side2 = side2fx.getText();
            String other = otherfx.getText();
          //  System.out.println(side1);
            
            TypeCompte c = typesfx.getSelectionModel().getSelectedItem();
            System.out.println(c);
            Compte tc = new Compte(2, c.getId(), side1, side2, other, maxAmount, minAmount, redaccount);

            compteService.ajouter(tc);

            Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("Information dialog box");
            alert0.setHeaderText(null);
            alert0.setContentText("Added successfully ");
            alert0.show();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterDemandeCompteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void goToHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Client.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void goToTransactions(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WireTransferClient.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void goToSendMoney(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SendMoneyClient.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void goToAccounts(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ComptesClient.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
