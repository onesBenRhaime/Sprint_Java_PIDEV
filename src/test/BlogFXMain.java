/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author don7a
 */
public class BlogFXMain extends Application {

     @Override
    public void start(Stage primaryStage) throws Exception {
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/AddAgence.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}




