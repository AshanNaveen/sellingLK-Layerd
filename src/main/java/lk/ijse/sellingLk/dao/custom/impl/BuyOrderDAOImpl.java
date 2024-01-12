package lk.ijse.sellingLk.dao.custom.impl;

import lk.ijse.sellingLk.dao.custom.BuyOrderDAO;
import lk.ijse.sellingLk.entity.BuyOrder;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BuyOrderDAOImpl implements BuyOrderDAO {
    @Override
    public boolean save(BuyOrder dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO buyorder VALUES (?,?)", dto.getId(), dto.getSellerId());
    }
    @Override
    public boolean update(BuyOrder dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public List<BuyOrder> getAll() throws SQLException {
        return null;
    }
    @Override
    public BuyOrder getInfo(String id) throws SQLException {
        return null;
    }
    @Override
    public String getLastID() throws SQLException {

        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM sellOrder ORDER BY id DESC LIMIT 1");
        String current = null;

        if (resultSet.next()) {
            current = resultSet.getString(1);
            return current;
        }
        return null;
    }
}
