package lk.ijse.sellingLk.bo.custom;

import lk.ijse.sellingLk.bo.SuperBO;
import lk.ijse.sellingLk.model.SearchDto;
import lk.ijse.sellingLk.model.VehicleDto;

import java.sql.SQLException;
import java.util.List;

public interface VehicleBO extends SuperBO {
    boolean saveVehicle(final VehicleDto dto) throws SQLException;

    boolean updateVehicle(final VehicleDto dto) throws SQLException;

    boolean deleteVehicle(final String id) throws SQLException;

    boolean updateStatus(String id) throws SQLException;

    List<VehicleDto> getAllVehicle() throws SQLException;

    String getNextVehicleId() throws SQLException;

    String splitId(String current);

    int getMinumunYear() throws SQLException;

    int getMaximumYear() throws SQLException;

    List<VehicleDto> search(SearchDto searchDto) throws SQLException;

    List<String> getNotGetVehicle() throws SQLException;

    List<String> getNotSoldVehicle() throws SQLException;

    VehicleDto getInfo(String id) throws SQLException;
}
