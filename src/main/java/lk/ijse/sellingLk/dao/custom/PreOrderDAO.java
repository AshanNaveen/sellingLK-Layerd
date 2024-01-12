package lk.ijse.sellingLk.dao.custom;

import lk.ijse.sellingLk.dao.CrudDAO;
import lk.ijse.sellingLk.entity.PreOrder;
import lk.ijse.sellingLk.model.PreOrderDto;

import java.sql.SQLException;
import java.util.List;

public interface PreOrderDAO extends CrudDAO<PreOrder> {
    List<PreOrder> getThisMonthPreOrders() throws SQLException;
}
