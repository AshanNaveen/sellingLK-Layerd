package lk.ijse.sellingLk.dao.custom.impl;

import lk.ijse.sellingLk.dao.custom.PaymentDAO;
import lk.ijse.sellingLk.entity.Payment;
import lk.ijse.sellingLk.model.PaymentDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public String getLastID() throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM payment ORDER BY id DESC LIMIT 1");
        String current = null;

        if (resultSet.next()) {
            current = resultSet.getString(1);
            return current;
        }
        return null;
    }

    @Override
    public Payment getInfo(String id) throws SQLException {
        return null;
    }
    @Override
    public boolean save(Payment payment) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO payment VALUES(?,?,?,?,?,?)",payment.getId(),payment.getAmount(),payment.getDate(),payment.getSellerId(),payment.getBuyerId(),payment.getUId());
    }

    @Override
    public boolean update(Payment dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public List<Payment> getAll() throws SQLException {
        return null;
    }
}
