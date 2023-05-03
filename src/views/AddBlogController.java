/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import models.Blog;
import models.Category;
import java.io.FileOutputStream;
import java.io.IOException;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.text.Document;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.controlsfx.control.Notifications;
import services.BlogService;
import services.CategoryService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddBlogController implements Initializable {

    @FXML
    private TextField nameT;
    @FXML
    private TextField descriptionT;
    @FXML
    private TextField detailsT;
    @FXML
    private Button btnSave;
    @FXML
    private TableColumn<?, ?> Cid;
    @FXML
    private TableColumn<?, ?> cName;
    @FXML
    private TableColumn<?, ?> cDesc;
    @FXML
    private TableView<Blog> tbBlogs;
    @FXML
    private TableColumn<?, ?> cDetails;
    @FXML
    private ComboBox<String> categoryDropdown;
    @FXML
    private Button refresh;
    @FXML
    private TextField filterInput;
    @FXML
    private Button btnPDF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ListeBlog();
            CategoryService categoryService = new CategoryService();
            ObservableList<String> categoryNames = FXCollections.observableArrayList();
            List<Category> categories = categoryService.getAll();
            for (Category category : categories) {
                categoryNames.add(category.getName());
            }
            categoryDropdown.setItems(categoryNames);
            filterInput.textProperty().addListener((observable, oldValue, newValue) -> filterTable(null));

        } catch (SQLException ex) {
            Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    BlogService ps = new BlogService();

    private List<Blog> blogsList = new ArrayList<>();

    public void ListeBlog() throws SQLException {
        BlogService hrc = new BlogService();

        Cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        cDetails.setCellValueFactory(new PropertyValueFactory<>("details"));
        boolean deleteColumnExists = false;
        boolean updateColumnExists = false;
        for (TableColumn column : tbBlogs.getColumns()) {
            if (column.getText().equals("ACTION")) {
                deleteColumnExists = true;
                break;
            } else if (column.getText().equals("UPDATE")) {
                updateColumnExists = true;
            }
        }

        if (!deleteColumnExists) {
            TableColumn<Blog, Void> deleteColumn = new TableColumn<>("ACTION");
            deleteColumn.setCellFactory(column -> {
                return new TableCell<Blog, Void>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.setOnAction(event -> {
                            Blog blg = getTableView().getItems().get(getIndex());
                            BlogService blgs = new BlogService();
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Delete Blog");
                            alert.setHeaderText("Are you sure you want to delete your blog?");
                            alert.setContentText("This action cannot be undone.");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                try {
                                    blgs.deleteBlog(blg.getId());
                                    refreshTable();
                                } catch (SQLException ex) {
                                    Logger.getLogger(AddBlogController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.out.println(
                                        "Delete blog with id " + blg.getId());
                            } else {
                                // deletion failed, display an error message
                                /*Alert errorAlert = new Alert(AlertType.ERROR);
                                errorAlert.setTitle("Deletion Error");
                                errorAlert.setHeaderText(null);
                                errorAlert.setContentText("Unable to delete Blog".");
                                errorAlert.showAndWait();*/
                                alert.close();
                            }

                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
                        }
                    }
                };
            });
            tbBlogs.getColumns().add(deleteColumn);
        }
        if (!updateColumnExists) {
            TableColumn<Blog, Void> updateColumn = new TableColumn<>("UPDATE");
            updateColumn.setCellFactory(column -> {
                return new TableCell<Blog, Void>() {
                    private final Button updateButton = new Button("Update");

                    {
                        updateButton.setOnAction(event -> {
                            Blog blg = getTableView().getItems().get(getIndex());
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateBlog.fxml"));
                                Parent root = loader.load();
                                UpdateBlogController controller = loader.getController();
                                controller.initData(blg);
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(updateButton);
                        }
                    }
                };
            });
            tbBlogs.getColumns().add(updateColumn);
        }

        List<Blog> list = hrc.getAll();
        ObservableList<Blog> observableList = FXCollections.observableArrayList(list);
        tbBlogs.setItems(observableList);
    }

    @FXML
    private void refreshTable() {
        try {
            blogsList = new BlogService().getAll();
            tbBlogs.setItems(FXCollections.observableArrayList(blogsList));
            
        } catch (SQLException ex) {
            Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void AjouterBlog(ActionEvent event) {
        String category1 = categoryDropdown.getValue();

        try {
            boolean allValid = true;
            String name = nameT.getText();
            if (name.isEmpty()) {
                // Display error message
                showAlert("Name field cannot be empty");
                allValid = false;
            } else if (name.length() > 10) {
                // Display error message
                showAlert("Name field must be less than 50 characters");
                allValid = false;
            }
            String description = descriptionT.getText();
            if (description.isEmpty()) {
                // Display error message
                showAlert("Description field cannot be empty");
                allValid = false;
            } else if (description.length() > 50) {
                // Display error message
                showAlert("Description field must be less than 50 characters");
                allValid = false;
            }
            String details = detailsT.getText();
            if (details.isEmpty()) {
                // Display error message
                showAlert("Details field cannot be empty");
                allValid = false;
            } else if (details.length() > 50) {
                // Display error message
                showAlert("Details field must be less than 50 characters");
                allValid = false;
            }
            CategoryService categoryService = new CategoryService();
            List<Category> categories = categoryService.getAll();
            categoryDropdown.getItems().clear();// Fetch categories from the repository
            for (Category category : categories) {

                categoryDropdown.getItems().add(category.getName());
            }

            if (allValid) {
                Category selectedCategory = categories.stream().filter(category -> category.getName().equals(category1)).findFirst().orElse(null);
                if (selectedCategory != null) {
                    int categoryId = selectedCategory.getId();
                    System.out.println("Selected category id: " + categoryId);
                    Blog P = new Blog(name, description, details, categoryId);
                    ps.add(P);
                    // rest of the code

                int s = 3; // define and assign a value to s
                Notifications notificationBuilder = Notifications.create()
                        .title("Alert")
                        .text("Blog added SUCCESSFULLY")
                        .graphic(null)
                        .hideAfter(Duration.seconds(s))
                        .position(Pos.CENTER)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("Blog added  successfully ");
                            }
                        });
                nameT.setText("");
                descriptionT.setText("");
                detailsT.setText("");
                notificationBuilder.darkStyle();
                notificationBuilder.show();
                refreshTable();
                                } else {
                    showAlert("Please select a category");
                }
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            showAlert("Unable to add blog post");
        }

    }

    @FXML
    private void filterTable(KeyEvent event) {
        refreshTable();
        String filter = filterInput.getText();
    if (filter == null || filter.length() == 0) {
        // if the filter is empty, show all blogs
        tbBlogs.setItems(FXCollections.observableArrayList(blogsList));
        
    } else {
        // otherwise, filter the list of blogs by name
        List<Blog> filteredList = new ArrayList<>();
        
        for (Blog blog : blogsList) {
            if (blog.getName().toLowerCase().contains(filter.toLowerCase()) || blog.getDescription().toLowerCase().contains(filter.toLowerCase())) {
                
                filteredList.add(blog);
            }
        }
        tbBlogs.setItems(FXCollections.observableArrayList(filteredList));
    }
    }

    @FXML
    private void generatePdf(ActionEvent event) throws IOException {
        downloadPdf(tbBlogs.getItems());
    }

   

private void downloadPdf(List<Blog> blogsList) throws IOException {
    PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        int y = 700; // Starting Y coordinate

        PDType1Font font = PDType1Font.HELVETICA_BOLD;
        int fontSize = 16;
        int leading = 20;

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
    for (Blog blog : blogsList) {
        y -= leading;
        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(100, y);
        contentStream.showText("Title: " + blog.getName());
        contentStream.endText();

        y -= leading;
        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(100, y);
        contentStream.showText("Author: " + blog.getDescription());
        contentStream.endText();

        y -= leading;
        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.newLineAtOffset(100, y);
        contentStream.showText("Content: " + blog.getDetails());
        contentStream.endText();

        y -= leading * 2; // Add extra space between blogs
    }
}

document.save("blogsss.pdf");
document.close();
}








    
}
