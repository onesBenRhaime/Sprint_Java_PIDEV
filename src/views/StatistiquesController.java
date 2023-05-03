/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import services.DemandeCarte;
import services.TypeCarteService;
import services.TypeChequeService;
import models.CarteBancaire;
import models.TypeCheque;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class StatistiquesController implements Initializable {

    
    @FXML
    private PieChart piechart;
    List<TypeCheque> list = new ArrayList<TypeCheque>();
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    DemandeCarte DC = new DemandeCarte();
    TypeChequeService cc = new TypeChequeService();
     TypeCarteService tc = new TypeCarteService();
    @FXML
    private Button retour;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label1.setText("  " + DC.nbdemande());
        label2.setText("  " + tc.nbtypebynom());
        label3.setText("  " + tc.nbtypes());
        
        
        pieChartData.clear();

        //pieChartData.clear();

        ResultSet rs = DC.typecarte();
        try {
            while (rs.next()) {
                String TYPE = rs.getString("nom");
                int count = rs.getInt("cartebancaire_count");
//                System.out.println(category + ": " + count);
                PieChart.Data data = new PieChart.Data(TYPE, count);
                pieChartData.add(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatbpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        piechart.setData(pieChartData);
      
        
    }    

    @FXML
    private void back(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Typecarte.fxml"));
            Parent root = loader.load();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(StatistiquesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
