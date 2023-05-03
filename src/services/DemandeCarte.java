/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.CarnetCheque;
import models.CarteBancaire;
import models.TypeCarte;
//import entities.cartebanc;
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
 * @author 21650
 */
public class DemandeCarte implements IServiceS<CarteBancaire> {

    Connection cnx = MyConnection.getInstance().getCon();

    @Override
    public void ajouter(CarteBancaire t) throws SQLException {
        try {
            String req = "INSERT INTO carte_bancaire(`email`, `identifier`, `description`, `cin_s1`, `cin_s2`,`status`, `idtypecarte_id`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getEmail());
            ps.setString(2, t.getIdentifier());
            ps.setString(3, t.getDescription());
            ps.setString(4, t.getCin_s1());
            ps.setString(5, t.getCin_s2());
            ps.setString(6, "Encours");
            ps.setInt(7, t.getIdtypecarte_id());

            ps.executeUpdate();
            System.out.println("Carte ajoutee avec succes ");
        } catch (SQLException ex) {
            System.out.println("Cartes non ajoutee !!");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(CarteBancaire t, int id) throws SQLException {
        try {
            String query = "UPDATE carte_bancaire SET idtypecarte_id=?, email=?, identifier=?, description=? , cin_s1=?, cin_s2=?, status=? WHERE id=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setInt(2, t.getIdtypecarte_id());
            stmt.setString(3, t.getEmail());
            stmt.setString(4, t.getIdentifier());
            stmt.setString(5, t.getDescription());
            stmt.setString(6, t.getStatus());
            stmt.setString(7, t.getCin_s1());
            stmt.setString(8, t.getCin_s2());
            
            stmt.executeUpdate();

            System.out.println("type carte  updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void modifier2(CarteBancaire p) throws SQLException {
        try {
            String req = "UPDATE `carte_bancaire` SET `email` = '" + p.getEmail()
                    + "', `description` = '" + p.getDescription() + "', `cin_s1` = '" + p.getCin_s1() + "', `cin_s2` = '" + p.getCin_s2()
                    + "', `identifier` = '" + p.getIdentifier() + "', `status` = '" + p.getStatus()
                    + "', `idtypecarte_id` = '" + p.getIdtypecarte_id()
                    + "' WHERE `carte_bancaire`.`id` = '" + p.getId() + "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("carte updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(CarteBancaire t) throws SQLException {
        try {
            String req = "DELETE FROM  carte_bancaire WHERE id = " + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(" demande carte deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public List<CarteBancaire> recuperer() throws SQLException {
        List<CarteBancaire> list = new ArrayList<>();
        try {
            String req = "Select * from  carte_bancaire";
            Statement st = cnx.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                CarteBancaire t = new CarteBancaire();
                t.setId(RS.getInt("id"));
                t.setIdtypecarte_id(RS.getInt(2));
                t.setEmail(RS.getString(3));
                t.setIdentifier(RS.getString(4));
                t.setDescription(RS.getString(5));
                t.setCin_s1(RS.getString(6));
                t.setCin_s2(RS.getString(7));
                t.setStatus(RS.getString(8));

              
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public TypeCarte getByIdBonplan(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TypeCarte getCategorieById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CarteBancaire getByRefProduit(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CarnetCheque> afficher() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  public void accepter(CarteBancaire t) throws SQLException {
        try {
            String query = "UPDATE carte_bancaire SET status=? WHERE id=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, "Accepter");
            stmt.setInt(2, t.getId());
            stmt.executeUpdate();

            System.out.println("Demande  Accepted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void refuser(CarteBancaire t) throws SQLException {
        try {
            String query = "UPDATE carte_bancaire SET status=? WHERE id=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, "Refuser");
            stmt.setInt(2, t.getId());
            stmt.executeUpdate();

            System.out.println("Demande Rejected !");
                 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public  ResultSet typecarte(){
      try {
            String req = "SELECT type_carte.nom, COUNT(carte_bancaire.id) as cartebancaire_count FROM type_carte JOIN carte_bancaire ON type_carte.id = carte_bancaire.idtypecarte_id GROUP BY type_carte.nom ";
            Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(req);
            return RS ;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    
    
    return null;
    
}
   public int nbdemande() {
        int pend = 0;
        try {
            String req = "SELECT COUNT(*) as order_count FROM carte_bancaire WHERE status = 'Encours'";
            Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                pend = RS.getInt("order_count");
                return pend;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return pend;
    }
 
    public float totalsales() {
        float total = 0;
        try {
            String req = "SELECT SUM(id) as total FROM carte_bancaire";
            Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                total = RS.getFloat("id");
                return total;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return total;

    }
    
    

 
}
