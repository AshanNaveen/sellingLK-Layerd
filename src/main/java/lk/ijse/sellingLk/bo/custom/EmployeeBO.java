package lk.ijse.sellingLk.bo.custom;

import lk.ijse.sellingLk.bo.SuperBO;
import lk.ijse.sellingLk.model.EmployeeDto;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    boolean saveEmployee(final EmployeeDto dto) throws SQLException;

    boolean updateEmployee(final EmployeeDto dto) throws SQLException;

    boolean deleteEmployee(final String id) throws SQLException;

    List<EmployeeDto> getAllEmployee() throws SQLException;

    String getNextEmpId() throws SQLException;

    String splitId(String current);

    String getUserId(String uname, String pword) throws SQLException;
}
