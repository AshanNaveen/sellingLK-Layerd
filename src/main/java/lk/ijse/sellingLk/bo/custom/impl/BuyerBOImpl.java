package lk.ijse.sellingLk.bo.custom.impl;

import lk.ijse.sellingLk.bo.custom.BuyerBO;
import lk.ijse.sellingLk.dao.DAOFactory;
import lk.ijse.sellingLk.dao.custom.BuyerDAO;
import lk.ijse.sellingLk.dao.custom.LoyalIdDAO;
import lk.ijse.sellingLk.dao.custom.UserDAO;
import lk.ijse.sellingLk.entity.Buyer;
import lk.ijse.sellingLk.entity.LoyalId;
import lk.ijse.sellingLk.model.BuyerDto;
import lk.ijse.sellingLk.model.LoyalIdDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyerBOImpl implements BuyerBO {
    BuyerDAO buyerDAO = (BuyerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BUYER);
    LoyalIdDAO loyalIdDAO = (LoyalIdDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOYAID);
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean saveBuyer(final BuyerDto dto) throws SQLException {
        return buyerDAO.save(new Buyer(dto.getId(),dto.getName(), dto.getNic(), dto.getEmail(),dto.getAddress(),dto.getPhone(),dto.getUId(), dto.getLoyalId()));
    }

    @Override
    public boolean updateBuyer(final BuyerDto dto) throws SQLException {
        return buyerDAO.update(new Buyer(dto.getId(),dto.getName(), dto.getNic(), dto.getEmail(),dto.getAddress(),dto.getPhone(),dto.getUId(), dto.getLoyalId()));
    }

    @Override
    public boolean deleteBuyer(final String id) throws SQLException {
        return buyerDAO.delete(id);
    }

    @Override
    public List<BuyerDto> getAllBuyer() throws SQLException {
        List<Buyer> buyers = buyerDAO.getAll();
        List<BuyerDto> dtos = new ArrayList<>();
        for (Buyer buyer : buyers){
            dtos.add(new BuyerDto(buyer.getId(),buyer.getName(),buyer.getNic(),buyer.getEmail(),buyer.getAddress(),buyer.getPhone(),buyer.getUId(),buyer.getLoyalId()));
        }
        return dtos;
    }

    @Override
    public String generateNextBuyerId() throws SQLException {
        return splitId(buyerDAO.getLastID());
    }

    @Override
    public String splitId(String current) {
        if (current != null) {
            String[] split = current.split("B");
            int id = Integer.parseInt(split[1]);
            id++;
            if (9 >= id && id > 0) return "B00" + id;
            else if (99 >= id && id > 9) return "B0" + id;
            else if (id > 99) return String.valueOf(id);
        }
        return "B001";
    }

    @Override
    public BuyerDto getBuyerInfo(String contactOrIdOrName) throws SQLException {
        Buyer buyer = buyerDAO.getInfo(contactOrIdOrName);
        return new BuyerDto(buyer.getId(),buyer.getName(), buyer.getNic(), buyer.getEmail(),buyer.getAddress(),buyer.getPhone(),buyer.getUId(), buyer.getLoyalId());
    }

    @Override
    public List<LoyalIdDto> getAllLoyalIds() throws SQLException {
        List<LoyalId> idList = loyalIdDAO.getAll();
        List<LoyalIdDto> list = new ArrayList<>();
        for (LoyalId id : idList){
            list.add(new LoyalIdDto(id.getId(), id.getDiscount()));
        }
        return list;
    }

    @Override
    public String getUserId(String uname, String pword) throws SQLException {
        return userDAO.searchUser(uname,pword).getId();
    }

}
