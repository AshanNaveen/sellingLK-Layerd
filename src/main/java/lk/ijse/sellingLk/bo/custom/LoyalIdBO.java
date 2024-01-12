package lk.ijse.sellingLk.bo.custom;

import lk.ijse.sellingLk.bo.SuperBO;
import lk.ijse.sellingLk.model.LoyalIdDto;

import java.sql.SQLException;
import java.util.List;

public interface LoyalIdBO extends SuperBO {
    List<LoyalIdDto> getAllLoyalId() throws SQLException ;
}
