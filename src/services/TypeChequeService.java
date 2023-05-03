/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.CarnetCheque;
import models.CarteBancaire;
import models.TypeCarte;
import models.TypeCheque;
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
public class TypeChequeService implements IServiceS<TypeCheque> {

    Connection cnx = MyConnection.getInstance().getCon();

    @Override
    public void ajouter(TypeCheque t) throws SQLException {
        try {
            String req = "INSERT INTO type_carnet(nom,description,startnum,endnum,datecreation,datevalidation) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);

            //ps.setInt(1,c.getId_categorie());
            ps.setString(1, t.getNom());
            ps.setString(2, t.getDescription());
            ps.setInt(3, t.getStartnum());
            ps.setInt(4, t.getEndnum());
            ps.setDate(5, t.getDatecreation());
            ps.setDate(6, t.getDatevalidation());
            ps.executeUpdate();
            System.out.println("type cheque ajoutee avec succes");
        } catch (SQLException ex) {
            System.out.println("type cheque  non ajoutee !!");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(TypeCheque t, int id) throws SQLException {
        try {
            String query = "UPDATE type_carnet SET nom=?, description=?, startnum=?, endnum=?, datecreation=?, datevalidation=? WHERE id=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, t.getNom());
            stmt.setString(2, t.getDescription());
            stmt.setInt(3, t.getStartnum());
            stmt.setInt(4, t.getEndnum());
            stmt.setDate(5, t.getDatecreation());
            stmt.setDate(6, t.getDatevalidation());
            stmt.setInt(7, id);
            
            stmt.executeUpdate();

            System.out.println("type carnet  updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier2(TypeCheque t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(TypeCheque t) throws SQLException {
        try {
            String req = "DELETE FROM  type_carnet WHERE id = " + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(" type carnet deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<TypeCheque> recuperer() throws SQLException {
        List<TypeCheque> list = new ArrayList<>();
        try {
            String req = "Select * from  type_carnet";
            Statement st = cnx.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                TypeCheque t = new TypeCheque();
                t.setId(RS.getInt("id"));
                t.setNom(RS.getString(2));
                t.setDescription(RS.getString(3));
                t.setStartnum(RS.getInt("startnum"));
                t.setEndnum(RS.getInt("endnum"));
                t.setDatecreation(RS.getDate("datecreation"));
                t.setDatevalidation(RS.getDate("datevalidation"));
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
    
     public List<TypeCheque> top5prod() {
        List<TypeCheque> prod = new ArrayList<>();

        try {

            // Get the current month
            java.util.Date now = new java.util.Date();
            java.sql.Date currentMonth = new java.sql.Date(now.getTime());

            // Execute the SQL query
            String sql = "SELECT id, COUNT(*) as count FROM type_carnet WHERE MONTH(datecreation) = MONTH(?) AND YEAR(datecreation) = YEAR(?) GROUP BY id ORDER BY count DESC LIMIT 5";
            PreparedStatement stmt = cnx.prepareStatement(sql);
            stmt.setDate(1, currentMonth);
            stmt.setDate(2, currentMonth);
            ResultSet rs = stmt.executeQuery();

            // Print the results
            while (rs.next()) {
                TypeCheque p = new TypeCheque();
                p.setId(rs.getInt("id"));
                p.setStartnum(rs.getInt("count"));
                prod.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prod;
    }

}
