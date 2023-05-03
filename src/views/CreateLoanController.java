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
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import services.CategoryCreditService;
import services.CreditService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CreateLoanController implements Initializable {

    @FXML
    private Text lbcat;
    @FXML
    private Text lbMinAmount;
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
    private Text Create;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnBack;
    @FXML
    private ComboBox<String> comCat;

    private CategoryCreditService ps = new CategoryCreditService();
    private List<CategoryCredit> categories = new ArrayList<>();
    private ObservableList<String> observableCategories;
    CreditService creditService = new CreditService();
    @FXML
    private Button profilfx;
    @FXML
    private Button lbcat1;
    @FXML
    private Button lboffer;
    @FXML
    private Button lbdem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            categories = ps.recuperer();
        } catch (SQLException ex) {
            Logger.getLogger(CreateLoanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Create an observable list of category names to be displayed in the combo box
        observableCategories = FXCollections.observableArrayList();
        for (CategoryCredit c : categories) {
            observableCategories.add(c.getName());
        }
        // Set the combo box items to the observable list of category names
        comCat.setItems(observableCategories);
        // Set a default value for the combo box
        comCat.setValue("Select a category");
        // Set tooltips for the combo box items that display the full category details
        for (CategoryCredit c : categories) {
            String tooltipText = "name: " + c.getName() + "\n"
                    + "description: " + c.getDescription() + "\n";
            Tooltip tooltip = new Tooltip(tooltipText);
            comCat.setTooltip(tooltip);
        }
    }

    private boolean isValidNumber(String text) {
        // Check if the text is empty or null
        if (text == null || text.isEmpty()) {
            return false;
        }
        // Check if the text contains only digits
        return text.matches("\\d+");
    }

    @FXML
    private void add(ActionEvent event) {
        try {
            // Get the selected category from the combo box
            String categoryName = comCat.getValue();
            CategoryCredit selectedCategory = null;

            if (categoryName != null) {
                for (CategoryCredit c : categories) {
                    if (c.getName().equals(categoryName)) {
                        selectedCategory = c;
                        break;
                    }
                }
            }

            int minAmount = 0;
            if (isValidNumber(tfMinAmount.getText())) {
                minAmount = Integer.parseInt(tfMinAmount.getText());
            } else {
                throw new NumberFormatException();
            }
            int maxAmount = 0;
            if (isValidNumber(tfMaxAmount.getText())) {
                maxAmount = Integer.parseInt(tfMaxAmount.getText());
            } else {
                throw new NumberFormatException();
            }
            int loanRate = 0;
            if (isValidNumber(tfLoanRate.getText())) {
                loanRate = Integer.parseInt(tfLoanRate.getText());
            } else {
                throw new NumberFormatException();
            }
            int months = 0;
            if (isValidNumber(tfMonths.getText())) {
                months = Integer.parseInt(tfMonths.getText());
            } else {
                throw new NumberFormatException();
            }
            int withdrawMonthly = 0;
            if (isValidNumber(tfwithdraw.getText())) {
                withdrawMonthly = Integer.parseInt(tfwithdraw.getText());
            } else {
                throw new NumberFormatException();
            }

            // Create a new credit object with the details and the selected category
            Credit newCredit = new Credit(selectedCategory, minAmount, maxAmount, withdrawMonthly, months, loanRate);
            System.out.println(newCredit);
            // Call the credit service to insert the new credit into the database
            creditService.ajouter(newCredit);
            // Show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Credit added successfully.");
            alert.showAndWait();
        } catch (NumberFormatException ex) {
            // Show an error message if any of the fields cannot be parsed as a number
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid number in all numeric fields.");
            alert.showAndWait();
        } catch (SQLException ex) {
            // Show an error message if an SQL exception occurs
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while adding the credit. Please try again.");
            alert.showAndWait();
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Loans.fxml"));
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
