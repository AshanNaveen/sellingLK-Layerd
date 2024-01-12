package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.ijse.sellingLk.bo.BOFactory;
import lk.ijse.sellingLk.bo.custom.BuyerBO;
import lk.ijse.sellingLk.model.BuyerDto;
import lk.ijse.sellingLk.model.LoyalIdDto;
import lk.ijse.sellingLk.util.ValidateUtil;

import java.sql.SQLException;
import java.util.List;

public class NewBuyerFormController {
    @FXML
    private Pane buyerDetailPane;

    @FXML
    private JFXTextField txtBuyerName;

    @FXML
    private JFXTextField txtBuyerContact;

    @FXML
    private JFXTextField txtBuyerEmail;

    @FXML
    private JFXTextField txtBuyerAddress;

    @FXML
    private JFXTextField txtBuyerNic;

    @FXML
    private Label lblSaved;

    @FXML
    private JFXComboBox<String> cmbLoyalId;

    private BuyerBO buyerBO = (BuyerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BUYER);

    public void initialize() {
        loadLoyalId();
    }

    private void loadLoyalId() {

        ObservableList<String> obList= FXCollections.observableArrayList();
        try {
            List<LoyalIdDto> list = buyerBO.getAllLoyalIds();
            list.forEach(e->{
                obList.add(e.getId());
            });
            cmbLoyalId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnNewBuyerOnAction(ActionEvent event) {
        String name = txtBuyerName.getText();
        String contact = txtBuyerContact.getText();
        String email = txtBuyerEmail.getText();
        String address = txtBuyerAddress.getText();
        String nic = txtBuyerNic.getText();

        if (ValidateUtil.validatePhone(contact, txtBuyerContact) &&
                ValidateUtil.validateMail(email, txtBuyerEmail) &&
                ValidateUtil.validateNic(nic, txtBuyerNic) &&
                ValidateUtil.validateAddress(address, txtBuyerAddress)) {
            try {

                String uID = buyerBO.getUserId(SignInFormController.uname, SignInFormController.pword);
                boolean isSaved = buyerBO.saveBuyer(new BuyerDto(buyerBO.generateNextBuyerId(), name, nic, email, address, contact, uID, "normal"));
                if (isSaved) {
                    lblSaved.setText("Seller Saved Successfully !");
                    lblSaved.setVisible(true);
                    Timeline timeline = new Timeline(
                            new KeyFrame(Duration.millis(1000), e -> {
                                // Code to be executed at the end (500 millis)
                                lblSaved.setVisible(false);
                            })
                    );
                    timeline.setCycleCount(1);
                    timeline.play();
                } else {
                    lblSaved.setText("Try again !");
                    lblSaved.setVisible(true);
                    Timeline timeline = new Timeline(
                            new KeyFrame(Duration.millis(1000), e -> {
                                lblSaved.setVisible(false);
                            })
                    );
                    timeline.setCycleCount(1);
                    timeline.play();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void txtBuyerAddressOnAction(ActionEvent event) {
        cmbLoyalId.requestFocus();
    }

    @FXML
    void txtBuyerContactOnAction(ActionEvent event) {
        txtBuyerEmail.requestFocus();
    }

    @FXML
    void txtBuyerEmailOnAction(ActionEvent event) {
        txtBuyerNic.requestFocus();
    }

    @FXML
    void txtBuyerNameOnAction(ActionEvent event) {
        txtBuyerContact.requestFocus();
    }

    @FXML
    void txtBuyerNicOnAction(ActionEvent event) {
        txtBuyerAddress.requestFocus();
    }

    @FXML
    void cmbLoyalIdOnAction(ActionEvent event) {
        btnNewBuyerOnAction(event);
    }
}
