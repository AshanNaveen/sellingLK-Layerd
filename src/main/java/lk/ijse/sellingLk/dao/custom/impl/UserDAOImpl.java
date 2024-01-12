package lk.ijse.sellingLk.dao.custom.impl;

import lk.ijse.sellingLk.dao.custom.UserDAO;
import lk.ijse.sellingLk.entity.User;
import lk.ijse.sellingLk.model.UserDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT into user(id,role,email,password,username,name) VALUES (?,?,?,?,?,?)",
                dto.getId(),
                dto.getRole(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getUsername(),
                dto.getName());
    }

    @Override
    public boolean update(User dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }
    @Override
    public User searchUser(String username, String password) throws SQLException {
        ResultSet rst = CrudUtil.crudUtil("SELECT * FROM user WHERE username=? AND password=?", username, password);
        if (rst.next()){
            return new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
                    );
        }
        return null;
    }
    @Override
    public List<User> getAll() throws SQLException {
        return null;
    }

    @Override
    public String getLastID() throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM user ORDER BY id DESC LIMIT 1");
        String current = null;

        if (resultSet.next()) {
            current = resultSet.getString(1);
            return current;
        }
        return null;

    }

    @Override
    public User getInfo(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM user WHERE id=?",id);
        if (resultSet.next()){
            return new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6));
        }
        return null;
    }
}
