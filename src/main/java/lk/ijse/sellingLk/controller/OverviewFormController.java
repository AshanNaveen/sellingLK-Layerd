package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXSpinner;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lk.ijse.sellingLk.bo.BOFactory;
import lk.ijse.sellingLk.bo.custom.OverviewBO;
import lk.ijse.sellingLk.model.BuyerDto;
import lk.ijse.sellingLk.model.PreOrderDto;
import lk.ijse.sellingLk.model.SellerDto;
import lk.ijse.sellingLk.model.VehicleDto;

import java.sql.SQLException;
import java.util.List;

public class OverviewFormController {
    @FXML
    private VBox vBox;

    @FXML
    private LineChart<?, ?> grpSelling;

    @FXML
    private JFXSpinner loader;

    @FXML
    private Label lblPercentage;

    @FXML
    private Label lblVehicleCount;

    @FXML
    private Label lblBuyerCount;

    @FXML
    private Label lblSellerCount;

    private OverviewBO overviewBO = (OverviewBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.OVERVIEW);
    public void initialize() throws InterruptedException {
        loader.setProgress(-1);
        Thread.sleep(1000);
        loadVehicle();
        loadBuyer();
        loadSeller();
        loadLoader();
        loadGraph();
    }

    private void loadGraph() {

    }

    private void loadSeller() {
        try {
            List<SellerDto> allSeller = overviewBO.getAllSeller();
            lblSellerCount.setText(String.valueOf(allSeller.size()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadBuyer() {
        try {
            List<BuyerDto> allBuyer = overviewBO.getAllBuyer();
            lblBuyerCount.setText(String.valueOf(allBuyer.size()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadVehicle() {
        try {
            List<VehicleDto> allVehile = overviewBO.getAllVehicle();
            lblVehicleCount.setText(String.valueOf(allVehile.size()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadLoader() {
        try {
            List<PreOrderDto> dto =overviewBO.getAllPreOrders();
            int total = dto.size();
            if (total>0){
                int count = 0;
                for (PreOrderDto preOrderDto : dto) {
                    if (preOrderDto.getStatus() == 0) {
                        count++;
                    }
                }
                int percentage = (count * 100) / total;
                lblPercentage.setText(percentage + "%");
                loader.setProgress(percentage);
            }else {
                lblPercentage.setText("0" + "%");
                loader.setProgress(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
