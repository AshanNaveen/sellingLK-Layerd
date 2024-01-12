package lk.ijse.sellingLk.dao.custom.impl;

import lk.ijse.sellingLk.dao.custom.PreOrderDAO;
import lk.ijse.sellingLk.entity.PreOrder;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreOrderDAOImpl implements PreOrderDAO {
    @Override
    public boolean save(PreOrder dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO preOrder VALUES(?,?,?,?,?,?,?)",dto.getId(),dto.getYear(),dto.getBrand(),dto.getModel(),dto.getDate(),dto.getStatus(),dto.getBuyerId());
    }
    @Override
    public boolean update(PreOrder dto) throws SQLException {
        return CrudUtil.crudUtil("UPDATE preOrder SET year=?,brand=?,model=?,date=?,status=?,buyerId=? WHERE id=?",dto.getYear(),dto.getBrand(),dto.getModel(), Date.valueOf(dto.getDate()),dto.getStatus(),dto.getBuyerId(),dto.getId());
    }
    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM preOrder WHERE id=?",id);
    }
    @Override
    public List<PreOrder> getThisMonthPreOrders() throws SQLException {
        ResultSet resultSet=CrudUtil.crudUtil("SELECT * FROM preOrder WHERE month(date) = month(now())");
        List<PreOrder> list=new ArrayList<>();
        while (resultSet.next()){
            list.add(new PreOrder(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7)
            ));
        }
        return list;
    }
    @Override
    public String getLastID() throws SQLException {

        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM seller ORDER BY id DESC LIMIT 1");
        String current = null;

        if (resultSet.next()) {
            current = resultSet.getString(1);
            return (current);
        }
        return null;

    }

    @Override
    public PreOrder getInfo(String id) throws SQLException {
        return null;
    }
    @Override
    public List<PreOrder> getAll() throws SQLException {
        ResultSet resultSet=CrudUtil.crudUtil("SELECT * FROM preOrder");
        List<PreOrder> list=new ArrayList<>();
        while (resultSet.next()){
            list.add(new PreOrder(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getString(7)
            ));
        }
        return list;
    }
}
