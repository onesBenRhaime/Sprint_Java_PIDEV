/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.Personne;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.PersonneService;

/**
 * FXML Controller class
 *
 * @author don7a
 */
public class AjouterPersonneController implements Initializable {
    PersonneService ps = new PersonneService();

    @FXML
    private TextField nomTF;
    @FXML
    private TextField prenomTF;
    @FXML
    private TextField ageTF;
    @FXML
    private Label welcomeLB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterPersonne(ActionEvent event) {
        try {
            String nom = nomTF.getText();
            String prenom = prenomTF.getText();
            int age = Integer.parseInt(ageTF.getText());
            Personne P=  new Personne (age,nom,prenom);
            ps.ajouter(P);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void AfficherPersonne(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherPersonnes.fxml"));
            Parent root = loader.load();
            welcomeLB.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    public void setUsername (String username){
    welcomeLB.setText("Welcome " + username);
    }
    
}
