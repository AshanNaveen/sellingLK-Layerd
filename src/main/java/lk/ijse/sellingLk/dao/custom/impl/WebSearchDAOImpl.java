package lk.ijse.sellingLk.dao.custom.impl;

import lk.ijse.sellingLk.dao.custom.WebSearchDAO;
import lk.ijse.sellingLk.entity.WebVehicle;
import lk.ijse.sellingLk.model.WebVehicleDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WebSearchDAOImpl implements WebSearchDAO {
    @Override
    public boolean saveAll(List<WebVehicle> webVehicleDtos) throws SQLException {
        CrudUtil.crudUtil("TRUNCATE TABLE websearch");
        for (WebVehicle dto : webVehicleDtos) {
            if (!save(dto)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean save(WebVehicle webVehicleDto) throws SQLException {
        return CrudUtil.crudUtil("INSERT INTO websearch VALUES(?,?,?,?,?,?,?,?,?,?)",
                webVehicleDto.getTitle(),
                webVehicleDto.getBrand(),
                webVehicleDto.getModel(),
                webVehicleDto.getContact(),
                webVehicleDto.getYear(),
                webVehicleDto.getPrice(),
                webVehicleDto.getFuelType(),
                webVehicleDto.getEngineCapacity(),
                webVehicleDto.getMileage(),
                webVehicleDto.getCity()
        );
    }

    @Override
    public List<WebVehicle> getAllSearch() throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM websearch");
        List<WebVehicle> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new WebVehicle(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10)
            ));
        }
        return list;
    }
}
