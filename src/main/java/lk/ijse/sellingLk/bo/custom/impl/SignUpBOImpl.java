package lk.ijse.sellingLk.bo.custom.impl;

import lk.ijse.sellingLk.bo.custom.SignUpBO;
import lk.ijse.sellingLk.dao.DAOFactory;
import lk.ijse.sellingLk.dao.custom.UserDAO;
import lk.ijse.sellingLk.entity.User;
import lk.ijse.sellingLk.model.UserDto;

import java.sql.SQLException;

public class SignUpBOImpl implements SignUpBO {
    private UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean saveUser(UserDto userDto) throws SQLException {
        return userDAO.save(new User(userDto.getId(),userDto.getRole(),userDto.getEmail(),userDto.getPassword(),userDto.getUsername(),userDto.getName()));
    }

    @Override
    public String generateNextUserId() throws SQLException {
        return splitId(userDAO.getLastID());
    }

    public String splitId(String current) {
        if (current != null) {
            String[] split = current.split("U");
            int id = Integer.parseInt(split[1]);
            id++;
            if (9 >= id && id > 0) return "U00" + id;
            else if (99 >= id && id > 9) return "U0" + id;
            else if (id > 99) return String.valueOf(id);
        }
        return "U001";
    }
}
