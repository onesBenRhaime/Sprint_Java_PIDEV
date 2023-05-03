
package views;

import services.DemandeCarte;
import services.DemandeCheque;
import services.TypeChequeService;
import models.CarnetCheque;
import models.CarteBancaire;
import models.TypeCarte;
import models.TypeCheque;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class AjouterdemandechequeController implements Initializable {

    @FXML
    private Label descriptionfx;
    @FXML
    private TextField description;
      @FXML
    private Label typefx;
    @FXML
    private Label identifierfx;
    @FXML
    private TextField email;
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
    private Label emailfx;
    private TextField type;
 CarnetCheque typecheque = new CarnetCheque();

    DemandeCheque ps = new DemandeCheque();
    @FXML
    private Label welcome;
    @FXML
    private ComboBox<TypeCheque> typefxx;
    
    public static CarnetCheque carteB;
    /**
     * Initializes the controller class.
     */
    
     public boolean isAlphabetic(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        return s.matches("[a-zA-Z]+");
    }
    // Methode Num ou Non
         public boolean isNumeric (String s) {
           try {
               int x = Integer.parseInt(s);    
             }catch (NumberFormatException ex) {
                    return false;
             }
                    return true;
            //  return !s.matches("[^a-zA-Z]*");
          }//End

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ListerType();
    }    
    public void setCarteBancaire(CarnetCheque p) {
    carteB = p;
    typefxx.getSelectionModel().select(carteB.getTypeCheque());
    }
 private void ListerType() {

        DemandeCheque categoriecrud = new DemandeCheque();
        ObservableList<TypeCheque> list = FXCollections.observableArrayList();
        try {
            String req = " select id,`description`,`nom` from `Type_carnet`  ";

            Connection conn = MyConnection.getInstance().getCon();

            PreparedStatement pst = conn.prepareStatement(req);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                TypeCheque c = new TypeCheque(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(c);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        typefxx.setItems(null);
        typefxx.setItems(list);
    }
   
    @FXML
    private void Ajouter(ActionEvent event) {
        
         try {
            if (description.getText().isEmpty()) {
                Tooltip tooltip = new Tooltip("Le champ nom est obligatoire.");
                description.setTooltip(tooltip);
                tooltip.show(description, description.getLayoutX(), description.getLayoutY() + description.getHeight());
                description.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.isEmpty()) {
                        tooltip.hide();
                    }
                });
                return;
            }
            if (email.getText().isEmpty()) {
                Tooltip tooltip = new Tooltip("Le champ description est obligatoire.");
                email.setTooltip(tooltip);
                tooltip.show(email, email.getLayoutX(), email.getLayoutY() + email.getHeight());
                email.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.isEmpty()) {
                        tooltip.hide();
                    }
                });
                return;
            }
            if (identifier.getText().isEmpty()) {
                Tooltip tooltip = new Tooltip("Le champ startnum est obligatoire.");
                identifier.setTooltip(tooltip);
                tooltip.show(identifier, identifier.getLayoutX(), identifier.getLayoutY() + identifier.getHeight());
                identifier.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.isEmpty()) {
                        tooltip.hide();
                    }
                });
                return;
            }
            if (type.getText().isEmpty()) {
                Tooltip tooltip = new Tooltip("Le champ startnum est obligatoire.");
                type.setTooltip(tooltip);
                tooltip.show(type, type.getLayoutX(), type.getLayoutY() + type.getHeight());
                type.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.isEmpty()) {
                        tooltip.hide();
                    }
                });
                return;
            }
            
           
          
          
              DemandeCheque demandecarte = new DemandeCheque();

            String email = this.email.getText();
            String description = this.description.getText();
            String identifier = this.identifier.getText();
            TypeCheque c = typefxx.getSelectionModel().getSelectedItem();

            CarnetCheque p = new CarnetCheque(  email, description, identifier, c);
            ps.ajouter(p);
            Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("information Dialog");
            alert0.setHeaderText(null);
            alert0.setContentText("Ajout avec succes ");
            alert0.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listedemandchequefront.fxml"));
            Parent root = loader.load();
            welcome.getScene().setRoot(root);
            //((Node) event.getSource()).getScene().getWindow().hide();
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }
    }

    @FXML
    private void Annuler(ActionEvent event) {
    }
    
     @FXML
    private void accueilAction(ActionEvent event) {
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

    
}
