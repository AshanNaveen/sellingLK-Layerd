package lk.ijse.sellingLk.dao.custom;

import lk.ijse.sellingLk.dao.SuperDAO;
import lk.ijse.sellingLk.entity.WebVehicle;
import lk.ijse.sellingLk.model.WebVehicleDto;

import java.sql.SQLException;
import java.util.List;

public interface WebSearchDAO extends SuperDAO {
    boolean saveAll(List<WebVehicle> webVehicles) throws SQLException ;
    boolean save(WebVehicle webVehicleDto) throws SQLException ;

    List<WebVehicle> getAllSearch() throws SQLException ;
}
