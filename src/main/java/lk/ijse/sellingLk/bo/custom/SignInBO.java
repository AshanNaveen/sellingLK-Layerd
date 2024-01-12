package lk.ijse.sellingLk.bo.custom;

import lk.ijse.sellingLk.bo.SuperBO;

import java.sql.SQLException;

public interface SignInBO extends SuperBO {
    boolean searchUser(String username, String password) throws SQLException;
}
