/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author MEGA-PC
 */
public class SignUpController implements Initializable {
    UserService us = new UserService();
    
    @FXML
    private TextField nomTF;

    @FXML
    private PasswordField passwordTF;
    
    @FXML
    private TextField emailTF;
    
    @FXML
    private ComboBox<String> AgenceBox;
    
    @FXML
    private TextField phoneTF;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            AgenceBox.getItems().addAll("Horizental Bank", "Odessa Bank", "Cazo Bank");
            AgenceBox.setValue("Horizental Bank");
    }    
      
@FXML
private void SignUp(ActionEvent event) throws SQLException, IOException {
    String phone = phoneTF.getText();
    String password = passwordTF.getText();
    String nom = nomTF.getText();
    String prenom = AgenceBox.getValue().toString();
    String email = emailTF.getText();
//    System.out.println(phone);
    if (nom.isEmpty() || !nom.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid name");
        alert.setContentText("Please enter a valid name");
        alert.showAndWait();
        return;
    }
    else if(phone.isEmpty()|| !phone.matches("\\d{8}")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid phone number");
        alert.setContentText("Please enter a valid phone number");
        alert.showAndWait();
        return;
    }
    else if (email.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid email format");
        alert.setContentText("Email is required");
        alert.showAndWait();
        return;
    }
     else if (!email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid email format");
        alert.setContentText("Please enter a valid email address");
        alert.showAndWait();
        return;
    }
     else if(password.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid password");
        alert.setContentText("Please enter your password");
        alert.showAndWait();
        return;
    }
    else {
    User user = new User(email, password, nom, phone, "enabled", null, new String[] {"ROLE_USER"});
     us.ajouter(user);

    
    

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Login.fxml"));
    Parent parent = loader.load();
    LoginController controller = loader.getController();
    controller.setUser(user);
    phoneTF.getScene().setRoot(parent);}
}


    
    
       @FXML
    private void gotoLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            LoginController controller = loader.getController();
//            controller.setUsername(usernameTF.getText());
            phoneTF.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    



}

 
    

