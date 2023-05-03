
package models;

import java.sql.Date;

public class Compte {
    
       private int id , idUserId, idTypeId;
       private String cinS1 , cinS2,otherDoc,maxSolde ,minSolde , redSolde,rib , staute , solde;
       private Date  dateCreation ,dateFermeture;;

    public Compte() {
    }

    public Compte(int id, int idUserId, int idTypeId, String cinS1, String cinS2, String otherDoc, String maxSolde, String minSolde, String redSolde, String rib, String staute, String solde, Date dateCreation, Date dateFermeture) {
        this.id = id;
        this.idUserId = idUserId;
        this.idTypeId = idTypeId;
        this.cinS1 = cinS1;
        this.cinS2 = cinS2;
        this.otherDoc = otherDoc;
        this.maxSolde = maxSolde;
        this.minSolde = minSolde;
        this.redSolde = redSolde;
        this.rib = rib;
        this.staute = staute;
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.dateFermeture = dateFermeture;
    }

    public Compte(int idUserId, int idTypeId, String cinS1, String cinS2, String otherDoc, String maxSolde, String minSolde, String redSolde, String rib, String staute, String solde, Date dateCreation, Date dateFermeture) {
        this.idUserId = idUserId;
        this.idTypeId = idTypeId;
        this.cinS1 = cinS1;
        this.cinS2 = cinS2;
        this.otherDoc = otherDoc;
        this.maxSolde = maxSolde;
        this.minSolde = minSolde;
        this.redSolde = redSolde;
        this.rib = rib;
        this.staute = staute;
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.dateFermeture = dateFermeture;
    }

    public Compte(int idUserId, int idTypeId, String cinS1, String cinS2, String otherDoc, String maxSolde, String minSolde, String redSolde, Date dateCreation, Date dateFermeture) {
        this.idUserId = idUserId;
        this.idTypeId = idTypeId;
        this.cinS1 = cinS1;
        this.cinS2 = cinS2;
        this.otherDoc = otherDoc;
        this.maxSolde = maxSolde;
        this.minSolde = minSolde;
        this.redSolde = redSolde;
        this.dateCreation = dateCreation;
        this.dateFermeture = dateFermeture;
    }

    public Compte(String cinS1, String cinS2, String otherDoc, String maxSolde, String minSolde, String redSolde) {
       this.idUserId=idUserId;
        this.idTypeId=idTypeId;
        this.cinS1 = cinS1;
        this.cinS2 = cinS2;
        this.otherDoc = otherDoc;
        this.maxSolde = maxSolde;
        this.minSolde = minSolde;
        this.redSolde = redSolde;
    
    }

    public Compte(int idUserId, int idTypeId ,String cinS1, String cinS2, String otherDoc, String maxSolde, String minSolde, String redSolde) {
       this.idUserId=idUserId;
        this.idTypeId=idTypeId;
        this.cinS1 = cinS1;
        this.cinS2 = cinS2;
        this.otherDoc = otherDoc;
        this.maxSolde = maxSolde;
        this.minSolde = minSolde;
        this.redSolde = redSolde;
    
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUserId() {
        return idUserId;
    }

    public void setIdUserId(int idUserId) {
        this.idUserId = idUserId;
    }

    public int getIdTypeId() {
        return idTypeId;
    }

    public void setIdTypeId(int idTypeId) {
        this.idTypeId = idTypeId;
    }

    public String getCinS1() {
        return cinS1;
    }

    public void setCinS1(String cinS1) {
        this.cinS1 = cinS1;
    }

    public String getCinS2() {
        return cinS2;
    }

    public void setCinS2(String cinS2) {
        this.cinS2 = cinS2;
    }

    public String getOtherDoc() {
        return otherDoc;
    }

    public void setOtherDoc(String otherDoc) {
        this.otherDoc = otherDoc;
    }

    public String getMaxSolde() {
        return maxSolde;
    }

    public void setMaxSolde(String maxSolde) {
        this.maxSolde = maxSolde;
    }

    public String getMinSolde() {
        return minSolde;
    }

    public void setMinSolde(String minSolde) {
        this.minSolde = minSolde;
    }

    public String getRedSolde() {
        return redSolde;
    }

    public void setRedSolde(String redSolde) {
        this.redSolde = redSolde;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public String getStaute() {
        return staute;
    }

    public void setStaute(String staute) {
        this.staute = staute;
    }

    public String getSolde() {
        return solde;
    }

    public void setSolde(String solde) {
        this.solde = solde;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateFermeture() {
        return dateFermeture;
    }

    public void setDateFermeture(Date dateFermeture) {
        this.dateFermeture = dateFermeture;
    }

    @Override
    public String toString() {
        return "Compte{" + "id=" + id + ", idUserId=" + idUserId + ", idTypeId=" + idTypeId + ", cinS1=" + cinS1 + ", cinS2=" + cinS2 + ", otherDoc=" + otherDoc + ", maxSolde=" + maxSolde + ", minSolde=" + minSolde + ", redSolde=" + redSolde + ", rib=" + rib + ", staute=" + staute + ", solde=" + solde + ", dateCreation=" + dateCreation + ", dateFermeture=" + dateFermeture + '}';
    }


    
}
