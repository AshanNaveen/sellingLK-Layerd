package lk.ijse.sellingLk.bo.custom;

import lk.ijse.sellingLk.bo.SuperBO;
import lk.ijse.sellingLk.model.BuyerDto;
import lk.ijse.sellingLk.model.PreOrderDto;
import lk.ijse.sellingLk.model.SellerDto;
import lk.ijse.sellingLk.model.VehicleDto;

import java.sql.SQLException;
import java.util.List;

public interface OverviewBO extends SuperBO {
    List<SellerDto> getAllSeller() throws SQLException;

    List<BuyerDto> getAllBuyer() throws SQLException;

    List<VehicleDto> getAllVehicle() throws SQLException;

    List<PreOrderDto> getAllPreOrders() throws SQLException;
}
