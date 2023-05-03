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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import services.CategoryCreditService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UpdateCategoryCreditController implements Initializable {

    @FXML
    private TextArea tfDesc;
    @FXML
    private TextField tfName;
    @FXML
    private Text lbUpdateCat;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnBack;
    CategoryCredit cat = null;
    private boolean update;
    int catId;
    CategoryCreditService ps = new CategoryCreditService();
    private CategoryCredit donnees;
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
    private void update(ActionEvent event) throws IOException, SQLException {

        String name = tfName.getText();
        String description = tfDesc.getText();
        CategoryCredit c = new CategoryCredit();
        c.setId(donnees.getId());
        c.setName(name);
        c.setDescription(description);
        try {

            ps.modifier(c, donnees.getId());
            Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("information Dialog");
            alert0.setHeaderText(null);
            alert0.setContentText("category modified successfully");
            //((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreditCategories.fxml"));
            Parent root = loader.load();
            btnUpdate.getScene().setRoot(root);
            alert0.showAndWait();

        } catch (SQLException ex) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Failed to modify category");
            alert1.showAndWait();
            ex.printStackTrace();
        }
    }

    public void setTextField(CategoryCredit c) {
        this.donnees = c;
        tfName.setText(donnees.getName());
        tfDesc.setText(donnees.getDescription());

    }

    public void setUpdate(boolean b) {
        this.update = b;

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
