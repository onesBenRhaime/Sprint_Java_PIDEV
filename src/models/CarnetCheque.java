/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author 21650
 */
public class CarnetCheque {
     private int id, idtypecarnet_id;
    private String description, email, status, cin_s1, cin_s2, identifier,document;
 private TypeCheque TypeCheque;
    public CarnetCheque() {
    }

    public CarnetCheque(int id, int idtypecarnet_id, String description, String email, String status, String cin_s1, String cin_s2, String identifier,String document) {
        this.id = id;
        this.idtypecarnet_id = idtypecarnet_id;
        this.description = description;
        this.email = email;
        this.status = status;
        this.cin_s1 = cin_s1;
        this.cin_s2 = cin_s2;
        this.identifier = identifier;
    }

    public CarnetCheque(int idtypecarnet_id, String description, String email, String status, String cin_s1, String cin_s2, String identifier) {
        this.idtypecarnet_id = idtypecarnet_id;
        this.description = description;
        this.email = email;
        this.status = status;
        this.cin_s1 = cin_s1;
        this.cin_s2 = cin_s2;
        this.identifier = identifier;
    }

    public CarnetCheque(String description, String email, String status, String identifier) {
        this.description = description;
        this.email = email;
        this.status = status;
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "CarnetCheque{" + "id=" + id + ", idtypecarnet_id=" + idtypecarnet_id + ", description=" + description + ", email=" + email + ", status=" + status + ", cin_s1=" + cin_s1 + ", cin_s2=" + cin_s2 + ", identifier=" + identifier + ", document=" + document + '}';
    }

    public CarnetCheque(int id, String description, String email) {
        this.id = id;
        this.description = description;
        this.email = email;
    }

    public TypeCheque getTypeCheque() {
        return TypeCheque;
    }

    public void setTypeCheque(TypeCheque TypeCheque) {
        this.TypeCheque = TypeCheque;
    }

    public CarnetCheque(int idtypecarnet_id, String description, String email, TypeCheque TypeCheque) {
        this.idtypecarnet_id = idtypecarnet_id;
        this.description = description;
        this.email = email;
        this.TypeCheque = TypeCheque;
    }

    public CarnetCheque(String description, String email, String identifier, TypeCheque TypeCheque) {
        this.description = description;
        this.email = email;
        this.identifier = identifier;
        this.TypeCheque = TypeCheque;
    }

    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdtypecarnet_id() {
        return idtypecarnet_id;
    }

    public void setIdtypecarnet_id(int idtypecarnet_id) {
        this.idtypecarnet_id = idtypecarnet_id;
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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    
    
    
      
    
}
