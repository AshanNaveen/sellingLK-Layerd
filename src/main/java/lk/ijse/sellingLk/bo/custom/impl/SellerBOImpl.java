package lk.ijse.sellingLk.bo.custom.impl;

import lk.ijse.sellingLk.bo.custom.SellerBO;
import lk.ijse.sellingLk.dao.DAOFactory;
import lk.ijse.sellingLk.dao.custom.SellerDAO;
import lk.ijse.sellingLk.dao.custom.UserDAO;
import lk.ijse.sellingLk.entity.Seller;
import lk.ijse.sellingLk.model.SellerDto;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class SellerBOImpl implements SellerBO {
    private SellerDAO sellerDAO= (SellerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SELLER);
    private UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    public boolean saveSeller(final SellerDto dto) throws SQLException {
        return sellerDAO.save(new Seller(dto.getId(), dto.getName(), dto.getNic(), dto.getEmail(), dto.getAddress(), dto.getPhone(), dto.getUId()));
    }

    public boolean updateSeller(final SellerDto dto) throws SQLException {
        return sellerDAO.update(new Seller(dto.getId(), dto.getName(), dto.getNic(), dto.getEmail(), dto.getAddress(), dto.getPhone(), dto.getUId()));
    }

    public boolean deleteSeller(final String id) throws SQLException {
        return sellerDAO.delete(id);
    }


    public List<SellerDto> getAllSeller() throws SQLException {
        return sellerDAO.getAll().stream().map(seller -> new SellerDto(seller.getId(), seller.getName(), seller.getNic(), seller.getEmail(), seller.getAddress(), seller.getPhone(), seller.getUId())).collect(Collectors.toList());
    }

    public String generateNextSellerId() throws SQLException {
        return splitId(sellerDAO.getLastID());
    }

    public String splitId(String current) {
        if (current != null) {
            String[] split = current.split("S");
            int id = Integer.parseInt(split[1]);
            id++;
            if (id > 0) return "S00" + id;
            else if (id > 9) return "S0" + id;
            else if (id > 99) return String.valueOf(id);
        }
        return "S001";
    }

    public String getSellerName(String id) throws SQLException {
        return sellerDAO.getInfo(id).getName();
    }

    public SellerDto getSellerInfo(String contact) throws SQLException {
        Seller seller = sellerDAO.getInfo(contact);
        return new SellerDto(seller.getId(), seller.getName(), seller.getNic(), seller.getEmail(), seller.getAddress(), seller.getPhone(), seller.getUId());
    }

    public String getEmail(String cusId) throws SQLException {
        return sellerDAO.getInfo(cusId).getEmail();
    }

    @Override
    public String getUserId(String uname, String pword) throws SQLException {
        return userDAO.searchUser(uname,pword).getId();
    }
}
