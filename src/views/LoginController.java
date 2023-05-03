/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import javafx.scene.control.Hyperlink;
import javax.swing.JOptionPane;
import services.UserService;
import utils.MyConnection;
import java.util.Arrays;

/**
 * FXML Controller class
 *
 * @author MEGA-PC
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private Button LoginButton;
    Connection con;
    PreparedStatement pst;
    private User user;
    @FXML
    private Hyperlink hyper;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

    public void setUser(User user) {
        this.user = user;
        usernameTF.setText(user.getEmail());
    }

    @FXML
    private void Login(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        String nom = usernameTF.getText();
        String password = passwordTF.getText();
        if (nom.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your email");
        } else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your password");
        } else {
            try {
                UserService userService = new UserService();
                User loggedInUser = userService.login(nom, password);
                if (loggedInUser != null) {
                    if (loggedInUser.getStatus().equals("disabled")) {
                    JOptionPane.showMessageDialog(null, "Your account has been disabled. Please contact the administrator for assistance.");
                } else {
                    System.out.println("Logged in user roles: " + Arrays.toString(loggedInUser.getRoles()));
                    if (loggedInUser.getRoles()[0].contains("ROLE_USER")) {
                        System.out.println("Loading user profile");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
                        Parent root = loader.load();
                        ProfileController controller = loader.getController();
                        controller.setUser(loggedInUser);
                        usernameTF.getScene().setRoot(root);
                    } else if (loggedInUser.getRoles()[0].contains("ROLE_ADMIN")) {
                        System.out.println("Loading admin interface");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminInterface.fxml"));
                        Parent root = loader.load();
                        AdminInterfaceController controller = loader.getController();
                        controller.setUser(loggedInUser);
                        usernameTF.getScene().setRoot(root);
                    }}
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                }

            } catch (SQLException ex) {
                System.out.println("Error occurred while logging in");
                JOptionPane.showMessageDialog(null, "An error occurred while logging in. Please try again later.");
                ex.printStackTrace();
            }
        }
    }

    private void signup(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterPersonne.fxml"));
            Parent root = loader.load();
            AjouterPersonneController controller = loader.getController();
            controller.setUsername(usernameTF.getText());
            usernameTF.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void gotoSignup(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
            Parent root = loader.load();
            usernameTF.getScene().setRoot(root);

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
