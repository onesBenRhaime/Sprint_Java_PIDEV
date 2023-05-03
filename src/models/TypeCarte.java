/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author 21650
 */
public class TypeCarte {
    
        private int id;
        private String nom,description;

    public TypeCarte() {
    }

    public TypeCarte(int id) {
        this.id = id;
    }
    

    public TypeCarte(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }
    

    public TypeCarte(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    @Override
//    public String toString() {
//        return "TypeCarte{" + "id=" + id + ", nom=" + nom + ", description=" + description + '}';
//    }
        @Override
    public String toString() {
        return nom ;
    }


    
}
