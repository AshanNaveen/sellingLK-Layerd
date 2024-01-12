package lk.ijse.sellingLk.dao.custom.impl;

import lk.ijse.sellingLk.dao.custom.EmployeeDAO;
import lk.ijse.sellingLk.entity.Employee;
import lk.ijse.sellingLk.model.EmployeeDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(final Employee dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT into employee(" +
                        "id," +
                        "name," +
                        "email,"+
                        "address," +
                        "phone," +
                        "role," +
                        "uId" +
                        ") VALUES (?,?,?,?,?,?,?)",
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getRole(),
                dto.getUId());
    }
    @Override
    public boolean update(final Employee dto) throws SQLException {
        return CrudUtil.crudUtil("UPDATE employee SET " +
                        "name=?," +
                        "email=?,"+
                        "address=?," +
                        "phone=?," +
                        "role=?" +
                        " WHERE id=?",
                dto.getName(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getRole(),
                dto.getId());
    }
    @Override
    public boolean delete(final String id) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM employee WHERE id=?", id);
    }

    @Override
    public List<Employee> getAll()throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM employee");

        List<Employee> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new Employee(
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

        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM employee ORDER BY id DESC LIMIT 1");
        String current = null;

        if (resultSet.next()) {
            current = resultSet.getString(1);
            return current;
        }
        return null;

    }

    @Override
    public Employee getInfo(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM employee WHERE id=?",id);

        if (resultSet.next()) {
            return new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }
        return null;
    }

}
