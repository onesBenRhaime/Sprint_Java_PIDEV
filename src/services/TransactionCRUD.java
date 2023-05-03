/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import models.Transaction;
import utils.MyConnection;

public class TransactionCRUD implements IService<Transaction> {

    Connection cnx = MyConnection.getInstance().getCon();

    @Override
    public void ajouter(Transaction t) throws SQLException {
        try {
            String req = "INSERT INTO transaction(compte_id,type_transaction,montant,request_from,request_to,statue,agence_name,date) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            //ps.setInt(1,c.getId_categorie());
            ps.setInt(1, t.getCompteid());
            ps.setString(2, "Wire Transfer");
            ps.setString(3, t.getMontant());
            ps.setString(4, t.getRequestFrom());
            ps.setString(5, t.getRequestTo());
            ps.setString(6, "in progress");
            ps.setString(7, t.getAgenceName());
            ps.setDate(8, Date.valueOf(LocalDate.now()));

            ps.executeUpdate();

            System.out.println("Transaction  ajoutee avec succes");

        } catch (SQLException ex) {
            System.out.println("Transaction  non ajoutee !!");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Transaction t, int id) throws SQLException {
        try {
            String query = "UPDATE transaction SET montant=?, agence_name=?, request_to=?  WHERE id=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, t.getMontant());
            stmt.setString(2, t.getAgenceName());
            stmt.setString(3, t.getRequestTo());
            stmt.setInt(4, id);
            stmt.executeUpdate();

            System.out.println("Transaction  updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Transaction t) throws SQLException {
        try {
            String req = "DELETE FROM  transaction WHERE id = " + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(" transaction deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Transaction> recuperer() throws SQLException {
        List<Transaction> list = new ArrayList<>();
        try {
            String req = "Select * from  transaction";
            Statement st = cnx.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Transaction t = new Transaction();
                t.setId(RS.getInt("id"));
                t.setCompteid(RS.getInt(2));
                t.setTypeTransaction(RS.getString(3));
                t.setMontant(RS.getString(4));
                t.setRequestFrom(RS.getString(5));
                t.setRequestTo(RS.getString(6));
                t.setStatue(RS.getString(7));
                t.setAgenceName(RS.getString(8));
                t.setDate(RS.getDate(9));
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;

    }

    public List<Transaction> recupererSendMoney() throws SQLException {
        List<Transaction> list = new ArrayList<>();
        try {
            String req = "Select * from  transaction where type_transaction = 'Send Money' ";
            Statement st = cnx.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Transaction t = new Transaction();
                t.setId(RS.getInt("id"));
                t.setCompteid(RS.getInt(2));
                t.setTypeTransaction(RS.getString(3));
                t.setMontant(RS.getString(4));
                t.setRequestFrom(RS.getString(5));
                System.out.println("/n");
                System.out.println(" 5 : " + RS.getString(5));
                t.setRequestTo(RS.getString(6));
                t.setStatue(RS.getString(7));
                t.setAgenceName(RS.getString(8));
                t.setDate(RS.getDate(9));
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;

    }

    public void ajouterSendMoney(Transaction t) throws SQLException {
        try {
            System.out.println(t);
            String req = "INSERT INTO transaction(compte_id,type_transaction,montant,request_from,request_to,statue,agence_name,date) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println(ps);
            //ps.setInt(1,c.getId_categorie());
            ps.setInt(1, t.getCompteid());
            ps.setString(2, "Send Money");
            ps.setString(3, t.getMontant());
            ps.setString(4, t.getRequestFrom());
            ps.setString(5, t.getRequestTo());
            ps.setString(6, "valide");
            ps.setString(7, "no agence");
            ps.setDate(8, Date.valueOf(LocalDate.now()));

            ps.executeUpdate();

            System.out.println("Send Money  ajoutee avec succes");

        } catch (SQLException ex) {
            System.out.println("Transaction  non ajoutee !!");
            System.out.println(ex.getMessage());
        }
    }

    public boolean AgenceExiste(String agenceName) {

        String sql = "SELECT nom FROM agence WHERE nom = ?";
        try {
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setString(1, agenceName);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("AgenceExiste :" + rs);
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void accept(Transaction t) throws SQLException {
        try {
            String query = "UPDATE transaction SET statue=? WHERE id=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, "valide");
            stmt.setInt(2, t.getId());
            stmt.executeUpdate();

            System.out.println("Transaction  Accepted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void reject(Transaction t) throws SQLException {
        try {
            String query = "UPDATE transaction SET statue=? WHERE id=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, "rejected");
            stmt.setInt(2, t.getId());
            stmt.executeUpdate();

            System.out.println("Transaction Rejected !");
                 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int nbSendMoney() throws SQLException {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) FROM transaction WHERE type_transaction='Send Money'";

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                count = rs.getInt(1);
            }

           System.out.println("Number of Send Money transactions: " + count);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }    
    public int nbWireTransfer() throws SQLException {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) FROM transaction WHERE type_transaction='Wire Transfer'";

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                count = rs.getInt(1);
            }

         System.out.println("Number of Wire Transfer transactions: " + count);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }    
    public int nbRejectedWireTransfer() throws SQLException {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) FROM transaction WHERE statue='rejected' AND  type_transaction='Wire Transfer' ";

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                count = rs.getInt(1);
            }

         System.out.println("Number of rejected Wire Transfer transactions: " + count);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }
    public int nbAcceptedWireTransfer() throws SQLException {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) FROM transaction WHERE statue='valide' AND  type_transaction='Wire Transfer' ";

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                count = rs.getInt(1);
            }

         System.out.println("Number of Accepted Wire Transfer transactions: " + count);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

}
