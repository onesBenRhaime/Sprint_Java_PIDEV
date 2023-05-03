/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Category;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author ASUS
 */
public class CategoryService implements IServiceBlog<Category>{
    private final String url = "jdbc:mysql://localhost:3306/appbank";
    private final String user = "root";
    private final String password = "";
    
    Connection cnx;

    public CategoryService(int id, String name, String description) {
        cnx = MyConnection.getInstance().getCon();
    }

    public CategoryService() {
       cnx = MyConnection.getInstance().getCon();
    }
    
    @Override
    public void add(Category t) throws SQLException {
       
        String req = "insert into category(name,description) values('" + t.getName() + "','" + t.getDescription()  + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }
    @Override
    public void update(Category t) throws SQLException {
        String req = "update category set name = ?, description = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getName());
        ps.setString(2, t.getDescription());
        ps.setInt(3, t.getId());
        ps.executeUpdate();

    }
    public void deleteCategory(int id) throws SQLException {
    String query = "DELETE FROM category WHERE id = ?";
    PreparedStatement stmt = cnx.prepareStatement(query);
    stmt.setInt(1, id);
    stmt.executeUpdate();
}
    @Override
    public List<Category> getAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String req = "select * from category";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Category p = new Category();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));
            
            categories.add(p);
        }
        return categories;
    }

    
        public List<Category> getById(int id) throws SQLException {
        List<Category> categories = new ArrayList<>();
        String req = "select * from category where id= ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            Category p = new Category();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));
            
            categories.add(p);
        }
        return categories;
    }

}
