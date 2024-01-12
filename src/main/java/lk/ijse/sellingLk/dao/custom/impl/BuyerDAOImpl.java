package lk.ijse.sellingLk.dao.custom.impl;

import lk.ijse.sellingLk.dao.custom.BuyerDAO;
import lk.ijse.sellingLk.entity.Buyer;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyerDAOImpl implements BuyerDAO {
    @Override
    public boolean save(final Buyer dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT into buyer(id,name,nic,email,address,phone,uId,loyalId) VALUES (?,?,?,?,?,?,?,?)",
                dto.getId(),
                dto.getName(),
                dto.getNic(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getUId(),
                dto.getLoyalId());
    }
    @Override
    public boolean update(final Buyer dto) throws SQLException {
        return CrudUtil.crudUtil("UPDATE buyer SET name=? , nic=? ,email=? , address=? , phone=? , uId=? , loyalId=? WHERE id=?",
                dto.getName(),
                dto.getEmail(),
                dto.getNic(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getUId(),
                dto.getLoyalId(),
                dto.getId());
    }
    @Override
    public boolean delete(final String id) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM buyer WHERE id=?", id);
    }

    @Override
    public List<Buyer> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM buyer");

        List<Buyer> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new Buyer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            ));
        }
        return dtoList;
    }
    @Override
    public String getLastID() throws SQLException {

        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM buyer ORDER BY id DESC LIMIT 1");
        String current = null;

        if (resultSet.next()) {
            current = resultSet.getString(1);
            return current;
        }
        return null;

    }
    @Override
    public Buyer getInfo(String contactOrIdOrName) throws SQLException {
        Buyer dto = null;
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM buyer WHERE phone=? OR id=? OR name=?", contactOrIdOrName,contactOrIdOrName,contactOrIdOrName);
        if (resultSet.next()) {
            dto= new Buyer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
        }
        return dto;
    }
}
