package lk.ijse.sellingLk.bo.custom.impl;

import lk.ijse.sellingLk.bo.custom.SignInBO;
import lk.ijse.sellingLk.dao.DAOFactory;
import lk.ijse.sellingLk.dao.custom.UserDAO;

import java.sql.SQLException;

public class SignInBOImpl implements SignInBO {
    private UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean searchUser(String username, String password) throws SQLException {
        return userDAO.searchUser(username,password)!=null;
    }
}
