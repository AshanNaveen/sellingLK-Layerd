package lk.ijse.sellingLk.bo.custom;

import lk.ijse.sellingLk.bo.SuperBO;
import lk.ijse.sellingLk.model.BuyOrderDto;
import lk.ijse.sellingLk.model.PlaceOrderDto;
import lk.ijse.sellingLk.model.VehicleDto;

import java.sql.SQLException;

public interface BuyOrderBO extends SuperBO {
    boolean saveOrder(BuyOrderDto dto) throws SQLException;

    String generateNextOrderId() throws SQLException;

    String splitId(String current);
    public boolean saveBuyOrder(PlaceOrderDto placeOrderDto) throws SQLException;

    VehicleDto getVehicleInfo(String value) throws  SQLException;

    String getBuyerName(String cusId) throws SQLException;
}
