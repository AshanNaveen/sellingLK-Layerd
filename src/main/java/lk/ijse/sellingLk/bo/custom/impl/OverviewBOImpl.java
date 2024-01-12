package lk.ijse.sellingLk.bo.custom.impl;

import lk.ijse.sellingLk.bo.custom.OverviewBO;
import lk.ijse.sellingLk.dao.DAOFactory;
import lk.ijse.sellingLk.dao.custom.BuyerDAO;
import lk.ijse.sellingLk.dao.custom.PreOrderDAO;
import lk.ijse.sellingLk.dao.custom.SellerDAO;
import lk.ijse.sellingLk.dao.custom.VehicleDAO;
import lk.ijse.sellingLk.entity.Seller;
import lk.ijse.sellingLk.model.BuyerDto;
import lk.ijse.sellingLk.model.PreOrderDto;
import lk.ijse.sellingLk.model.SellerDto;
import lk.ijse.sellingLk.model.VehicleDto;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class OverviewBOImpl implements OverviewBO {
    private SellerDAO sellerDAO = (SellerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SELLER);
    private BuyerDAO buyerDAO= (BuyerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BUYER);
    private VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);
    private PreOrderDAO preOrderDAO = (PreOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PREORDER);

    @Override
    public List<SellerDto> getAllSeller() throws SQLException {
        return sellerDAO.getAll().stream().map(seller -> new SellerDto(seller.getId(), seller.getName(), seller.getNic(), seller.getEmail(), seller.getAddress(), seller.getPhone(), seller.getUId())).collect(Collectors.toList());
    }

    @Override
    public List<BuyerDto> getAllBuyer() throws SQLException {
        return buyerDAO.getAll().stream().map(buyer -> new BuyerDto(buyer.getId(), buyer.getName(), buyer.getNic(), buyer.getEmail(), buyer.getAddress(), buyer.getPhone(), buyer.getUId(), buyer.getLoyalId())).collect(Collectors.toList());
    }

    @Override
    public List<VehicleDto> getAllVehicle() throws SQLException {
        return vehicleDAO.getAll().stream().map(vehicle -> new VehicleDto(vehicle.getId(), vehicle.getType(), vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getFuelType(), vehicle.getEnginCapacity(), vehicle.getMileage(), vehicle.getVehicleNumber(), vehicle.getPrice(), vehicle.getStatus())).collect(Collectors.toList());
    }

    @Override
    public List<PreOrderDto> getAllPreOrders() throws SQLException {
        return preOrderDAO.getThisMonthPreOrders().stream().map(preOrder -> new PreOrderDto(preOrder.getId(), preOrder.getYear(), preOrder.getBrand(), preOrder.getModel(), preOrder.getDate(), preOrder.getStatus(), preOrder.getBuyerId())).collect(Collectors.toList());
    }
}
