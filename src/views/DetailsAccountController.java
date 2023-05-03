/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Compte;

/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class DetailsAccountController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private Label welcomeLb;
    @FXML
    private Button Fermer;

    /**
     * Initializes the controller class.
     */
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

      @FXML
    public void exit(){
         Stage stage = (Stage) welcomeLb.getScene().getWindow(); // Get the current stage
    stage.close();
    }   

    void setData(Compte tc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
