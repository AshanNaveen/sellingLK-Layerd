package lk.ijse.sellingLk.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T> extends SuperDAO{
    boolean save(final T dto) throws SQLException;
    boolean update(final T dto) throws SQLException;
    boolean delete(final String id) throws SQLException;
    List<T> getAll() throws SQLException;
    String getLastID() throws SQLException;
    T getInfo(String id) throws SQLException;
}
