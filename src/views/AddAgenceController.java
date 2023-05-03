/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.Agence;
import models.Category;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.AgenceService;
import services.CategoryService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddAgenceController implements Initializable {

    @FXML
    private TextField nameT;
    @FXML
    private TextField descriptionT;
    @FXML
    private Button btnSave;
    @FXML
    private TableView<Agence> tbAgences;
    @FXML
    private TableColumn<?, ?> Cid;
    @FXML
    private TableColumn<?, ?> cName;
    @FXML
    private TableColumn<?, ?> cDesc;
AgenceService ps = new AgenceService();
    @FXML
    private Button refresh;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ListeAgence();
            //tbCategories.setItems(FXCollections.observableArrayList(categoriesList));

        } catch (SQLException ex) {
            Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private List<Agence> agencesList;
    
    public void ListeAgence() throws SQLException {
        AgenceService hrc = new AgenceService();

        Cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        boolean deleteColumnExists = false;
        boolean updateColumnExists = false;
        for (TableColumn column : tbAgences.getColumns()) {
            if (column.getText().equals("ACTION")) {
                deleteColumnExists = true;
                break;
            }else if (column.getText().equals("UPDATE")) {
                updateColumnExists = true;
            }
        }

        if (!deleteColumnExists) {
            TableColumn<Agence, Void> deleteColumn = new TableColumn<>("ACTION");
            deleteColumn.setCellFactory(column -> {
                return new TableCell<Agence, Void>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.setOnAction(event -> {
                            Agence ctg = getTableView().getItems().get(getIndex());
                            AgenceService ctgs = new AgenceService();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Delete Agence");
                            alert.setHeaderText("Are you sure you want to delete your agence?");
                            alert.setContentText("This action cannot be undone.");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                try {
                                    ctgs.deleteAgence(ctg.getId());
                                    refreshTable();
                                } catch (SQLException ex) {
                                    Logger.getLogger(AddAgenceController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.out.println(
                                        "Delete Agence with id " + ctg.getId());
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
            tbAgences.getColumns().add(deleteColumn);
        }
        if (!updateColumnExists) {
            TableColumn<Agence, Void> updateColumn = new TableColumn<>("UPDATE");
            updateColumn.setCellFactory(column -> {
                return new TableCell<Agence, Void>() {
                    private final Button updateButton = new Button("Update");

                    {
                        updateButton.setOnAction(event -> {
                            Agence blg = getTableView().getItems().get(getIndex());
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateAgence.fxml"));
                                Parent root = loader.load();
                                UpdateAgenceController controller = loader.getController();
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
            tbAgences.getColumns().add(updateColumn);
        }

        List<Agence> list = hrc.getAll();
        ObservableList<Agence> observableList = FXCollections.observableArrayList(list);
        tbAgences.setItems(observableList);
    }

    @FXML
    private void refreshTable() {
        try {
            agencesList = new AgenceService().getAll();
            tbAgences.setItems(FXCollections.observableArrayList(agencesList));
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
    private void AjouterAgence(ActionEvent event) {
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
                Agence P = new Agence(name, description);
                ps.add(P);
                int s = 3; // define and assign a value to s
                Notifications notificationBuilder = Notifications.create()
                        .title("Alert")
                        .text("Agence added SUCCESSFULLY")
                        .graphic(null)
                        .hideAfter(Duration.seconds(s))
                        .position(Pos.CENTER)
                        .onAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println("Agence added  successfully ");
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
