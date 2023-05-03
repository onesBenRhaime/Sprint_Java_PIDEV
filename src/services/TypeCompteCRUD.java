
package services;
import models.TypeCompte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Compte;
import utils.MyConnection;

public class TypeCompteCRUD  implements IService<TypeCompte>{
    
     Connection cnx = MyConnection.getInstance().getCon();

    @Override
    public void ajouter(TypeCompte t) throws SQLException {
//        String req = "insert into type_compte(type,description) values('" + t.getType() + "','" + t.getDescription()+ ")";//        
//        Statement st = cnx.createStatement();
//        st.executeUpdate(req);
         try {
            String req = "INSERT INTO type_compte(type,description) VALUES (?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            //ps.setInt(1,c.getId_categorie());
            ps.setString(1, t.getType());
            ps.setString(2, t.getDescription());
            ps.executeUpdate();
            System.out.println("type compte ajoutee avec succes");
        } catch (SQLException ex) {
            System.out.println("type compte  non ajoutee !!");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(TypeCompte t,int id) throws SQLException {
        try {
            String query = "UPDATE type_compte SET type=?, description=? WHERE id=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, t.getType());
            stmt.setString(2, t.getDescription());
            stmt.setInt(3, id);
            stmt.executeUpdate();

            System.out.println("type compte  updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(TypeCompte t) throws SQLException {
        try {
            String req = "DELETE FROM  type_compte WHERE id = " + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(" type compte deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<TypeCompte> recuperer() throws SQLException {
    List<TypeCompte> list = new ArrayList<>();
        try {
            String req = "Select * from  type_compte";
            Statement st = cnx.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                TypeCompte t = new TypeCompte();
                t.setId(RS.getInt("id"));
                t.setType(RS.getString(2));
                t.setDescription(RS.getString(3));
                list.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;

    }
    
    //Test d'unicité
    public boolean Existe(String type) {

        String sql = "SELECT * FROM type_compte WHERE type = ?";
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
 
      //Test d'unicité
    public TypeCompte NameTypeByID(Compte compte) {

        String sql = "SELECT type FROM type_compte WHERE id = ?";
        try {
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setInt(1, compte.getIdTypeId());
            ResultSet rs = pstmt.executeQuery();
            System.out.println(" ***"+compte.getIdTypeId());
            while (rs.next()) {
                TypeCompte t = new TypeCompte();   
                  t.setType(rs.getString(1));
           return  t;
               
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
           return  null;
        }
         return null;
    }
 
    
}
