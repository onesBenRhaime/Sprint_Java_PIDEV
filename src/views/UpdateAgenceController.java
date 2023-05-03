/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.Agence;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.AgenceService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UpdateAgenceController implements Initializable {

    @FXML
    private TextField cName;
    @FXML
    private TextField cDesc;
    @FXML
    private Button btnSave;

    private Agence agence;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void UpdateAgence(ActionEvent event) throws SQLException {
        String name = cName.getText();
        String description = cDesc.getText();
        AgenceService bgs = new AgenceService();
       this.agence.setName(name);
       this.agence.setDescription(description);
       
        bgs.update(agence);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void initData(Agence agence) {
        // initialize the text fields with the data from the Blog object
        this.agence = agence;
        cName.setText(agence.getName());
        cDesc.setText(agence.getDescription());
    }
}
