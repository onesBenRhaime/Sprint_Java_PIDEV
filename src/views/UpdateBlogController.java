/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.Blog;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.BlogService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UpdateBlogController implements Initializable {

    @FXML
    private TextField cName;
    @FXML
    private TextField cDesc;
    @FXML
    private TextField cDetails;
    @FXML
    private Button btnSave;

    private Blog blog;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void UpdateBlog(ActionEvent event) throws SQLException {
        String name = cName.getText();
        String description = cDesc.getText();
        String details = cDetails.getText();
        BlogService bgs = new BlogService();
       this.blog.setName(name);
       this.blog.setDescription(description);
       this.blog.setDetails(details);
        bgs.update(blog);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void initData(Blog blog) {
        // initialize the text fields with the data from the Blog object
        this.blog = blog;
        cName.setText(blog.getName());
        cDesc.setText(blog.getDescription());
        cDetails.setText(blog.getDetails());
    }
}
