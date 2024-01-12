package lk.ijse.sellingLk.dao.custom.impl;

import lk.ijse.sellingLk.dao.custom.SellOrderDAO;
import lk.ijse.sellingLk.entity.SellOrder;
import lk.ijse.sellingLk.model.SellOrderDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellOrderDAOImpl implements SellOrderDAO {
    @Override
    public boolean save(SellOrder dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO sellorder VALUES (?,?)",dto.getId(),dto.getBuyerId());
    }

    @Override
    public boolean update(SellOrder dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public List<SellOrder> getAll() throws SQLException {
        return null;
    }

    @Override
    public String getLastID() throws SQLException {

        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM sellOrder ORDER BY id DESC LIMIT 1");
        String current = null;

        if (resultSet.next()) {
            current = resultSet.getString(1);
            return (current);
        }
        return null;

    }

    @Override
    public SellOrder getInfo(String id) throws SQLException {
        return null;
    }
}
