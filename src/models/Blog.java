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
public class Blog {
    int id,categoryId;
    String name,description,details;
    Category category;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Blog() {
    }

    public Blog( String name, String description, String details) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.details = details;
        this.category = category;
    }
    public Blog(int id, String name, String description, String details,Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.details = details;
        this.category = category;
    }
    
    public Blog( String name, String description, String details,int categoryId) {
        
        this.name = name;
        this.description = description;
        this.details = details;
        this.categoryId = categoryId;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", name=" + name + ", description=" + description + ", details=" + details + '}';
    }
    
}
