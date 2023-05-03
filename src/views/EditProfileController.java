/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Mega-PC
 */
public class EditProfileController implements Initializable {
 private User user;
    @FXML
    private TextField emailID1;
    @FXML
    private TextField nameID1;
    @FXML
    private TextField phoneID1;
 public void setUser(User user) {
        this.user = user;
        System.out.println(user);
//        System.out.println("logged in");
        emailID1.setText(user.getEmail());
//          System.out.println(user.getEmail());
        nameID1.setText(user.getName());
        
        phoneID1.setText(user.getPhone());
//        System.out.println(user.getPhone());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updateUser(ActionEvent event) {
        if (emailID1.getText().isEmpty() || nameID1.getText().isEmpty() || phoneID1.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please fill in all required fields.");
        alert.showAndWait();
    } else if (!isValidEmail(emailID1.getText()) || !isValidName(nameID1.getText()) || !isValidPhone(phoneID1.getText())) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter valid information.");
        alert.showAndWait();
    } else {
        try {
            UserService userService = new UserService();
            String newEmail = emailID1.getText();
            String newName = nameID1.getText();
            String newPhone = phoneID1.getText();

            user.setEmail(newEmail);
            user.setName(newName);
            user.setPhone(newPhone);

            userService.modifier(user,1);
            System.out.println("User " + user.getEmail() + " updated in the database.");

            // Load the Profile.fxml view with the updated user credentials
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
            Parent root = loader.load();
            ProfileController profileController = loader.getController();
            profileController.setUser(user);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    }
    
    private boolean isValidPhone(String username) {
    // Check if the username contains only alphanumeric characters and has length between 3 and 20
    return username.matches("\\d{8}");
}

private boolean isValidName(String name) {
    // Check if the name contains only letters and has length between 2 and 50
    return name.matches("^[a-zA-Z ]{2,50}$");
}

private boolean isValidEmail(String email) {
    // Check if the email matches the email format
    return email.matches("^\\S+@\\S+\\.\\S+$");
}
    
}
