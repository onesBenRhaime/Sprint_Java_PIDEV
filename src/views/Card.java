/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import models.TypeCompte;

import javafx.scene.layout.VBox;

/**
 *
 * @author BAZINFO
 */
public class Card extends VBox{
   public Card(TypeCompte data) {
        Label titleLabel = new Label(data.getType());
        Label descriptionLabel = new Label(data.getDescription());


        setSpacing(10);
        setPadding(new Insets(10));
        setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000;");
    }
    
    
}
