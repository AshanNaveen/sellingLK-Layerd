package lk.ijse.sellingLk.bo.custom.impl;

import lk.ijse.sellingLk.bo.BOFactory;
import lk.ijse.sellingLk.bo.custom.SellOrderBO;
import lk.ijse.sellingLk.dao.DAOFactory;
import lk.ijse.sellingLk.dao.custom.*;
import lk.ijse.sellingLk.controller.SignInFormController;
import lk.ijse.sellingLk.db.DbConnection;
import lk.ijse.sellingLk.entity.SellOrder;
import lk.ijse.sellingLk.entity.SellOrderDetail;
import lk.ijse.sellingLk.entity.Vehicle;
import lk.ijse.sellingLk.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SellOrderBOImpl implements SellOrderBO {
    private UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    private BuyerDAO buyerDAO= (BuyerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BUYER);
    private SellOrderDAO sellOrderDAO = (SellOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SELLORDER);
    private VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);
    private PaymentBOImpl paymentBO = (PaymentBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    private SellOrderDetailDAO sellOrderDetailDAO = (SellOrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SELLORDERDETAIL);

    public boolean saveSellOrder(PlaceOrderDto placeOrderDto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isSaved = sellOrderDAO.save(new SellOrder(placeOrderDto.getOrderId(), placeOrderDto.getCusId()));
            if (isSaved) {
                boolean isUpdated = true;
                for (String vehicle : placeOrderDto.getItems()){
                    if (!vehicleDAO.updateStatus(vehicle)){
                        isUpdated=false;
                        break;
                    }
                }
                if (isUpdated) {
                    String id = paymentBO.generateNextId();
                    String uId = userDAO.searchUser(SignInFormController.uname, SignInFormController.pword).getId();
                    boolean isPaid = paymentBO.savePayment(new PaymentDto(id, placeOrderDto.getAmount(), placeOrderDto.getDate(), null, placeOrderDto.getCusId(), uId));
                    if (isPaid) {
                        boolean isOrderDetailSaved = true;
                        for (String vehicle : placeOrderDto.getItems()){
                            if(!sellOrderDetailDAO.save(new SellOrderDetail(String.valueOf(placeOrderDto.getDate()), placeOrderDto.getOrderId(), vehicle))){
                                isOrderDetailSaved=false;
                                break;
                            }
                        }
                        if (isOrderDetailSaved) {
                            connection.commit();
                            System.out.println("Hureee");
                            //generateReport(placeOrderDto);
                            result = true;
                        }

                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }
    public String generateNextOrderId() throws SQLException {
        return splitId(sellOrderDAO.getLastID());
    }

    public String splitId(String current) {
        if (current != null) {
            String[] split = current.split("O");
            int id = Integer.parseInt(split[1]);
            id++;
            if (9>=id&&id > 0) return "O00" + id;
            else if (99>=id&&id > 9) return "O0" + id;
            else if (id > 99) return String.valueOf(id);
        }
        return "O001";
    }

    @Override
    public List<String> getNotSoldVehicle() throws SQLException {
        return vehicleDAO.getNotSoldVehicle();
    }

    @Override
    public VehicleDto getVehicleInfo(String value) throws SQLException {
        Vehicle vehicle = vehicleDAO.getInfo(value);
        return new VehicleDto(vehicle.getId(), vehicle.getType(), vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getFuelType(), vehicle.getEnginCapacity(), vehicle.getMileage(), vehicle.getVehicleNumber(), vehicle.getPrice(), vehicle.getStatus());
    }

    @Override
    public String getBuyerEmail(String cusId) throws SQLException {
        return buyerDAO.getInfo(cusId).getEmail();
    }

    @Override
    public String getBuyerName(String text) throws SQLException {
        return buyerDAO.getInfo(text).getName();
    }

    @Override
    public String getUserName(String uname, String pword) throws SQLException {
        return userDAO.searchUser(uname,pword).getName();
    }
}
