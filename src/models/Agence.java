/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ASUS
 */
public class Agence {
    int id;
    String name,description;

    public Agence() {
    }

    public Agence(int id, String name, String description) {
        this.id = id;
        
    }

    public Agence(String name, String description) {
       this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Agence{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }
    
}
