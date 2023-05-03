package models;

import javax.management.relation.Role;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import utils.MyConnection;
import java.sql.PreparedStatement;


public class User {
    
    private int id;
    private String phone, password, email, name, status, reset_token;
    private String[] roles;

    public User() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(String email, String password, String name, String status, String reset_token, String[] roles) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.status = status;
        this.reset_token = reset_token;
        this.roles = roles;
    }
    
    public User(int id, String email, String password, String name, String phone, String status, String reset_token, String[] roles)
    {   this.id =id ;
        this.password = password;
        this.email = email;
        this.name = name;
        this.status = status;
        this.reset_token = reset_token;
        this.roles = roles;
        this.phone = phone;
    }
    
    public User(String email, String password, String name, String phone ,String status, String reset_token, String[] roles) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.reset_token = reset_token;
        this.roles = roles;
    }

//    public User(int id, String email, String password, String name, String phone ,String status, String reset_token, String[] roles) {
//        this.id = id;
//        this.password = password;
//        this.email = email;
//        this.name = name;
//        this.phone = phone;
//        this.status = status;
//        this.reset_token = reset_token;
//        this.roles = roles;
//    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getReset_token() {
        return reset_token;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", password=" + password + ", email=" + email + ", name=" + name + ", status=" + status + ", reset_token=" + reset_token + ", roles=" + roles + '}';
    }

public boolean hasRole(String role) {
    for (String r : roles) {
        if (r.equals(role)) {
            return true;
        }
    }
    return false;
}

public boolean deleteAccount(User user) {
    String deleteQuery = "DELETE FROM user WHERE email = ?";
    try (Connection connection = MyConnection.getInstance().getCon();
         PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.executeUpdate();
        return true;
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        return false;
    }
}
}
