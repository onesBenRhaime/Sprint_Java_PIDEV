/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author 21650
 */
public class CarteBancaire {

    private int id,idtypecarte_id;
    private String description, email, status, cin_s1, cin_s2, identifier;
      List<CarteBancaire> list;

    public CarteBancaire(int id, int idtypecarte_id, String description, String email, String status, String cin_s1, String cin_s2, String identifier) {
        this.id = id;
        this.idtypecarte_id = idtypecarte_id;
        this.description = description;
        this.email = email;
        this.status = status;
        this.cin_s1 = cin_s1;
        this.cin_s2 = cin_s2;
        this.identifier = identifier;
    }

    public CarteBancaire() {
    }

    public CarteBancaire(int idtypecarte_id, String description, String email, String status, String cin_s1, String cin_s2, String identifier) {
        this.idtypecarte_id = idtypecarte_id;
        this.description = description;
        this.email = email;
        this.status = status;
        this.cin_s1 = cin_s1;
        this.cin_s2 = cin_s2;
        this.identifier = identifier;
    }

    public CarteBancaire(String description, String email, String status, String cin_s1, String cin_s2, String identifier) {
        this.description = description;
        this.email = email;
        this.status = status;
        this.cin_s1 = cin_s1;
        this.cin_s2 = cin_s2;
        this.identifier = identifier;
    }
    
       public CarteBancaire( String email, String identifier, String description, String cin_s1, String cin_s2,int idtype) {
        this.description = description;
        this.email = email;
        this.idtypecarte_id = idtype;
        this.cin_s1 = cin_s1;
        this.cin_s2 = cin_s2;
        this.identifier = identifier;
    }
             public CarteBancaire(int id, String email, String identifier, String description, String cin_s1, String cin_s2,int idtype) {
                  this.id = id;
        this.description = description;
        this.email = email;
        this.idtypecarte_id = idtype;
        this.cin_s1 = cin_s1;
        this.cin_s2 = cin_s2;
        this.identifier = identifier;
    }

   
        public List<CarteBancaire> getList() {
        return list;
    }
        

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdtypecarte_id() {
        return idtypecarte_id;
    }

    public void setIdtypecarte_id(int idtypecarte_id) {
        this.idtypecarte_id = idtypecarte_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCin_s1() {
        return cin_s1;
    }

    public void setCin_s1(String cin_s1) {
        this.cin_s1 = cin_s1;
    }

    public String getCin_s2() {
        return cin_s2;
    }

    public void setCin_s2(String cin_s2) {
        this.cin_s2 = cin_s2;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "CarteBancaire{" + "id=" + id + ", idtypecarte_id=" + idtypecarte_id + ", description=" + description + ", email=" + email + ", status=" + status + ", cin_s1=" + cin_s1 + ", cin_s2=" + cin_s2 + ", identifier=" + identifier + '}';
    }
  
   

}
