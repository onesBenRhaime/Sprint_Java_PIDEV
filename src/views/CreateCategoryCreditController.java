/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.CategoryCredit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import services.CategoryCreditService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CreateCategoryCreditController implements Initializable {
    CategoryCreditService ps =new CategoryCreditService();

    @FXML
    private TextArea tfDesc;
    @FXML
    private TextField tfName;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnBack;
    @FXML
    private Text lbCreateCat;
    @FXML
    private Button profilfx;
    @FXML
    private Button lbcat;
    @FXML
    private Button lboffer;
    @FXML
    private Button lbdem;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void add(ActionEvent event) {
        try {
            if (tfName.getText().isEmpty()) {
                Tooltip tooltip = new Tooltip("please enter name of category");
                tfName.setTooltip(tooltip);
                tooltip.show(tfName, tfName.getLayoutX(), tfName.getLayoutY() + tfName.getHeight());
                tfName.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.isEmpty()) {
                        tooltip.hide();
                    }
                });
                return;
            }
             if (tfDesc.getText().isEmpty()) {
                Tooltip tooltip = new Tooltip("please enter description of category");
                tfDesc.setTooltip(tooltip);
                tooltip.show(tfDesc, tfDesc.getLayoutX(), tfDesc.getLayoutY() + tfDesc.getHeight());
                tfDesc.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.isEmpty()) {
                        tooltip.hide();
                    }
                });
                return;
            }
             String name =tfName.getText();
             String description =tfDesc.getText();
        CategoryCredit p =new CategoryCredit (name,description);
            ps.ajouter(p);
            Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("information Dialog");
            alert0.setHeaderText(null);
            alert0.setContentText("categry added succesfuly ");
            alert0.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreditCategories.fxml"));
            Parent root = loader.load();
            tfName.getScene().setRoot(root);
            //((Node) event.getSource()).getScene().getWindow().hide();
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
        

        
        

    }
     @FXML
    private void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreditCategories.fxml"));
            Parent root = loader.load();
            tfName.getScene().setRoot(root);
        } catch (IOException ex) {
                System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void categories(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreditCategories.fxml"));
            Parent root = loader.load();
            lbcat.getScene().setRoot(root);
        } catch (IOException ex) {
                System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void offers(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Loans.fxml"));
            Parent root = loader.load();
            lboffer.getScene().setRoot(root);
        } catch (IOException ex) {
                System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void demandes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DemandesCredit.fxml"));
            Parent root = loader.load();
            lbdem.getScene().setRoot(root);
        } catch (IOException ex) {
                System.out.println(ex.getMessage());

        }
    }
    
}
