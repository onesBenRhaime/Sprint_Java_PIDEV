/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.DemandeCredit;
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
 * @author ASUS
 */
public class DemandeCreditService {
    Connection cnx;

    public DemandeCreditService() {
        cnx = MyConnection.getInstance().getCon();
    }
    
    public void ajouter(DemandeCredit t) throws SQLException {
    String req = "INSERT INTO demande_credit (credit_id_id, user_id_id , amount, created_at, note, status,cin1,cin2) VALUES (?, ?, ?, ?, ?, ?,?,?)";
        PreparedStatement pstmt = cnx.prepareStatement(req);
        pstmt.setInt(1, t.getCredit().getId());
        pstmt.setInt(2, t.getUser().getId());
        pstmt.setInt(3, t.getAmount());
        pstmt.setDate(4, t.getCreatedAt());
        pstmt.setString(5, t.getNote());
        pstmt.setString(6, t.getStatus());
        pstmt.setString(7, t.getCin1());
        pstmt.setString(8, t.getCin2());
        System.out.println(t);
        pstmt.executeUpdate();
    }

    public void modifier(DemandeCredit t, int id) throws SQLException {
        String req = "update demande_credit set  amount = ? ,note = ?, cin1 = ?, cin2 = ? where id = ?";
        PreparedStatement pstmt = cnx.prepareStatement(req);
        pstmt.setInt(1, t.getAmount());
        pstmt.setString(2, t.getNote());
        pstmt.setString(3, t.getCin1());
        pstmt.setString(4, t.getCin2());
        pstmt.setInt(5, id);
        pstmt.executeUpdate();   
    }
     public void modifierStatusToAccepted(DemandeCredit t, int id) throws SQLException {
        String req = "update demande_credit set  status = ? where id = ?";
        PreparedStatement pstmt = cnx.prepareStatement(req);  
        pstmt.setString(1, "accepted");
        pstmt.setInt(2, id);
        pstmt.executeUpdate();   
    }
       public void modifierStatusToRejected(DemandeCredit t, int id) throws SQLException {
        String req = "update demande_credit set  status = ? where id = ?";
        PreparedStatement pstmt = cnx.prepareStatement(req);  
        pstmt.setString(1, "rejected");
        pstmt.setInt(2, id);
        pstmt.executeUpdate();   
    }

    public void supprimer(DemandeCredit t, int id) throws SQLException {
        String req = "DELETE FROM demande_credit WHERE id = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, t.getId()); // set the value of the id parameter
        st.executeUpdate();
    }

    public List<DemandeCredit> recuperer() throws SQLException {
        CreditService cs = new CreditService();
        UserService s = new UserService();

        List<DemandeCredit> demandes = new ArrayList<>();
        String req = "select * from demande_credit";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            DemandeCredit p = new DemandeCredit();
            p.setId(rs.getInt("id"));
            p.setCredit(cs.getById(rs.getInt("credit_id_id")));
            p.setUser(s.getById(rs.getInt("user_id_id")));
            p.setAmount(rs.getInt("amount"));
            p.setCreatedAt(rs.getDate("created_at"));
            p.setNote(rs.getString("note"));
            p.setStatus(rs.getString("status"));
            p.setCin1(rs.getString("cin1"));
            p.setCin2(rs.getString("cin2"));
            demandes.add(p);
        }
        return demandes;
    }

    public List<DemandeCredit> recupererById(int id) throws SQLException {
        List<DemandeCredit> demandes = new ArrayList<>();
            CreditService cs = new CreditService();
        UserService s = new UserService();
        String req = "select * from demande_credit where id= ?";
        PreparedStatement st = cnx.prepareStatement(req);
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            DemandeCredit p = new DemandeCredit();
            p.setId(rs.getInt("id"));
            p.setCredit(cs.getById(rs.getInt("credit_id_id")));
            p.setUser(s.getById(rs.getInt("user_id_id")));
            p.setAmount(rs.getInt("amount"));
            p.setCreatedAt(rs.getDate("created_at"));
            p.setNote(rs.getString("note"));
            p.setStatus(rs.getString("status"));
            p.setCin1(rs.getString("cin1"));
            p.setCin2(rs.getString("cin2"));
            demandes.add(p);
        }
        return demandes;
    }
    
}
