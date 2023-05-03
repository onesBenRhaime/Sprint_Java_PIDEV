/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Agence;
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
 * @author [YOUR NAME]
 */
public class AgenceService implements IServiceBlog<Agence>{
    private final String url = "jdbc:mysql://localhost:3306/appbank";
    private final String user = "root";
    private final String password = "";
    
    Connection cnx;

    public AgenceService(int id, String name, String description) {
        cnx = MyConnection.getInstance().getCon();
    }

    public AgenceService() {
       cnx = MyConnection.getInstance().getCon();
    }
    
    @Override
    public void add(Agence t) throws SQLException {
       
        String req = "insert into agence(name,description) values('" + t.getName() + "','" + t.getDescription()  + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }
    
    @Override
    public void update(Agence t) throws SQLException {
        String req = "update agence set name = ?, description = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getName());
        ps.setString(2, t.getDescription());
        ps.setInt(3, t.getId());
        ps.executeUpdate();

    }
    
    public void deleteAgence(int id) throws SQLException {
        String query = "DELETE FROM agence WHERE id = ?";
        PreparedStatement stmt = cnx.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
    
    @Override
    public List<Agence> getAll() throws SQLException {
        List<Agence> agences = new ArrayList<>();
        String req = "select * from agence";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Agence a = new Agence();
            a.setId(rs.getInt("id"));
            a.setName(rs.getString("name"));
            a.setDescription(rs.getString("description"));
            
            agences.add(a);
        }
        return agences;
    }

    public List<Agence> getById(int id) throws SQLException {
        List<Agence> agences = new ArrayList<>();
        String req = "select * from agence where id= ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            Agence a = new Agence();
            a.setId(rs.getInt("id"));
            a.setName(rs.getString("name"));
            a.setDescription(rs.getString("description"));
            
            agences.add(a);
        }
        return agences;
    }
}
