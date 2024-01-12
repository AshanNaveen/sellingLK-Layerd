package lk.ijse.sellingLk.bo.custom;

import lk.ijse.sellingLk.bo.SuperBO;
import lk.ijse.sellingLk.model.PlaceOrderDto;
import lk.ijse.sellingLk.model.VehicleDto;

import java.sql.SQLException;
import java.util.List;

public interface SellOrderBO extends SuperBO {
    boolean saveSellOrder(PlaceOrderDto placeOrderDto) throws SQLException;
    String generateNextOrderId() throws SQLException;
    String splitId(String current);

    List<String> getNotSoldVehicle()throws SQLException;

    VehicleDto getVehicleInfo(String value) throws SQLException;

    String  getBuyerEmail(String cusId) throws SQLException;

    String getBuyerName(String text)throws SQLException;

    String getUserName(String uname, String pword) throws SQLException;
}
