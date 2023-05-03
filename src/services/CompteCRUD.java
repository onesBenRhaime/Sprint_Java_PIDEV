                /*
                * To change this license header, choose License Headers in Project Properties.
                * To change this template file, choose Tools | Templates
                * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.Compte;
import utils.MyConnection;

/**
 *
 * @author BAZINFO
 */
public class CompteCRUD implements IService<Compte> {

    Connection cnx = MyConnection.getInstance().getCon();

    @Override
    public void ajouter(Compte t) throws SQLException {
        try {
            String req = "INSERT INTO compte(date_creation,date_fermeture,solde,id_user_id,id_type_id,cin_s1,cin_s2,other_doc,max_solde,min_solde,red_solde,rib,statue) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            //ps.setInt(1,c.getId_categorie());
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.setString(3, "in progress");
            ps.setInt(4, t.getIdUserId());
            ps.setInt(5, t.getIdTypeId());
            ps.setString(6, t.getCinS1());
            ps.setString(7, t.getCinS2());
            ps.setString(8, t.getOtherDoc());
            ps.setString(9, t.getMaxSolde());
            ps.setString(10, t.getMinSolde());
            ps.setString(11, t.getRedSolde());
            ps.setString(12, "in progress");
            ps.setString(13, "in progress");
            ps.executeUpdate();

            System.out.println("Compte  ajoutee avec succes");

        } catch (SQLException ex) {
            System.out.println("Compte  non ajoutee !!");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Compte t, int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Compte t) throws SQLException {
       try {
           System.out.println("supppp : "+ t);
            String req = "DELETE FROM  compte WHERE id = " + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(" compte deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Compte> recuperer() throws SQLException {
        List<Compte> list = new ArrayList<>();
        try {
            String req = "Select * from  compte";
            Statement st = cnx.createStatement();
            ResultSet RS = st.executeQuery(req);
            System.out.println(RS);
            while (RS.next()) {
                System.out.println(RS.getInt(1));
                Compte t = new Compte();
                t.setId(RS.getInt("id"));
                t.setDateCreation(RS.getDate("date_creation"));
                t.setDateFermeture(RS.getDate("date_fermeture"));
                t.setSolde( RS.getString("solde"));
                t.setIdUserId(RS.getInt("id_user_id"));
                t.setIdTypeId(RS.getInt("id_type_id"));
                t.setCinS1(RS.getString("cin_s1"));                
                t.setCinS2(RS.getString("cin_s2"));
                t.setOtherDoc(RS.getString("other_doc")); 
                t.setMaxSolde(RS.getString("max_solde"));
                t.setMinSolde(RS.getString("min_solde"));
                t.setRedSolde(RS.getString("red_solde"));
                t.setRib(RS.getString("rib"));
                t.setStaute(RS.getString("statue"));
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    
    public void accept(Compte t) throws SQLException {
         try {
            String query = "UPDATE compte SET statue=? WHERE id=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, "valide");           
            stmt.setInt(2, t.getId());
            stmt.executeUpdate();

            System.out.println(" demande compte  Accepted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    
    
    public void reject(Compte t) throws SQLException {
         try {
            String query = "UPDATE compte SET statue=? WHERE id=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, "rejected");           
            stmt.setInt(2, t.getId());
            stmt.executeUpdate();

            System.out.println(" demande compte Rejected !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    

}
