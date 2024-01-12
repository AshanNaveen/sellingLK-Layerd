package lk.ijse.sellingLk.bo.custom;

import lk.ijse.sellingLk.bo.SuperBO;
import lk.ijse.sellingLk.model.SearchDto;
import lk.ijse.sellingLk.model.VehicleDto;
import lk.ijse.sellingLk.model.WebVehicleDto;

import java.sql.SQLException;
import java.util.List;

public interface SearchBO extends SuperBO {
    List<VehicleDto> search(SearchDto serchDto) throws SQLException;

    boolean save(List<WebVehicleDto> webVehicleDtos) throws SQLException ;
    List<WebVehicleDto> getAllSearch() throws SQLException;
}
