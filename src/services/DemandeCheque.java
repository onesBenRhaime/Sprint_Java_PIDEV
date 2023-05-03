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
public class DemandeCheque implements IServiceS<CarnetCheque> {

    Connection cnx = MyConnection.getInstance().getCon();

    @Override
    public void ajouter(CarnetCheque t) throws SQLException {
        try {
            String req = "INSERT INTO carnet_cheque(email,description,identifier,idtypecarnet_id,cin_s1,cin_s2,document,status) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);

            //ps.setInt(1,c.getId_categorie());
            ps.setString(1, t.getEmail());
            ps.setString(2, t.getDescription());
            ps.setString(3, t.getIdentifier());
            String status = t.getStatus() != null ? t.getIdentifier() : "Valeur par défaut";
            ps.setString(4, status);
             String cin_s1 = t.getCin_s1()!= null ? t.getCin_s1(): "Valeur par défaut";
            ps.setString(5, cin_s1);
             String cin_s2 = t.getCin_s2()!= null ? t.getCin_s2(): "Valeur par défaut";
            ps.setString(6, cin_s2);
            String document = t.getDocument()!= null ? t.getDocument(): "Valeur par défaut";
            ps.setString(7, document);
            Integer idtypecarnet_id = t.getIdtypecarnet_id()!= 0 ? t.getIdtypecarnet_id(): 5;
            ps.setInt(8, idtypecarnet_id);
            //ps.setInt(7, t.getIdtypecarnet_id());
            ps.executeUpdate();
            System.out.println("type cheque ajoutee avec succes");
        } catch (SQLException ex) {
            System.out.println("type cheque  non ajoutee !!");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(CarnetCheque t, int id) throws SQLException {

    }

    @Override
    public void modifier2(CarnetCheque p) throws SQLException {

    }

    @Override
    public void supprimer(CarnetCheque t) throws SQLException {
        try {
            String req = "DELETE FROM  carnet_cheque WHERE id = " + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(" demande carnet deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<CarnetCheque> recuperer() throws SQLException {
        List<CarnetCheque> list = new ArrayList<>();
        try {
            String req = "Select * from  carnet_cheque";
            Statement st = cnx.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                CarnetCheque t = new CarnetCheque();
                t.setId(RS.getInt("id"));
                t.setIdtypecarnet_id(RS.getInt(2));
                t.setEmail(RS.getString(3));
                t.setIdentifier(RS.getString(4));
                t.setDescription(RS.getString(5));
                t.setCin_s1(RS.getString(6));
                t.setCin_s2(RS.getString(7));
                t.setDocument(RS.getString(8));
                t.setStatus(RS.getString(9));

                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public List<CarnetCheque> afficher() throws SQLException {
        List<CarnetCheque> list = new ArrayList<>();
        try {
            String req = "Select * from  carnet_cheque";
            Statement st = cnx.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                CarnetCheque t = new CarnetCheque();
                t.setId(RS.getInt("id"));
                t.setIdtypecarnet_id(RS.getInt(2));
                t.setEmail(RS.getString(3));
                t.setIdentifier(RS.getString(4));
                t.setDescription(RS.getString(5));
                t.setStatus(RS.getString(9));

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

}
