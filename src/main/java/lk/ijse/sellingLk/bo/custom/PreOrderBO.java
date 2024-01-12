package lk.ijse.sellingLk.bo.custom;

import lk.ijse.sellingLk.bo.SuperBO;
import lk.ijse.sellingLk.model.PreOrderDto;

import java.sql.SQLException;
import java.util.List;

public interface PreOrderBO extends SuperBO {
    boolean savePreOrder(PreOrderDto dto) throws SQLException;
    boolean updatePreOrder(PreOrderDto dto) throws SQLException;
    boolean deletePreOrder(String id) throws SQLException;
    List<PreOrderDto> getThisMonthPreOrders() throws SQLException;
    String generateNextPreOrderId() throws SQLException;
    String splitId(String current);
    List<PreOrderDto> getAllPreOrders() throws SQLException;

    String getBuyerId(String contact) throws SQLException;

    String getBuyerName(String name) throws SQLException;

    String getBuyerPhone(String contact) throws SQLException;
}
