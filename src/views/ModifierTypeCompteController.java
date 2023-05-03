/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
    import models.TypeCompte;
    import services.TypeCompteCRUD;
/**
 * FXML Controller class
 *
 * @author BAZINFO
 */
public class ModifierTypeCompteController implements Initializable {

    @FXML
    private Pane pnlOverview;
    @FXML
    private TextArea descriptionfx;
    @FXML
    private TextArea typefx;
    @FXML
    private Label welcomeLb;
    public TableView tableview;
    /**
     * Initializes the controller class.
     */
    
    
      TypeCompteCRUD typeCompteService = new TypeCompteCRUD();
    @FXML
    private Button Fermer;
    
    
     @FXML
    public void exit(){
       Stage stage = (Stage) welcomeLb.getScene().getWindow(); // Get the current stage
    stage.close();
    }  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void Modifier(ActionEvent event) {
        try {
           TypeCompte  tc = (TypeCompte) tableview.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierTypeCompte.fxml"));
            Parent root = loader.load();
             TypeCompteAdminController controller = loader.getController();

            // Passer les données à modifier au contrôleur de l'interface utilisateur
            controller.setData(tc);
            // Afficher la nouvelle fenêtre de modification
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Récupérer la liste des objets TypeCarte à partir de votre classe de service de données
            List<TypeCompte> liste;
            try {
                liste = typeCompteService.recuperer();
                // Parcourir la liste et mettre à jour l'objet TypeCarte correspondant à l'ID de l'objet modifié
                for (int i = 0; i < liste.size(); i++) {
                    if (liste.get(i).getId() == tc.getId()) {
                        liste.set(i, tc);
                        break;
                    }
                    // Rafraîchir le contenu du tableau en remplaçant la liste actuelle par la nouvelle liste modifiée
                    tableview.getItems().clear();
                    tableview.getItems().addAll(liste);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TypeCompteAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void setData(TypeCompte tc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

        
    }
    

