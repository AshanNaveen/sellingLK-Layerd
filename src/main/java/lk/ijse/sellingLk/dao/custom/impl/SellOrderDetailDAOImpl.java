package lk.ijse.sellingLk.dao.custom.impl;

import lk.ijse.sellingLk.dao.custom.SellOrderDetailDAO;
import lk.ijse.sellingLk.entity.SellOrderDetail;
import lk.ijse.sellingLk.model.SellOrderDetailDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class SellOrderDetailDAOImpl implements SellOrderDetailDAO {
    @Override
    public boolean save(SellOrderDetail dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO sellOrderDetail Values (?,?,?)",dto.getDate(),dto.getOrderId(),dto.getVehicleId());
    }

    @Override
    public boolean update(SellOrderDetail dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public List<SellOrderDetail> getAll() throws SQLException {
        return null;
    }

    @Override
    public String getLastID() throws SQLException {
        return null;
    }

    @Override
    public SellOrderDetail getInfo(String id) throws SQLException {
        return null;
    }
}
