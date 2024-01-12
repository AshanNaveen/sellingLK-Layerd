package lk.ijse.sellingLk.dao.custom;

import lk.ijse.sellingLk.dao.CrudDAO;
import lk.ijse.sellingLk.entity.User;
import lk.ijse.sellingLk.model.UserDto;
import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {
    public User searchUser(String username, String password) throws SQLException;
}
