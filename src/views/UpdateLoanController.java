/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.CategoryCredit;
import models.Credit;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import services.CategoryCreditService;
import services.CreditService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UpdateLoanController implements Initializable {

    @FXML
    private Text lbcat;
    @FXML
    private Text lbMinAmount;
    @FXML
    private TextField tfCat;
    @FXML
    private Text lbLoanRat;
    @FXML
    private Text lbMonths;
    @FXML
    private Text lbWithdrawMonthly;
    @FXML
    private Text lbMaxAmount;
    @FXML
    private TextField tfName1;
    @FXML
    private TextField tfName2;
    @FXML
    private TextField tfMaxAmount;
    @FXML
    private TextField tfMinAmount;
    @FXML
    private TextField tfLoanRate;
    @FXML
    private TextField tfMonths;
    @FXML
    private TextField tfwithdraw;
    @FXML
    private Text Update;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnBack;
    int creditId;

    CreditService ps = new CreditService();
    private Credit donnees;
    private boolean update;
    @FXML
    private Button profilfx;
    @FXML
    private Button lbcat1;
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
    private void add(ActionEvent event) throws IOException,SQLException {
        int minAmount = Integer.parseInt(tfMinAmount.getText());
        int maxAmount = Integer.parseInt(tfMaxAmount.getText());
        int loanRate = Integer.parseInt(tfLoanRate.getText());
        int months = Integer.parseInt(tfMonths.getText());
        int withdrawMonthly = Integer.parseInt(tfwithdraw.getText());

        Credit c = new Credit();
        c.setCategory(donnees.getCategory());
        c.setId(donnees.getId());
        c.setMinAmount(minAmount);
        c.setMaxAmount(maxAmount);
        c.setMonths(months);
        c.setWithdrawMonthly(withdrawMonthly);
        c.setLoanRate(loanRate);
        try {

            ps.modifier(c, donnees.getId());
            Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("information Dialog");
            alert0.setHeaderText(null);
            alert0.setContentText("category modified successfully");
            //((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Loans.fxml"));
            Parent root = loader.load();
            btnUpdate.getScene().setRoot(root);
            alert0.showAndWait();

        } catch (SQLException ex) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Failed to modify category");
            alert1.showAndWait();
        }
    }

    public void setTextField(Credit c) {
        this.donnees = c;
        tfCat.setText(donnees.getCategory().getName()); // assuming that getMinAmount() returns a numeric value
        tfMinAmount.setText(String.valueOf(donnees.getMinAmount())); // assuming that getMinAmount() returns a numeric value
        tfMaxAmount.setText(String.valueOf(donnees.getMaxAmount())); // assuming that getMaxAmount() returns a numeric value
        tfLoanRate.setText(String.valueOf(donnees.getLoanRate()));
        tfMonths.setText(String.valueOf(donnees.getMonths()));
        tfwithdraw.setText(String.valueOf(donnees.getWithdrawMonthly()));
    }

    public void setUpdate(boolean b) {
        this.update = b;

    }

    @FXML
    private void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loans.fxml"));
            Parent root = loader.load();
            btnBack.getScene().setRoot(root);
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
