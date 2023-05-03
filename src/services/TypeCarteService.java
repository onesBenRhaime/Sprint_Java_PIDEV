/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.CarnetCheque;
import models.CarteBancaire;
import models.TypeCarte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import utils.MyConnection;

/**
 *
 * @author 21650
 */
public class TypeCarteService implements IServiceS <TypeCarte>{
    Connection cnx = MyConnection.getInstance().getCon();

@Override
    public void ajouter(TypeCarte t) throws SQLException {
//        String req = "insert into type_compte(type,description) values('" + t.getType() + "','" + t.getDescription()+ ")";//        
//        Statement st = cnx.createStatement();
//        st.executeUpdate(req);
         try {
            String req = "INSERT INTO Type_Carte(nom,description) VALUES (?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);

            //ps.setInt(1,c.getId_categorie());
            ps.setString(1, t.getNom());
            ps.setString(2, t.getDescription());
            ps.executeUpdate();
            System.out.println("type carte ajoutee avec succes");
        } catch (SQLException ex) {
            System.out.println("type carte  non ajoutee !!");
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public TypeCarte getCategorieById(int id) throws SQLException {
        TypeCarte c = null;
        try {
            String req = "SELECT * FROM Type_Carte WHERE id = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                c = new TypeCarte();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setDescription(rs.getString("description"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }

@Override
    public void modifier2(TypeCarte t) throws SQLException {
       try {
            String req = "UPDATE `TypeCarte` SET `id` = '" + t.getId()
                    + "', `nom` = '" + t.getNom()
                    + "', `description` = '" + t.getDescription()
                   
                    + "' WHERE `TypeCarte`.`id` = " + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Type updated !");
        } catch (SQLException ex) {
            System.out.println("Type non updated !");
        }
    }
    public int nbtypes() {
        int pend = 0;
        try {
            String req = "SELECT COUNT(*) as order_count FROM Type_Carte ";
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
     public int nbtypebynom() {
        int pend = 0;
        try {
            String req = "SELECT COUNT(*) as order_count FROM Type_Carte WHERE nom = 'VisaCard'";
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
    
    @Override
    public void modifier(TypeCarte t,int id) throws SQLException {
        try {
            String query = "UPDATE Type_Carte SET nom=?, description=? WHERE id=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, t.getNom());
            stmt.setString(2, t.getDescription());
            stmt.setInt(3, id);
            stmt.executeUpdate();

            System.out.println("type carte  updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(TypeCarte t) throws SQLException {
        try {
            String req = "DELETE FROM  Type_Carte WHERE id = " + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(" type carte deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public boolean Existe(String type) {
        String sql = "SELECT * FROM Type_Carte WHERE nom = ?";
        try {
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setString(1, type);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<TypeCarte> recuperer() throws SQLException {
    List<TypeCarte> list = new ArrayList<>();
        try {
            String req = "Select * from  Type_Carte";
            Statement st = cnx.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                TypeCarte t = new TypeCarte();
                t.setId(RS.getInt("id"));
                t.setNom(RS.getString(2));
                t.setDescription(RS.getString(3));
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;

    }

   @Override
    public TypeCarte getByIdBonplan(int id) {
        TypeCarte Bpp = new TypeCarte();
        try {
            String req = "SELECT * FROM `TypeCarte` WHERE `id` = '" + id + "'";
            Statement st = cnx.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {

                Bpp.setId(RS.getInt("id"));
                Bpp.setNom(RS.getString("nom"));
                Bpp.setDescription(RS.getString("description"));
               

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Bpp;
    }

    @Override
    public CarteBancaire getByRefProduit(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CarnetCheque> afficher() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
