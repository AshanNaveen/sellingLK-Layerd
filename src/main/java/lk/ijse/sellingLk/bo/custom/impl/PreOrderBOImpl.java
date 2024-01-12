package lk.ijse.sellingLk.bo.custom.impl;

import lk.ijse.sellingLk.bo.custom.PreOrderBO;
import lk.ijse.sellingLk.dao.DAOFactory;
import lk.ijse.sellingLk.dao.custom.BuyerDAO;
import lk.ijse.sellingLk.dao.custom.PreOrderDAO;
import lk.ijse.sellingLk.entity.PreOrder;
import lk.ijse.sellingLk.model.PreOrderDto;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class PreOrderBOImpl implements PreOrderBO {
    private PreOrderDAO preOrderDAO = (PreOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PREORDER);
    private BuyerDAO buyerDAO = (BuyerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BUYER);
    public boolean savePreOrder(PreOrderDto dto) throws SQLException {
        return preOrderDAO.save(new PreOrder(dto.getId(), dto.getYear(), dto.getBrand(), dto.getModel(), dto.getDate(), dto.getStatus(), dto.getBuyerId()));
    }
    public boolean updatePreOrder(PreOrderDto dto) throws SQLException {
        return preOrderDAO.update(new PreOrder(dto.getId(), dto.getYear(), dto.getBrand(), dto.getModel(), dto.getDate(), dto.getStatus(), dto.getBuyerId()));
    }
    public boolean deletePreOrder(String id) throws SQLException {
        return preOrderDAO.delete(id);
    }
    public List<PreOrderDto> getThisMonthPreOrders() throws SQLException {
        return preOrderDAO.getThisMonthPreOrders().stream().map(preOrder -> new PreOrderDto(preOrder.getId(), preOrder.getYear(), preOrder.getBrand(), preOrder.getModel(), preOrder.getDate(), preOrder.getStatus(), preOrder.getBuyerId())).collect(Collectors.toList());
    }
    public String generateNextPreOrderId() throws SQLException {
        return splitId(preOrderDAO.getLastID());

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

    public List<PreOrderDto> getAllPreOrders() throws SQLException {
        return preOrderDAO.getAll().stream().map(preOrder ->new PreOrderDto(preOrder.getId(), preOrder.getYear(), preOrder.getBrand(), preOrder.getModel(), preOrder.getDate(), preOrder.getStatus(), preOrder.getBuyerId())).collect(Collectors.toList());
    }

    @Override
    public String getBuyerId(String contact) throws SQLException {
        return buyerDAO.getInfo(contact).getId();
    }

    @Override
    public String getBuyerName(String contact) throws SQLException {
        return buyerDAO.getInfo(contact).getName();
    }

    @Override
    public String getBuyerPhone(String name) throws SQLException {
        return buyerDAO.getInfo(name).getPhone();
    }
}
