/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author 21650
 */
public class TypeCheque {

    private int id, startnum, endnum;
    private String nom, description;
    private Date datecreation, datevalidation;

    public TypeCheque() {
    }

    public TypeCheque(int id, int startnum, int endnum, String nom, String description, Date datecreation, Date datevalidation) {
        this.id = id;
        this.startnum = startnum;
        this.endnum = endnum;
        this.nom = nom;
        this.description = description;
        this.datecreation = datecreation;
        this.datevalidation = datevalidation;
    }

    public TypeCheque(int startnum, int endnum, String nom, String description, Date datecreation, Date datevalidation) {
        this.startnum = startnum;
        this.endnum = endnum;
        this.nom = nom;
        this.description = description;
        this.datecreation = datecreation;
        this.datevalidation = datevalidation;
    }

    public TypeCheque(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartnum() {
        return startnum;
    }

    public void setStartnum(int startnum) {
        this.startnum = startnum;
    }

    public int getEndnum() {
        return endnum;
    }

    public void setEndnum(int endnum) {
        this.endnum = endnum;
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

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Date getDatevalidation() {
        return datevalidation;
    }

    public void setDatevalidation(Date datevalidation) {
        this.datevalidation = datevalidation;
    }

    @Override
    public String toString() {
        return "TypeCheque{" + "id=" + id + ", startnum=" + startnum + ", endnum=" + endnum + ", nom=" + nom + ", description=" + description + ", datecreation=" + datecreation + ", datevalidation=" + datevalidation + '}';
    }
    
    
    
    

}
