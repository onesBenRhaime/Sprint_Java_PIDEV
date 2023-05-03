/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Personne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author Skand
 */
public class PersonneService implements IService<Personne> {

    Connection cnx;

    public PersonneService() {
        cnx = MyConnection.getInstance().getCon();
    }

    @Override
    public void ajouter(Personne t) throws SQLException {
        String req = "insert into personne(nom,prenom,age) values('" + t.getNom() + "','" + t.getPrenom() + "'," + t.getAge() + ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Personne t,int id) throws SQLException {
        String req = "update personne set nom = ?, prenom = ?, age = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom());
        ps.setString(2, t.getPrenom());
        ps.setInt(3, t.getAge());
        ps.setInt(4, t.getId());
        ps.executeUpdate();

    }

    @Override
    public void supprimer(Personne t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Personne> recuperer() throws SQLException {
        List<Personne> personnes = new ArrayList<>();
        String req = "select * from personne";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Personne p = new Personne();
            p.setId(rs.getInt("id"));
            p.setAge(rs.getInt("age"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            
            personnes.add(p);
        }
        return personnes;
    }
    
    
    public List<Personne> recupererById(int id) throws SQLException {
        List<Personne> personnes = new ArrayList<>();
        String req = "select * from personne where id= ?";
        PreparedStatement st = cnx.prepareStatement(req);
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Personne p = new Personne();
            p.setId(rs.getInt("id"));
            p.setAge(rs.getInt("age"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            
            personnes.add(p);
        }
        return personnes;
    }

     public boolean login(String nom, String password) throws SQLException {
        String req = "SELECT * FROM personne WHERE nom = ? AND password = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, nom);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
                    System.out.println("Login successful. Connexion établie.");

            // Login successful, return true
            return true;
        } else {
            // Login failed, return false
            return false;
        }
    }

}
