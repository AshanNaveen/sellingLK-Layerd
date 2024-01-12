package lk.ijse.sellingLk.bo.custom.impl;

import lk.ijse.sellingLk.bo.custom.EmployeeBO;
import lk.ijse.sellingLk.dao.DAOFactory;
import lk.ijse.sellingLk.dao.custom.EmployeeDAO;
import lk.ijse.sellingLk.dao.custom.UserDAO;
import lk.ijse.sellingLk.entity.Employee;
import lk.ijse.sellingLk.model.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean saveEmployee(final EmployeeDto dto) throws SQLException {
        return employeeDAO.save(new Employee(dto.getId(),dto.getName(),dto.getEmail(),dto.getAddress(),dto.getPhone(),dto.getRole(),dto.getUId()));
    }

    @Override
    public boolean updateEmployee(final EmployeeDto dto) throws SQLException {
        return employeeDAO.update(new Employee(dto.getId(),dto.getName(),dto.getEmail(),dto.getAddress(),dto.getPhone(),dto.getRole(),dto.getUId()));
    }

    @Override
    public boolean deleteEmployee(final String id) throws SQLException {
        return employeeDAO.delete(id);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() throws SQLException {
        List<Employee> employees = employeeDAO.getAll();
        List<EmployeeDto> list=new ArrayList<>();
        for (Employee emp:employees){
            list.add(new EmployeeDto(emp.getId(),emp.getName(),emp.getEmail(),emp.getAddress(),emp.getPhone(),emp.getRole(),emp.getUId()));
        }
        return list;
    }

    @Override
    public String getNextEmpId() throws SQLException {
        return splitId(employeeDAO.getLastID());
    }

    @Override
    public String splitId(String current) {
        if (current != null) {
            String[] split = current.split("E");
            int id = Integer.parseInt(split[1]);
            id++;
            if (9 > id && id > 0) return "E00" + id;
            else if (99 > id && id > 9) return "E0" + id;
            else if (id > 99) return String.valueOf(id);
        }
        return "E001";
    }

    @Override
    public String getUserId(String uname, String pword) throws SQLException {
        return userDAO.searchUser(uname,pword).getId();
    }
}
