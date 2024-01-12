package lk.ijse.sellingLk.dao.custom.impl;

import lk.ijse.sellingLk.dao.custom.LoyalIdDAO;
import lk.ijse.sellingLk.entity.LoyalId;
import lk.ijse.sellingLk.model.LoyalIdDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoyalIdDAOImpl implements LoyalIdDAO {
    @Override
    public boolean save(LoyalId dto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(LoyalId dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.crudUtil("DELETE loyalid WHERE id=?",id);
    }

    @Override
    public List<LoyalId> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM loyalid");
        List<LoyalId> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new LoyalId(
                    resultSet.getString(1),
                    resultSet.getInt(2)
            ));
        }
        return list;
    }

    @Override
    public String getLastID() throws SQLException {
        return null;
    }

    @Override
    public LoyalId getInfo(String id) throws SQLException {
        return null;
    }
}
