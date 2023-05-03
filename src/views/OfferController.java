/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import models.Credit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import services.MyListener;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class OfferController implements Initializable {

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
    private VBox chosenOfferCard;
    private Credit credit;
    private MyListener myListener;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(credit);
    }

   

    public void setData(Credit credit, MyListener myListener) {
        this.credit = credit;
        this.myListener = myListener;
        lbcat.setText(credit.getCategoryName());
        lbRate.setText("Per Installment : "+credit.getLoanRate()+"%");
        lbMin.setText("Minimum Amount : "+credit.getMinAmount());
        lbMax.setText("Maximum Amount : "+credit.getMaxAmount());
        lbMonths.setText("Installment Interval : "+credit.getMonths()+"Months");
        lbWithdraw.setText("Withdraw Monthly : "+credit.getWithdrawMonthly());

       
    } 
}
