package services;

import models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;
import org.mindrot.jbcrypt.BCrypt;
import services.PasswordEncoder;

public class UserService implements IService<User> {

    Connection cnx;

    public UserService() {
        cnx = MyConnection.getInstance().getCon();
    }

    @Override
    public void ajouter(User t) throws SQLException {
                String encodedPassword = PasswordEncoder.encode(t.getPassword());

        String req = "INSERT INTO user(email, roles, password, name, reset_token, status, phone) "
                + "VALUES('" + t.getEmail() + "', '[\"ROLE_USER\"]', '" + encodedPassword + "', '" + t.getName() + "', NULL, 'enabled', '" + t.getPhone() + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(User t ,int id) throws SQLException {
        String req = "update user set name = ?, email = ?, phone = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getName());
        ps.setString(2, t.getEmail());
        ps.setString(3, t.getPhone());
        ps.setInt(4, t.getId());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(User t) throws SQLException {
        String req = "delete from user where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId());
        ps.executeUpdate();
    }

    @Override
    public List<User> recuperer() throws SQLException {
        List<User> users = new ArrayList<>();
        String req = "select * from user";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            users.add(u);
        }
        return users;
    }

    public List<User> recupererById(int id) throws SQLException {
        List<User> users = new ArrayList<>();
        String req = "select * from user where id= ?";
        PreparedStatement st = cnx.prepareStatement(req);
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setEmail(rs.getString("email"));
            u.setStatus(rs.getString("status"));
            u.setPassword(rs.getString("password"));
            users.add(u);
        }
        return users;
    }

     public User login(String email, String password) throws SQLException {
    String req = "SELECT * FROM user WHERE email = ?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setString(1, email);

    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        String encodedPassword = rs.getString("password");
        boolean passwordMatch = BCrypt.checkpw(password, encodedPassword);
         if (passwordMatch) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String status = rs.getString("status");
                String phone = rs.getString("phone");
                String reset_token = rs.getString("reset_token");
                String[] roles = rs.getString("roles").split(",");
        
        User user = new User(id, email, password, name, phone, status, reset_token, roles);
        return user;
            } else {
                // Login failed, return null
                return null;
            }
        
        // Login successful, return true
    } else {
        // Login failed, return false
        return null;
    }
}

    public boolean emailExists(String email) throws SQLException {
        String req = "SELECT * FROM user WHERE email = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // Email exists in the database, return true
            return true;
        } else {
            // Email does not exist in the database, return false
            return false;
        }
    }

    public User getUserByUsername(String username) throws SQLException {
        User user = null;
        String query = "SELECT * FROM user WHERE email = ?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            int id = result.getInt("id");
            String password = result.getString("password");
            String email = result.getString("email");
            String name = result.getString("name");
            String phone = result.getString("phone");
            String status = result.getString("status");
            String resetToken = result.getString("reset_token");
            String[] roles = result.getString("roles").split(",");

            user = new User(id, email, password, name, phone, status, resetToken, roles);
        }

        return user;
    }

    public void deleteUser(User user) throws SQLException {
        String req = "DELETE FROM user WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, user.getId());
        ps.executeUpdate();

    }

    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String req = "select * from user";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            User p = new User();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setEmail(rs.getString("email"));
            p.setPhone(rs.getString("phone"));
            p.setStatus(rs.getString("status"));
            p.setPassword(rs.getString("password"));
            users.add(p);
        }
        return users;
    }

    public void deleteZUser(int id) throws SQLException {
        String query = "DELETE FROM user WHERE id = ?";
        PreparedStatement stmt = cnx.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    
    //hadill 
       public User getById(int id) throws SQLException {
    User c = null;
    String req = "select * from user where id= ?";
    PreparedStatement st = cnx.prepareStatement(req);
    st.setInt(1, id); // set the value of the id parameter
    ResultSet rs = st.executeQuery(); // execute the query
    if (rs.next()) {
        c = new User();
          c.setId(rs.getInt("id"));
           c.setName(rs.getString("name"));
            c.setEmail(rs.getString("email"));
            c.setPhone(rs.getString("phone"));
            c.setStatus(rs.getString("status"));
            c.setPassword(rs.getString("password"));
   
    }
    return c;
}

    
}
