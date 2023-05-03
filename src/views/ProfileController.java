/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import services.UserService;
import java.sql.SQLException;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.Optional;

import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author MEGA-PC
 */
public class ProfileController implements Initializable {

    @FXML
    private Label usernameLB;
    @FXML
    private Label nomLB;
    @FXML
    private Label phoneLB;

    private User user;
    @FXML
    private Label nomLB1;
    private User currentUser;
    @FXML
    private Button deleteButton;
    @FXML
    private Button EditProfile;

    public void setUser(User user) {
        this.user = user;
        System.out.println(user);
//        System.out.println("logged in");
        usernameLB.setText(user.getEmail());
//          System.out.println(user.getEmail());
        nomLB.setText(user.getName());
        nomLB1.setText(user.getName());
        phoneLB.setText(user.getPhone());
//        System.out.println(user.getPhone());
        this.currentUser = user;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void edit(ActionEvent event) {
        
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProfile.fxml"));
        Parent root = loader.load();
        EditProfileController controller = loader.getController();
        controller.setUser(user);
        usernameLB.getScene().setRoot(root);
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    }

    @FXML
    private void Logout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            usernameLB.getScene().setRoot(root);

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    void handleDeleteButtonAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Account");
        alert.setHeaderText("Are you sure you want to delete your account?");
        alert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean deleted = currentUser.deleteAccount(currentUser);
            if (deleted) {
                try {
                    // close the current window
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    // open the login screen
                    Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                    Stage loginStage = new Stage();
                    loginStage.setScene(new Scene(root));
                    loginStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // deletion failed, display an error message
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Deletion Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Unable to delete account.");
                errorAlert.showAndWait();
            }
        }
    }

}
