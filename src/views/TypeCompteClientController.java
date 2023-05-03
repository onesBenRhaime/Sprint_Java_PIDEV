/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.TypeCompte;
import services.TypeCompteCRUD;
/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class TypeCompteClientController implements Initializable {

  
    private VBox cardContainer;
    /**
     * Initializes the controller class.
     */
     TypeCompteCRUD typeCompteService = new TypeCompteCRUD();
    @FXML
    private Label descfx;
    @FXML
    private Label nomLabell1;
    @FXML
    private Label di1;
    @FXML
    private Label idLabel1;
    @FXML
    private Label nomLabell11;
    @FXML
    private Label di11;
    @FXML
    private Label idLabel11;
    @FXML
    private Label typefx;
    @FXML
    private AnchorPane anch;
    @FXML
    private Label typefx2;
    @FXML
    private Label descfx2;
    @FXML
    private Label typefx3;
    @FXML
    private Label descfx3;
    @FXML
    private Label welcomeLb;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            List<TypeCompte> cardDataList =  typeCompteService.recuperer();
            System.out.println("list  : "+ cardDataList);     
           
       //    for (TypeCompte typeCompte : cardDataList) {
                
               typefx.setText( cardDataList.get(0).getType() + "\n");
               descfx.setText( cardDataList.get(0).getDescription() + "\n");
               typefx2.setText( cardDataList.get(1).getType() + "\n");
               descfx2.setText( cardDataList.get(1).getDescription() + "\n");
               typefx3.setText( cardDataList.get(2).getType() + "\n");
               descfx3.setText( cardDataList.get(2).getDescription() + "\n");
              
       //}
        } catch (SQLException ex) {
            Logger.getLogger(TypeCompteClientController.class.getName()).log(Level.SEVERE, null, ex);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TypeCompteClient.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
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
    private void deposit(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterDemandeCompte.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void yours(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ComptesClient.fxml"));
            Parent root = loader.load();
            welcomeLb.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
}
