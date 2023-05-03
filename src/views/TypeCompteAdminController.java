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
import java.util.Optional;
    import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
    import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
    import javafx.scene.control.Label;
    import javafx.scene.control.ScrollPane;
    import javafx.scene.control.TableColumn;
    import javafx.scene.control.TableView;
    import javafx.scene.control.TextField;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
    import models.TypeCompte;
    import services.TypeCompteCRUD;

    /**
     * FXML Controller class
     *
     * @author BAZINFO
     */
    public class TypeCompteAdminController implements Initializable {

        @FXML
        private ScrollPane scroll;
        @FXML
        private Button profilfx;
        @FXML
        private TextField searchField;
        @FXML
        private TableView<TypeCompte> tableview;
        @FXML
        private TableColumn<TypeCompte, String> typeCol;
        @FXML
        private TableColumn<TypeCompte, String> descCol;

        TypeCompteCRUD typeCompteService = new TypeCompteCRUD();
        ObservableList<TypeCompte> obs;
        @FXML
        private Label welcomeLb;
        private TypeCompte t;

        /**
         * Initializes the controller class.
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
            try {
                // TODO

                List<TypeCompte> types = typeCompteService.recuperer();
                obs = FXCollections.observableArrayList(types);
                tableview.setItems(obs);
                typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
                descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
             } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }    

        @FXML
        private void goToAjouter(ActionEvent event) {
             try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterTypeCompteAdmin.fxml"));
                Parent root = loader.load();
                welcomeLb.getScene().setRoot(root);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }       

        }   

        @FXML
        private void deleteRandom(ActionEvent event) throws SQLException {
            int selectedIndex = obs.indexOf( tableview.getSelectionModel().getSelectedItem());
            System.out.println(obs.get(0)); 
//            typeCompteService.supprimer(obs.get(selectedIndex));
//            obs.remove(selectedIndex);

        if (selectedIndex >= 0 ){
            // Créer une boîte de dialogue d'alerte
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Do you really want to delete the selected line ?");

            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                // Supprimer la ligne sélectionnée                
                   typeCompteService.supprimer(obs.get(selectedIndex));
                    obs.remove(selectedIndex);             
            }
        }
            
        }

        @FXML
        private void goToTransactions(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("WireTransferAdmin.fxml"));
                Parent root = loader.load();
                welcomeLb.getScene().setRoot(root);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

        @FXML
        private void goToEdit(ActionEvent event) {
             
            try {
                //1.Créer la fenetre
                Stage s=new Stage();
                //2.charger le fichier FXML
                FXMLLoader lo = new FXMLLoader (getClass().getResource("ModifierTypeCompte.fxml"));
                //3.charger  le contenuer du fichier nouvelle.fxml
                Parent root = lo.load();
                //4.cree la scenne
                Scene sc =new Scene (root);
                //5 lier  la scenne  sc au stage a cree
                s.setScene(sc);
                //6.afficher la stage
                s.show();
            } catch (IOException ex) {
                Logger.getLogger(TypeCompteAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }


        }

        @FXML
        private void goToSendMoney(ActionEvent event) {
             try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SendMoneyAdmin.fxml"));
                Parent root = loader.load();
                welcomeLb.getScene().setRoot(root);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

        @FXML
        private void goToAccounts(ActionEvent event) {  
           try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TypeComptAdmin.fxml"));
                Parent root = loader.load();
                welcomeLb.getScene().setRoot(root);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        @FXML
        private void goToAccountsDemonds(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ComptesAdmin.fxml"));
                Parent root = loader.load();
                welcomeLb.getScene().setRoot(root);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            } 
        }

        @FXML
        private void goToHome(ActionEvent event) {
             try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
                Parent root = loader.load();
                welcomeLb.getScene().setRoot(root);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

    void setData(TypeCompte tc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
