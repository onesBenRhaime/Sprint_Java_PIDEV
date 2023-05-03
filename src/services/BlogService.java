package services;

import models.Blog;
import models.Category;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

public class BlogService implements IServiceBlog<Blog>{
    private final String url = "jdbc:mysql://localhost:3306/appbank";
    private final String user = "root";
    private final String password = "";
    
    Connection cnx;

    public BlogService(int id, String name, String description, String details) {
        cnx = MyConnection.getInstance().getCon();
    }

    public BlogService() {
        cnx = MyConnection.getInstance().getCon();
    }
    
    @Override
    public void add(Blog t) throws SQLException {
        
       String req = "insert into blog(name,description,details) values('" + t.getName() + "','" + t.getDescription()+ "','" + t.getDetails()  + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }
    @Override
    public void update(Blog t) throws SQLException {
        String req = "update blog set name = ?, description = ?, details = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getName());
        ps.setString(2, t.getDescription());
        ps.setString(3, t.getDetails());
        ps.setInt(4, t.getId());
        ps.executeUpdate();

    }
    public void deleteBlog(int id) throws SQLException {
    String query = "DELETE FROM blog WHERE id = ?";
    PreparedStatement stmt = cnx.prepareStatement(query);
    stmt.setInt(1, id);
    stmt.executeUpdate();
}
    @Override
    public List<Blog> getAll() throws SQLException {
        List<Blog> blogs = new ArrayList<>();
        String req = "select * from blog";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Blog p = new Blog();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));
            p.setDetails(rs.getString("details"));
            
            blogs.add(p);
        }
        return blogs;
    }

    
        public List<Blog> getById(int id) throws SQLException {
        List<Blog> blogs = new ArrayList<>();
        String req = "select * from blog where id= ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            Blog p = new Blog();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));
            p.setDetails(rs.getString("details"));
            
            blogs.add(p);
        }
        return blogs;
    }


}
