package lk.ijse.sellingLk.bo.custom;

import lk.ijse.sellingLk.bo.SuperBO;
import lk.ijse.sellingLk.model.SellerDto;

import java.sql.SQLException;
import java.util.List;

public interface SellerBO extends SuperBO {
    boolean saveSeller(final SellerDto dto) throws SQLException;

    boolean updateSeller(final SellerDto dto) throws SQLException;

    boolean deleteSeller(final String id) throws SQLException;

    List<SellerDto> getAllSeller() throws SQLException;

    String generateNextSellerId() throws SQLException;

    String splitId(String current);

    String getSellerName(String id) throws SQLException;

    SellerDto getSellerInfo(String contact) throws SQLException;

    String getEmail(String cusId) throws SQLException;

    String getUserId(String uname, String pword) throws SQLException;
}
