/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.Personne;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.PersonneService;

/**
 * FXML Controller class
 *
 * @author don7a
 */
public class AfficherPersonnesController implements Initializable {

    @FXML
    private TableView<Personne> tableview;
    @FXML
    private TableColumn<Personne, String> nomCol;
    @FXML
    private TableColumn<Personne, String> prenomCol;
    @FXML
    private TableColumn<Personne, Integer> ageCol;

    PersonneService ps = new PersonneService();
     ObservableList<Personne> obs ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Personne> personnes = ps.recuperer();
            obs = FXCollections.observableArrayList(personnes);
            tableview.setItems(obs);
            nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
      
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }    

    @FXML
    private void deleterandom(ActionEvent event) {
        obs.remove(0);
    }
    
}
