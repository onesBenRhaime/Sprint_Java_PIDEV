/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.Category;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.controlsfx.control.Notifications;
import services.CategoryService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddCategoryController implements Initializable {

    CategoryService ps = new CategoryService();
    @FXML
    private TextField nameT;
    @FXML
    private TextField descriptionT;
    @FXML
    private Button btnSave;
    @FXML
    private TableView<Category> tbCategories;
    @FXML
    private TableColumn<?, ?> Cid;
    @FXML
    private TableColumn<?, ?> cName;
    @FXML
    private TableColumn<?, ?> cDesc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ListeCategory();
            //tbCategories.setItems(FXCollections.observableArrayList(categoriesList));

        } catch (SQLException ex) {
            Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private List<Category> categoriesList;

    @FXML
    public void ListeCategory() throws SQLException {
        CategoryService hrc = new CategoryService();

        Cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        boolean deleteColumnExists = false;
        for (TableColumn column : tbCategories.getColumns()) {
            if (column.getText().equals("ACTION")) {
                deleteColumnExists = true;
                break;
            }
        }

        if (!deleteColumnExists) {
            TableColumn<Category, Void> deleteColumn = new TableColumn<>("ACTION");
            deleteColumn.setCellFactory(column -> {
                return new TableCell<Category, Void>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.setOnAction(event -> {
                            Category ctg = getTableView().getItems().get(getIndex());
                            CategoryService ctgs = new CategoryService();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Delete Blog");
                            alert.setHeaderText("Are you sure you want to delete your blog?");
                            alert.setContentText("This action cannot be undone.");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                try {
                                    ctgs.deleteCategory(ctg.getId());
                                    refreshTable();
                                } catch (SQLException ex) {
                                    Logger.getLogger(AddBlogController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.out.println(
                                        "Delete Category with id " + ctg.getId());
                            } else {
                                
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
            tbCategories.getColumns().add(deleteColumn);
        }

        List<Category> list = hrc.getAll();
        ObservableList<Category> observableList = FXCollections.observableArrayList(list);
        tbCategories.setItems(observableList);
    }

    private void refreshTable() {
        try {
            categoriesList = new CategoryService().getAll();
            tbCategories.setItems(FXCollections.observableArrayList(categoriesList));
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
    private void AjouterCategory(ActionEvent event) {
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

            if (allValid) {
                Category P = new Category(name, description);
                ps.add(P);
                int s = 3; // define and assign a value to s
                Notifications notificationBuilder = Notifications.create()
                        .title("Alert")
                        .text("Category added SUCCESSFULLY")
                        .graphic(null)
                        .hideAfter(Duration.seconds(s))
                        .position(Pos.CENTER)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("Category added  successfully ");
                            }
                        });
                nameT.setText("");
                descriptionT.setText("");
                notificationBuilder.darkStyle();
                notificationBuilder.show();
                refreshTable();
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }

}
