package lk.ijse.sellingLk.bo.custom.impl;

import lk.ijse.sellingLk.bo.custom.SearchBO;
import lk.ijse.sellingLk.dao.DAOFactory;
import lk.ijse.sellingLk.dao.custom.VehicleDAO;
import lk.ijse.sellingLk.dao.custom.WebSearchDAO;
import lk.ijse.sellingLk.entity.WebVehicle;
import lk.ijse.sellingLk.model.SearchDto;
import lk.ijse.sellingLk.model.VehicleDto;
import lk.ijse.sellingLk.model.WebVehicleDto;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class SearchBOImpl implements SearchBO {
    private VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);
    private WebSearchDAO webSearchDAO = (WebSearchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.WEBSEARCH);

    @Override
    public boolean save(List<WebVehicleDto> webVehicleDtos) throws SQLException {
        return webSearchDAO.saveAll(webVehicleDtos.stream().map(webVehicleDto -> new WebVehicle(webVehicleDto.getTitle(), webVehicleDto.getBrand(), webVehicleDto.getModel(), webVehicleDto.getContact(), webVehicleDto.getYear(), webVehicleDto.getPrice(), webVehicleDto.getFuelType(), webVehicleDto.getEngineCapacity(), webVehicleDto.getMileage(), webVehicleDto.getCity())).collect(Collectors.toList()));
    }

    @Override
    public List<WebVehicleDto> getAllSearch() throws SQLException {
        return webSearchDAO.getAllSearch().stream().map(webVehicle -> new WebVehicleDto(webVehicle.getTitle(), webVehicle.getBrand(), webVehicle.getModel(), webVehicle.getContact(), webVehicle.getYear(), webVehicle.getPrice(), webVehicle.getFuelType(), webVehicle.getEngineCapacity(), webVehicle.getMileage(), webVehicle.getCity())).collect(Collectors.toList());
    }

    @Override
    public List<VehicleDto> search(SearchDto serchDto) throws SQLException {
        return vehicleDAO.search(serchDto).stream().map(vehicle -> new VehicleDto(vehicle.getId(), vehicle.getType(), vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getFuelType(), vehicle.getEnginCapacity(), vehicle.getMileage(), vehicle.getVehicleNumber(), vehicle.getPrice(), vehicle.getStatus())).collect(Collectors.toList());
    }
}
