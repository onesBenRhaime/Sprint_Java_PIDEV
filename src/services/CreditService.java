/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.CategoryCredit;
import models.Credit;
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
public class CreditService {

    Connection cnx;

    public CreditService() {
        cnx = MyConnection.getInstance().getCon();
    }

    public void ajouter(Credit t) throws SQLException {
        String req = "INSERT INTO credit (credit_category_id, min_amount, max_amount, withdraw_monthly, months, loan_rate) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = cnx.prepareStatement(req);
        pstmt.setInt(1, t.getCategory().getId());
        pstmt.setInt(2, t.getMinAmount());
        pstmt.setInt(3, t.getMaxAmount());
        pstmt.setInt(4, t.getWithdrawMonthly());
        pstmt.setInt(5, t.getMonths());
        pstmt.setInt(6, t.getLoanRate());
        pstmt.executeUpdate();
    }

    public void modifier(Credit t, int id) throws SQLException {

        String req = "update credit set credit_category_id = ?, min_amount = ?, max_amount = ?, withdraw_monthly = ?, months = ?, loan_rate = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getCategory().getId());
        ps.setInt(2, t.getMinAmount());
        ps.setInt(3, t.getMaxAmount());
        ps.setInt(4, t.getWithdrawMonthly());
        ps.setInt(5, t.getMonths());
        ps.setInt(6, t.getLoanRate());
        ps.setInt(7, id);
        ps.executeUpdate();

    }

    public void supprimer(Credit t, int id) throws SQLException {
        String req = "DELETE FROM credit WHERE id = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, t.getId()); // set the value of the id parameter
        st.executeUpdate();
    }

    public List<Credit> recuperer() throws SQLException {
        CategoryCreditService cs = new CategoryCreditService();

        List<Credit> credits = new ArrayList<>();
        String req = "select * from credit";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Credit p = new Credit();
            p.setId(rs.getInt("id"));
            p.setCategory(cs.getById(rs.getInt("credit_category_id")));
            p.setMaxAmount(rs.getInt("max_amount"));
            p.setMinAmount(rs.getInt("min_amount"));
            p.setWithdrawMonthly(rs.getInt("withdraw_monthly"));
            p.setMonths(rs.getInt("months"));
            p.setLoanRate(rs.getInt("loan_rate"));

            credits.add(p);
            System.out.println("Id de la categorie " + cs.getById(rs.getInt("credit_category_id")));
            System.out.println(p);

        }
        return credits;
    }

    public List<Credit> recupererById(int id) throws SQLException {
        List<Credit> credits = new ArrayList<>();
        CategoryCreditService cs = new CategoryCreditService();

        String req = "select * from credit where id= ?";
        PreparedStatement st = cnx.prepareStatement(req);
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Credit c = new Credit();

            c.setCategory(cs.getById(rs.getInt("credit_category_id")));

            c.setMaxAmount(rs.getInt("max_amount"));
            c.setMinAmount(rs.getInt("min_amount"));
            c.setWithdrawMonthly(rs.getInt("withdraw_monthly"));
            c.setMonths(rs.getInt("months"));
            c.setLoanRate(rs.getInt("loan_rate"));

            credits.add(c);
        }
        return credits;
    }

    public Credit getById(int id) throws SQLException {
        Credit c = null;
        CategoryCreditService cs = new CategoryCreditService();

        String req = "select * from credit where id= ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id); // set the value of the id parameter
        ResultSet rs = st.executeQuery(); // execute the query
        if (rs.next()) {
            c = new Credit();
            c.setCategory(cs.getById(rs.getInt("credit_category_id")));
            c.setId(rs.getInt("id"));

            c.setMaxAmount(rs.getInt("max_amount"));
            c.setMinAmount(rs.getInt("min_amount"));
            c.setWithdrawMonthly(rs.getInt("withdraw_monthly"));
            c.setMonths(rs.getInt("months"));
            c.setLoanRate(rs.getInt("loan_rate"));
        }
        return c;
    }
    public List<Credit> getByCategoryName(String categoryName) throws SQLException {
    List<Credit> credits = new ArrayList<>();
    CategoryCreditService cs = new CategoryCreditService();

    String req = "SELECT * FROM credit WHERE credit_category_id IN (SELECT id FROM category_credit WHERE name = ?)";
    PreparedStatement st = cnx.prepareStatement(req);
    st.setString(1, categoryName);
    ResultSet rs = st.executeQuery();
    while (rs.next()) {
        Credit c = new Credit();
        c.setId(rs.getInt("id"));
        c.setCategory(cs.getById(rs.getInt("credit_category_id")));
        c.setMaxAmount(rs.getInt("max_amount"));
        c.setMinAmount(rs.getInt("min_amount"));
        c.setWithdrawMonthly(rs.getInt("withdraw_monthly"));
        c.setMonths(rs.getInt("months"));
        c.setLoanRate(rs.getInt("loan_rate"));
        credits.add(c);
    }
    return credits;
}
public List<Credit> getByAmountInterval(int minAmount, int maxAmount) throws SQLException {
    List<Credit> credits = new ArrayList<>();
    CategoryCreditService cs = new CategoryCreditService();

    String req = "SELECT * FROM credit WHERE min_amount >= ? AND max_amount <= ?";
    PreparedStatement st = cnx.prepareStatement(req);
    st.setInt(1, minAmount);
    st.setInt(2, maxAmount);
    ResultSet rs = st.executeQuery();
    while (rs.next()) {
        Credit c = new Credit();
        c.setId(rs.getInt("id"));
        c.setCategory(cs.getById(rs.getInt("credit_category_id")));
        c.setMaxAmount(rs.getInt("max_amount"));
        c.setMinAmount(rs.getInt("min_amount"));
        c.setWithdrawMonthly(rs.getInt("withdraw_monthly"));
        c.setMonths(rs.getInt("months"));
        c.setLoanRate(rs.getInt("loan_rate"));
        credits.add(c);
    }
    return credits;
}







}
