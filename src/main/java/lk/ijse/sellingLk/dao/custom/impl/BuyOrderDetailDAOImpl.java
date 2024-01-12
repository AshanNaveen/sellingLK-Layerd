package lk.ijse.sellingLk.dao.custom.impl;

import lk.ijse.sellingLk.dao.custom.BuyOrderDetailDAO;
import lk.ijse.sellingLk.entity.BuyOrderDetail;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class BuyOrderDetailDAOImpl implements BuyOrderDetailDAO {
    @Override
    public boolean save(BuyOrderDetail dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO buyorderdetail Values (?,?,?)",dto.getDate(),dto.getOrderId(),dto.getItemId());
    }

    @Override
    public boolean update(BuyOrderDetail dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public List<BuyOrderDetail> getAll() throws SQLException {
        return null;
    }

    @Override
    public String getLastID() throws SQLException {
        return null;
    }

    @Override
    public BuyOrderDetail getInfo(String id) throws SQLException {
        return null;
    }
}
