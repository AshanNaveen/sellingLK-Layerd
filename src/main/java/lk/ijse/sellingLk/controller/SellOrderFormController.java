package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lk.ijse.sellingLk.bo.BOFactory;
import lk.ijse.sellingLk.bo.custom.SellOrderBO;
import lk.ijse.sellingLk.bo.custom.impl.*;
import lk.ijse.sellingLk.controller.barController.CartBarController;
import lk.ijse.sellingLk.db.DbConnection;
import lk.ijse.sellingLk.model.BuyerDto;
import lk.ijse.sellingLk.model.PlaceOrderDto;
import lk.ijse.sellingLk.model.VehicleDto;
import lk.ijse.sellingLk.util.DateTimeUtil;
import lk.ijse.sellingLk.util.EmailUtil;
import lk.ijse.sellingLk.util.Navigation;
import lk.ijse.sellingLk.util.ValidateUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SellOrderFormController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private JFXComboBox<String> cmbItemId;

    @FXML
    private VBox vBox;

    @FXML
    private Text lblTime;

    @FXML
    private Text txtTotalPrice;

    @FXML
    private Text lblDate;

    @FXML
    private JFXTextField txtBuyerName;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private Label lblWarning;

    @FXML
    private JFXTextField txtBuyerContact;


    private List<String> cart = new ArrayList<>();
    private int netTotal = 0;

    VehicleDto dto=null;

    private SellOrderBO sellOrderBO = (SellOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SELLORDER);

    public void initialize() {
        loadVehicleIds();
        vBox.getChildren().clear();
        time();
    }

    private void time() {
//        lblDate.setText(LocalDate.now().toString());
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            lblTime.setText(DateTimeUtil.timeNow());
            lblDate.setText(DateTimeUtil.dateNow());
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();
    }

    private void loadVehicleIds() {
        try {
            List<String> allVehicle = sellOrderBO.getNotSoldVehicle();
            ObservableList<String> vehicleIds = FXCollections.observableArrayList();
            for (String id : allVehicle) {
                vehicleIds.add(id);
            }
            cmbItemId.setItems(vehicleIds);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnItemIdOnAction(ActionEvent event) {
        try {
            dto =sellOrderBO.getVehicleInfo(cmbItemId.getValue());
            txtDescription.setText(dto.getBrand() + " " + dto.getModel() + " " + dto.getYear());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

        String vehicleId = cmbItemId.getValue();
        cart.add(vehicleId);

        calculateTotal();
        String strPrice = String.valueOf(netTotal);
        String[] split = strPrice.split("(?<=\\G.{" + 1 + "})");
        String txt = "";
        int round = 0;
        for (int i = split.length - 1; i > -1; i--) {
            txt = split[i] + txt;
            round += 1;
            if (round == 3) {
                txt = " " + txt;
                round = 0;
            }
        }
        txtTotalPrice.setText("Rs. " + txt + ".00");
        addRow();
        cmbItemId.requestFocus();
        txtDescription.clear();
    }

    private void calculateTotal() {
        cart.forEach(id -> {
            try {
                VehicleDto vehicleInfo = sellOrderBO.getVehicleInfo(id);
                netTotal += vehicleInfo.getPrice();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }


    private void addRow() {
        try {
            FXMLLoader loader = new FXMLLoader(CartBarController.class.getResource("/bar/cartBar.fxml"));
            Parent root = loader.load();
            CartBarController controller = loader.getController();
            controller.setData(dto);
            System.out.println("added row to cart");
            vBox.getChildren().add(root);
        } catch (IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void btnBuyOrderOnAction(ActionEvent event) {
        try {
            Pane root = FXMLLoader.load(this.getClass().getResource("/view/buyOrder-form.fxml"));
            mainPane.getChildren().clear();
            mainPane.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnNewBuyerOnAction(ActionEvent event) {
        Navigation.popupNavigation("newBuyer-form.fxml","Add New Buyer");
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String id = null;
        Date date = Date.valueOf(LocalDate.now());
        Time time = Time.valueOf(LocalTime.now());
        String contact = txtBuyerContact.getText();
        try {
            id = sellOrderBO.generateNextOrderId();
            System.out.println("Sell order ID : " + id);
            BuyerDto buyerDto = new BuyerBOImpl().getBuyerInfo(contact);

            var pdto = new PlaceOrderDto(
                    id,
                    buyerDto.getId(),
                    cart,
                    netTotal,
                    date,
                    time
            );
            boolean isSaved =sellOrderBO.saveSellOrder(pdto);
            if (isSaved) {
                generateReport(pdto);
                String email = sellOrderBO.getBuyerEmail(pdto.getCusId());
                //sendMail("Thank you for choosing our service !","Your Order Is Successfully .. ",email);
                cart.clear();
                vBox.getChildren().clear();
                loadVehicleIds();
                txtBuyerContact.requestFocus();
                txtBuyerContact.clear();
                txtDescription.clear();
                txtTotalPrice.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private boolean sendMail(String title, String message, String gmail) {
        try {
            new EmailUtil().sendMail(title, message, gmail);
            return true;
        } catch (IOException | MessagingException | GeneralSecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void generateReport(PlaceOrderDto placeOrderDto) throws SQLException {
        String tot = String.valueOf(netTotal);
        String userName = sellOrderBO.getUserName(SignInFormController.uname, SignInFormController.pword);

        HashMap map = new HashMap();
        map.put("id", placeOrderDto.getOrderId());
        map.put("Total", tot);
        map.put("discount", "1000");
        map.put("invoiceNo", "1000");
        map.put("user", userName);

        try {
            InputStream stream = getClass().getResourceAsStream("/reports/buyerInvoice.jrxml");
            JasperDesign load = JRXmlLoader.load(stream);
            JasperReport report = JasperCompileManager.compileReport(load);
            JasperPrint print = JasperFillManager.fillReport(report, map, DbConnection.getInstance().getConnection());
            JasperViewer.viewReport(print, false);
            /*File pdf = File.createTempFile("output.", ".pdf");
            JasperExportManager.exportReportToPdfStream(print, new FileOutputStream(pdf));*/
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtBuyerContactKeyPressed(KeyEvent event) {
        ValidateUtil.validatePhone(txtBuyerContact.getText(), txtBuyerContact);
        if (lblWarning.isVisible() && txtBuyerContact.getText().length() < 9) {
            lblWarning.setVisible(false);
        }
    }

    @FXML
    void txtBuyerContactOnAction(ActionEvent event) {
        try {
            String name = sellOrderBO.getBuyerName(txtBuyerContact.getText());

            if (name != null) {
                txtBuyerName.setText(name);
                cmbItemId.requestFocus();
                lblWarning.setVisible(false);
            } else {
                lblWarning.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
