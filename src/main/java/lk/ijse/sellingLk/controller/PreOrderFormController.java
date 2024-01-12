package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import lk.ijse.sellingLk.bo.BOFactory;
import lk.ijse.sellingLk.bo.custom.PreOrderBO;
import lk.ijse.sellingLk.controller.barController.PreOrderbarController;
import lk.ijse.sellingLk.model.PreOrderDto;
import lk.ijse.sellingLk.util.Navigation;
import lk.ijse.sellingLk.util.ValidateUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PreOrderFormController {

    @FXML
    private JFXTextField txtBrand;

    @FXML
    private VBox vBox;

    @FXML
    private JFXTextField txtYear;

    @FXML
    private JFXTextField txtBuyerContact;

    @FXML
    private JFXTextField txtModel;

    @FXML
    private Label txtBuyerName;

    @FXML
    private Label lblWarning;

    private PreOrderBO preOrderBO = (PreOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PREORDER);

    public void initialize(){
        loadData();
    }

    private void loadData() {
        vBox.getChildren().clear();
        try {
            List<PreOrderDto> list = preOrderBO.getAllPreOrders();
            for (int i = 0; i < list.size(); i++) {
                setData(list.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setData(PreOrderDto dto) {
        try {
            FXMLLoader loader = new FXMLLoader(PreOrderbarController.class.getResource("/bar/preOrderBar.fxml"));
            Parent root = loader.load();
            PreOrderbarController controller = loader.getController();
            controller.setData(dto);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtBrandOnAction(ActionEvent event) {
        txtModel.requestFocus();
    }

    @FXML
    void txtBrandkeyPressed(KeyEvent event) {
        ValidateUtil.validateName(txtBrand.getText(),txtBrand);
    }

    @FXML
    void txtContactKeyPressed(KeyEvent event) {
        ValidateUtil.validatePhone(txtBuyerContact.getText(),txtBuyerContact);
        if (lblWarning.isVisible() && txtBuyerContact.getText().length() < 9) {
            lblWarning.setVisible(false);
        }
    }

    @FXML
    void txtContactOnAction(ActionEvent event) {
        try {
            String buyerName = preOrderBO.getBuyerName(txtBuyerContact.getText());
            if (buyerName != null) {
                txtBuyerName.setText(buyerName);
                lblWarning.setVisible(false);
            } else {
                lblWarning.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtModelKeyPressed(KeyEvent event) {
        ValidateUtil.validateName(txtModel.getText(),txtModel);
    }

    @FXML
    void txtModelOnAction(ActionEvent event) {
        txtYear.requestFocus();
    }

    @FXML
    void txtYearKeyPressed(KeyEvent event) {
        ValidateUtil.validateYear(Integer.parseInt(txtYear.getText()),txtYear);
    }

    @FXML
    void txtYearOnAction(ActionEvent event) {
        txtBuyerContact.requestFocus();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String model=txtModel.getText();
        String brand=txtBrand.getText();
        int year= Integer.parseInt(txtYear.getText());
        String contact=txtBuyerContact.getText();

        try {
            boolean isSaved = preOrderBO.savePreOrder(new PreOrderDto(preOrderBO.generateNextPreOrderId(), year, brand, model, String.valueOf(LocalDate.now()), 0, preOrderBO.getBuyerId(contact)));
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Pre Order Saved Successfully").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void btnNewBuyerOnAction(ActionEvent event) {
        Navigation.popupNavigation("newBuyer-form.fxml","Add New Buyer");
    }

}
