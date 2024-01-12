package lk.ijse.sellingLk.bo.custom;

import lk.ijse.sellingLk.bo.SuperBO;
import lk.ijse.sellingLk.model.UserDto;

import java.sql.SQLException;

public interface SignUpBO extends SuperBO {
    boolean saveUser(UserDto userDto) throws SQLException;

    String generateNextUserId() throws SQLException;

    public String splitId(String current);
}
