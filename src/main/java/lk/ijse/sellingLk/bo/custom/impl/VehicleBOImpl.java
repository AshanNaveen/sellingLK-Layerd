package lk.ijse.sellingLk.bo.custom.impl;

import lk.ijse.sellingLk.bo.custom.VehicleBO;
import lk.ijse.sellingLk.dao.DAOFactory;
import lk.ijse.sellingLk.dao.custom.VehicleDAO;
import lk.ijse.sellingLk.entity.Vehicle;
import lk.ijse.sellingLk.model.SearchDto;
import lk.ijse.sellingLk.model.VehicleDto;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleBOImpl implements VehicleBO {
    private VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);

    @Override
    public boolean saveVehicle(final VehicleDto vehicle) throws SQLException {
        return vehicleDAO.save(new Vehicle(vehicle.getId(), vehicle.getType(), vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getFuelType(), vehicle.getEnginCapacity(), vehicle.getMileage(), vehicle.getVehicleNumber(), vehicle.getPrice(), vehicle.getStatus()));
    }

    @Override
    public boolean updateVehicle(final VehicleDto vehicle) throws SQLException {
        return vehicleDAO.update(new Vehicle(vehicle.getId(), vehicle.getType(), vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getFuelType(), vehicle.getEnginCapacity(), vehicle.getMileage(), vehicle.getVehicleNumber(), vehicle.getPrice(), vehicle.getStatus()));
    }

    @Override
    public boolean deleteVehicle(final String id) throws SQLException {
        return vehicleDAO.delete(id);
    }

    @Override
    public boolean updateStatus(String id) throws SQLException {
        return vehicleDAO.updateStatus(id);
    }

    @Override
    public List<VehicleDto> getAllVehicle() throws SQLException {
        return vehicleDAO.getAll().stream().map(vehicle -> new VehicleDto(vehicle.getId(), vehicle.getType(), vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getFuelType(), vehicle.getEnginCapacity(), vehicle.getMileage(), vehicle.getVehicleNumber(), vehicle.getPrice(), vehicle.getStatus())).collect(Collectors.toList());
    }

    @Override
    public String getNextVehicleId() throws SQLException {
        return splitId(vehicleDAO.getLastID());
    }

    @Override
    public String splitId(String current) {
        if (current != null) {
            String[] split = current.split("V");
            int id = Integer.parseInt(split[1]);
            id++;
            if (9 >= id && id > 0) return "V00" + id;
            else if (99 >= id && id > 9) return "V0" + id;
            else if (id > 99) return "V" + id;
        }
        return "V001";
    }

    @Override

    public int getMinumunYear() throws SQLException {
        return vehicleDAO.getMinumunYear();
    }

    @Override
    public int getMaximumYear() throws SQLException {
        return vehicleDAO.getMaximumYear();
    }

    @Override
    public List<VehicleDto> search(SearchDto searchDto) throws SQLException {
        return vehicleDAO.search(searchDto).stream().map(vehicle -> new VehicleDto(vehicle.getId(), vehicle.getType(), vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getFuelType(), vehicle.getEnginCapacity(), vehicle.getMileage(), vehicle.getVehicleNumber(), vehicle.getPrice(), vehicle.getStatus())).collect(Collectors.toList());
    }

    @Override
    public List<String> getNotGetVehicle() throws SQLException {
        return vehicleDAO.getNotGetVehicle();
    }

    @Override
    public List<String> getNotSoldVehicle() throws SQLException {
        return vehicleDAO.getNotSoldVehicle();
    }

    @Override
    public VehicleDto getInfo(String id) throws SQLException {
        Vehicle vehicle = vehicleDAO.getInfo(id);
        return new VehicleDto(vehicle.getId(), vehicle.getType(), vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getFuelType(), vehicle.getEnginCapacity(), vehicle.getMileage(), vehicle.getVehicleNumber(), vehicle.getPrice(), vehicle.getStatus());
    }
}
