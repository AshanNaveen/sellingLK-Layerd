package lk.ijse.sellingLk.bo.custom;

import lk.ijse.sellingLk.bo.SuperBO;
import lk.ijse.sellingLk.model.BuyerDto;
import lk.ijse.sellingLk.model.LoyalIdDto;

import java.sql.SQLException;
import java.util.List;

public interface BuyerBO extends SuperBO {
    boolean saveBuyer(final BuyerDto dto) throws SQLException;

    boolean updateBuyer(final BuyerDto dto) throws SQLException;

    boolean deleteBuyer(final String id) throws SQLException;


    List<BuyerDto> getAllBuyer() throws SQLException;

    String generateNextBuyerId() throws SQLException;

    String splitId(String current);

    BuyerDto getBuyerInfo(String contactOrIdOrName) throws SQLException;

    List<LoyalIdDto> getAllLoyalIds() throws SQLException;

    String getUserId(String uname, String pword) throws SQLException;
}
