
package models;

import java.sql.Date;
import java.text.SimpleDateFormat;
public class Transaction {
    
       private int id , compteid;
       private String requestTo ,requestFrom,montant,typeTransaction,statue,agenceName ;
       private Date date;

    public Transaction() {
    }
  public Transaction(int compteid , String montant, String requestFrom, String requestTo, String agenceName) {
      this.compteid = compteid;
      this.montant = montant;
      this.requestFrom= requestFrom;
      this.requestTo = requestTo;
      this.agenceName = agenceName;    
    }

    public Transaction(int compteid, String requestTo, String requestFrom, String montant) {
        this.compteid = compteid;
        this.requestTo = requestTo;
        this.requestFrom = requestFrom;
        this.montant = montant;
    }
  
    public Transaction(int id, int compteid, String requestTo, String requestFrom, String montant, String typeTransaction, String statue, String agenceName, Date date) {
        this.id = id;
        this.compteid = compteid;
        this.requestTo = requestTo;
        this.requestFrom = requestFrom;
        this.montant = montant;
        this.typeTransaction = typeTransaction;
        this.statue = statue;
        this.agenceName = agenceName;
        this.date = date;
    }

    public Transaction(int compteid, String requestTo, String requestFrom, String montant, String typeTransaction, String statue, String agenceName, Date date) {
        this.compteid = compteid;
        this.requestTo = requestTo;
        this.requestFrom = requestFrom;
        this.montant = montant;
        this.typeTransaction = typeTransaction;
        this.statue = statue;
        this.agenceName = agenceName;
        this.date = date;
      }
    
  
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompteid() {
        return compteid;
    }

    public void setCompteid(int compteid) {
        this.compteid = compteid;
    }

    public String getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(String requestTo) {
        this.requestTo = requestTo;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }

    public String getAgenceName() {
        return agenceName;
    }

    public void setAgenceName(String agenceName) {
        this.agenceName = agenceName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", compteid=" + compteid + ", requestTo=" + requestTo + ", requestFrom=" + requestFrom + ", montant=" + montant + ", typeTransaction=" + typeTransaction + ", statue=" + statue + ", agenceName=" + agenceName + ", date=" + date + '}';
    }

    public Date getDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
       
    
}
