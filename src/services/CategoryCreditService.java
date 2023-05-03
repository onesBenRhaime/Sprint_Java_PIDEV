/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.CategoryCredit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class CategoryCreditService {

    Connection cnx;

    private ObservableList<String> cat = FXCollections.observableArrayList();

    public CategoryCreditService() {
        cnx = MyConnection.getInstance().getCon();
    }

    public void ajouter(CategoryCredit t) throws SQLException {

        String req = "INSERT INTO category_credit (name, description) VALUES (?, ?)";
        PreparedStatement pstmt = cnx.prepareStatement(req);
        pstmt.setString(1, t.getName());
        pstmt.setString(2, t.getDescription());
        pstmt.executeUpdate();

    }

    public void modifier(CategoryCredit t, int id) throws SQLException {
        try {
            String req = "UPDATE category_credit SET name=?, description=? WHERE id=?";
            PreparedStatement pstmt = cnx.prepareStatement(req);

            pstmt.setString(1, t.getName());
            pstmt.setString(2, t.getDescription());
            pstmt.setInt(3, id);
            System.out.println("id !" + id);

            pstmt.executeUpdate();

            System.out.println("category  updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimer(CategoryCredit t, int id) throws SQLException {

        String req = "DELETE FROM category_credit WHERE id = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, t.getId()); // set the value of the id parameter
        st.executeUpdate();
    }

    public List<CategoryCredit> recuperer() throws SQLException {
        List<CategoryCredit> categories = new ArrayList<>();
        String req = "select * from category_credit";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            CategoryCredit p = new CategoryCredit();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));

            categories.add(p);
        }
        return categories;
    }

    public List<CategoryCredit> recupererById(int id) throws SQLException {
        List<CategoryCredit> categories = new ArrayList<>();
        String req = "select * from category_credit where id= ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id); // set the value of the id parameter
        ResultSet rs = st.executeQuery(); // execute the query
        while (rs.next()) {
            CategoryCredit p = new CategoryCredit();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));

            categories.add(p);
        }
        return categories;
    }

    public CategoryCredit getById(int id) throws SQLException {
        CategoryCredit category = null;
        String req = "select * from category_credit where id= ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id); // set the value of the id parameter
        ResultSet rs = st.executeQuery(); // execute the query
        if (rs.next()) {
            category = new CategoryCredit();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setDescription(rs.getString("description"));
        }
        return category;
    }

    public ObservableList<String> getNames() {
        return cat;
    }

    public int displayIdByName(String name) {
        String req = "select id from category_credit where name='" + name + "'";
        int options = 0;

        try {
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                options = rs.getInt("id");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return options;
    }

}
