package lk.ijse.sellingLk.dao.custom.impl;

import lk.ijse.sellingLk.dao.custom.VehicleDAO;
import lk.ijse.sellingLk.entity.Vehicle;
import lk.ijse.sellingLk.model.SearchDto;
import lk.ijse.sellingLk.model.VehicleDto;
import lk.ijse.sellingLk.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public boolean save(final Vehicle dto) throws SQLException {
        return CrudUtil.crudUtil("INSERT into vehicle(" +
                        "id,brand,model,type,year,fuelType,engineCapacity,mileage,vehicleNumber,price,status) VALUES (?,?,?,?,?,?,?,?,?,?,?)",
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getType(),
                dto.getYear(),
                dto.getFuelType(),
                dto.getEnginCapacity(),
                dto.getMileage(),
                dto.getVehicleNumber(),
                dto.getPrice(),
                dto.getStatus());
    }
    @Override
    public boolean update(final Vehicle dto) throws SQLException {
        return CrudUtil.crudUtil("UPDATE vehicle SET " +
                        "brand=?,model=?,type=?,year=?,fuelType=?,engineCapacity=?,mileage=?,vehicleNumber=?,price=?,status=? WHERE id=?",
                dto.getBrand(),
                dto.getModel(),
                dto.getType(),
                dto.getYear(),
                dto.getFuelType(),
                dto.getEnginCapacity(),
                dto.getMileage(),
                dto.getVehicleNumber(),
                dto.getPrice(),
                dto.getStatus());
    }
    @Override
    public boolean delete(final String id) throws SQLException {
        return CrudUtil.crudUtil("DELETE FROM vehicle WHERE id=?", id);
    }
    @Override
    public boolean updateStatus(String id) throws SQLException {
        return CrudUtil.crudUtil("UPDATE vehicle SET status='Sold' WHERE id=?", id);
    }
    @Override
    public List<Vehicle> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM vehicle");

        List<Vehicle> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new Vehicle(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getInt(7),
                    resultSet.getInt(8),
                    resultSet.getString(9),
                    resultSet.getInt(10),
                    resultSet.getString(11)
            ));
        }
        return dtoList;
    }
    @Override
    public String getLastID() throws SQLException {

        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM vehicle ORDER BY id DESC LIMIT 1");
        String current = null;

        if (resultSet.next()) {
            current = resultSet.getString(1);
            return (current);
        }
        return null;

    }
    @Override
    public int getMinumunYear() throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT *  FROM vehicle ORDER BY year LIMIT 1");
        while (resultSet.next()) {
            return resultSet.getInt(5);
        }
        return 2000;
    }

    @Override
    public int getMaximumYear() throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT *  FROM vehicle ORDER BY year DESC LIMIT 1");
        while (resultSet.next()) {
            return resultSet.getInt(5);
        }
        return 2000;
    }
    @Override
    public List<Vehicle> search(SearchDto searchDto) throws SQLException {
        List<Vehicle> list = new ArrayList<>();
        String brand = searchDto.getBrand();
        String model = searchDto.getModel();

        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM vehicle WHERE brand LIKE \'%" + brand + "%\' OR model LIKE \'%" + model + "%\' AND ?>year AND year>? AND fuelType LIKE \'%" + searchDto.getFuelType() + "%\' AND ?>price AND price>?", searchDto.getYearMax(), searchDto.getYearMin(), searchDto.getPriceMax(), searchDto.getPriceMin());
        while (resultSet.next()) {
            if (resultSet.getString(10).equals("On Hand")) {
                list.add(new Vehicle(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getString(9),
                        resultSet.getInt(10),
                        resultSet.getString(11)
                ));
            }
        }
        if (list.size() > 1) {
            return list;
        }


        resultSet = CrudUtil.crudUtil("SELECT * FROM vehicle WHERE brand LIKE \'%" + brand + "%\' AND model LIKE \'%" + model + "%\'");
        l1:
        while (resultSet.next()) {
            if (resultSet.getString(10).equals("On Hand")) {
                for (Vehicle dto : list) {
                    if (dto.getId().equals(resultSet.getString(1))) {
                        continue l1;
                    }
                }

                list.add(new Vehicle(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8),
                        resultSet.getString(9),
                        resultSet.getInt(10),
                        resultSet.getString(11)
                ));
            }
        }
        return list;
    }
    @Override
    public Vehicle getInfo(String id) throws SQLException {
        ResultSet resultSet = CrudUtil.crudUtil("SELECT * FROM vehicle WHERE id=?", id);

        while (resultSet.next()) {
            return new Vehicle(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getInt(7),
                    resultSet.getInt(8),
                    resultSet.getString(9),
                    resultSet.getInt(10),
                    resultSet.getString(11)
            );
        }
        return null;
    }
    @Override
    //this method can return vehicles not in buy orders
    public List<String> getNotGetVehicle() throws SQLException {
        List<String> list = new ArrayList<>();
        ResultSet resultSet = CrudUtil.crudUtil("SELECT id FROM vehicle WHERE id NOT IN (SELECT vehicleId FROM buyorderdetail)");
        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }
    @Override
    public List<String> getNotSoldVehicle() throws SQLException {
        List<String> list = new ArrayList<>();
        ResultSet resultSet = CrudUtil.crudUtil("SELECT id FROM vehicle WHERE id NOT IN (SELECT vehicleId FROM sellorderdetail)");
        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
