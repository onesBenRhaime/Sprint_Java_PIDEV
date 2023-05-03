
package test;


import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Compte;
import models.Transaction;
import models.TypeCompte;
import services.CompteCRUD;
import services.TransactionCRUD;
import services.TypeCompteCRUD;

public class Test {
    
    public static void main(String[] args) throws SQLException {
        TypeCompteCRUD tc = new TypeCompteCRUD();
        TransactionCRUD ts=new TransactionCRUD();
        CompteCRUD cs=new CompteCRUD();
        TypeCompte t = new TypeCompte(22,"salkma", "13545445");
        Compte c= new Compte(1,2,"2222", "2222", "2222", "2222", "2222", "2222");
        System.out.println("name Type : "+tc.NameTypeByID(c));
        //   cs.ajouter(c);
         ts.nbSendMoney();
        ts.nbWireTransfer(); 
        ts.nbAcceptedWireTransfer();ts.nbRejectedWireTransfer();
        Transaction a=new Transaction(23, "01234567891234", "01234567891234", "450");
        // System.out.println(a);
        // tc.ajouter(t);
        //  ts.modifier(a, 64);
        //   ts.accept(a);
        //tc.supprimer(t);
        //System.out.println(ts.AgenceExiste("agence ariana"));
        //  ts.ajouterSendMoney(a);
        //  System.out.println(ts.recupererSendMoney());
       
       
    }
    
    
    }
