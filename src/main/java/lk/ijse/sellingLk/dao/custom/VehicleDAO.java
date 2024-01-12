package lk.ijse.sellingLk.dao.custom;

import lk.ijse.sellingLk.dao.CrudDAO;
import lk.ijse.sellingLk.entity.Vehicle;
import lk.ijse.sellingLk.model.SearchDto;
import lk.ijse.sellingLk.model.VehicleDto;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDAO extends CrudDAO<Vehicle> {
    boolean updateStatus(String list) throws SQLException;
    List<String> getNotSoldVehicle() throws SQLException;
    List<String> getNotGetVehicle() throws SQLException;
    List<Vehicle> search(SearchDto searchDto) throws SQLException;
    int getMaximumYear() throws SQLException;
    int getMinumunYear() throws SQLException;
}
