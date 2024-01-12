package lk.ijse.sellingLk.dao.custom.impl;

import lk.ijse.sellingLk.dao.custom.SellerDAO;
import lk.ijse.sellingLk.entity.Seller;
import lk.ijse.sellingLk.model.SellerDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDAOImpl implements SellerDAO {
    @Override
    public boolean save(final Seller dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT into seller(id,name,nic,email,address,phone,uId) VALUES (?,?,?,?,?,?,?)",
                dto.getId(),
                dto.getName(),
                dto.getNic(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getUId());
    }
    @Override
    public boolean update(final Seller dto) throws SQLException {
        return CrudUtil.crudUtil("UPDATE seller SET name=? ,nic=?, email=? , address=? , phone=? , uId=?  WHERE id=?",
                dto.getName(),
                dto.getNic(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getUId(),
                dto.getId());
    }
    @Override
    public boolean delete(final String id) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM seller WHERE id=?", id);
    }
    @Override
    public List<Seller> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM seller");

        List<Seller> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new Seller(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return dtoList;
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
    public Seller getInfo(String contact) throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM seller WHERE phone=?",contact);
        if (resultSet.next())return new Seller(resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7));
        return null;
    }
}
