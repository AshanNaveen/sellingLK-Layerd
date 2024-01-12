package lk.ijse.sellingLk.bo.custom.impl;

import lk.ijse.sellingLk.bo.custom.LoyalIdBO;
import lk.ijse.sellingLk.dao.DAOFactory;
import lk.ijse.sellingLk.dao.custom.LoyalIdDAO;
import lk.ijse.sellingLk.entity.LoyalId;
import lk.ijse.sellingLk.model.LoyalIdDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoyalIdBOImpl implements LoyalIdBO {
    LoyalIdDAO loyalIdDAO = (LoyalIdDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOYAID);
    public List<LoyalIdDto> getAllLoyalId() throws SQLException {
        return loyalIdDAO.getAll().stream().map(loyalId -> new LoyalIdDto(loyalId.getId(), loyalId.getDiscount())).collect(Collectors.toList());
    }
}
