/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.Credit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javax.swing.JSlider;
import services.CreditService;
import services.MyListener;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class OffersController implements Initializable {

    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Label lbcat;
    @FXML
    private Label lbRate;
    @FXML
    private Label lbMin;
    @FXML
    private Label lbMax;
    @FXML
    private Label lbMonths;
    @FXML
    private Label lbWithdraw;
    @FXML
    private Button btnApply;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    CreditService ps = new CreditService();
    ObservableList<Credit> obs;
    private MyListener myListener;
private Credit selectedCredit;
    @FXML
    private VBox chosenOfferCard;
    @FXML
    private Slider slider;
    @FXML
    private Button search;
    @FXML
    private Button lboffers;
    @FXML
    private Button lbdem;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Credit> credits = ps.recuperer();
            obs = FXCollections.observableArrayList(credits);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        if (obs.size() > 0) {
            setChosenCredit(obs.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Credit credit) {
                    setChosenCredit(credit);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < obs.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/Offer.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                OfferController offerController = fxmlLoader.getController();
                offerController.setData(obs.get(i),myListener);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     private void setChosenCredit(Credit credit) {
         
        lbcat.setText(credit.getCategoryName());
        lbRate.setText("Per Installment : "+credit.getLoanRate()+"%");
        lbMin.setText("Minimum Amount : "+credit.getMinAmount());
        lbMax.setText("Maximum Amount : "+credit.getMaxAmount());
        lbMonths.setText("Installment Interval : "+credit.getMonths()+"Months");
        lbWithdraw.setText("Withdraw Monthly : "+credit.getWithdrawMonthly());
        selectedCredit = credit;

    }
     

    @FXML
private void search(ActionEvent event) {
   
    try {
        String searchTerm = tfSearch.getText().trim().toLowerCase();
        List<Credit> credits = ps.getByCategoryName(searchTerm);
        obs.clear();
        obs.addAll(credits);
        if (obs.size() > 0) {
            setChosenCredit(obs.get(0));
        }
        int column = 0;
        int row = 1;
        grid.getChildren().clear();
        for (int i = 0; i < obs.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/Offer.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            OfferController offerController = fxmlLoader.getController();
            offerController.setData(obs.get(i), myListener);

            if (column == 2) {
                column = 0;
                row++;
            }

            grid.add(anchorPane, column++, row); //(child,column,row)
            //set grid width
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);

            //set grid height
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);

            GridPane.setMargin(anchorPane, new Insets(10));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    @FXML
    private void apply(ActionEvent event) {
         try {
    
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateDemandeCredit.fxml"));
            Parent root = loader.load();
            CreateDemandeCreditController controller = loader.getController();
            controller.setCredit(selectedCredit);
            btnApply.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }

  @FXML
private void searchAmount(ActionEvent event) {
    try {
        int sliderValue = (int) slider.getValue();
        int range = 1000000000 - 1000; // the range is the difference between the max and min amount in the data

        int maxAmount = (int) (1000 + sliderValue / 100.0 * range);
        int minAmount = sliderValue < slider.getBlockIncrement() ? 1000 : (int) (1000 + (sliderValue - slider.getBlockIncrement()) / 100.0 * range);
        System.out.println(minAmount);
        System.out.println(maxAmount);

        List<Credit> credits = ps.getByAmountInterval(minAmount, maxAmount);
        obs.clear();
        obs.addAll(credits.stream()
            .filter(c -> c.getMinAmount() >= minAmount && c.getMaxAmount() <= maxAmount) // filter the credits based on the slider range
            .collect(Collectors.toList()));

        if (obs.size() > 0) {
            setChosenCredit(obs.get(0));
        }

        int column = 0;
        int row = 1;
        grid.getChildren().clear();

        for (int i = 0; i < obs.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/Offer.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            OfferController offerController = fxmlLoader.getController();
            offerController.setData(obs.get(i), myListener);

            if (column == 2) {
                column = 0;
                row++;
            }

            grid.add(anchorPane, column++, row);

            GridPane.setMargin(anchorPane, new Insets(10));
        }
    } catch (IOException e) {
        System.out.println(e.getMessage());
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

   @FXML
    private void offers(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Offers.fxml"));
            Parent root = loader.load();
            lboffers.getScene().setRoot(root);
        } catch (IOException ex) {
                System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void mydemandes(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MyDemandes.fxml"));
            Parent root = loader.load();
            lbdem.getScene().setRoot(root);
        } catch (IOException ex) {
                System.out.println(ex.getMessage());

        }
    }

}
