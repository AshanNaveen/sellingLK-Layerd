package lk.ijse.sellingLk.bo.custom.impl;

import lk.ijse.sellingLk.bo.BOFactory;
import lk.ijse.sellingLk.bo.custom.BuyOrderBO;
import lk.ijse.sellingLk.bo.custom.PaymentBO;
import lk.ijse.sellingLk.dao.DAOFactory;
import lk.ijse.sellingLk.dao.custom.*;
import lk.ijse.sellingLk.controller.SignInFormController;
import lk.ijse.sellingLk.db.DbConnection;
import lk.ijse.sellingLk.entity.BuyOrder;
import lk.ijse.sellingLk.entity.BuyOrderDetail;
import lk.ijse.sellingLk.entity.Payment;
import lk.ijse.sellingLk.entity.Vehicle;
import lk.ijse.sellingLk.model.*;

import java.sql.Connection;
import java.sql.SQLException;

public class BuyOrderBOImpl implements BuyOrderBO {
    private UserDAO userDAO= (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    private BuyOrderDAO buyOrderDAO = (BuyOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BUYORDER);
    private BuyerDAO buyerDAO = (BuyerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BUYER);
    private PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    private PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    private VehicleDAO vehicleBO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);
    private BuyOrderDetailDAO buyOrderDetailDAO = (BuyOrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BUYORDERDETAI);


    @Override
    public boolean saveOrder(BuyOrderDto dto) throws SQLException {
        return buyOrderDAO.save(new BuyOrder(dto.getId(), dto.getSellerId()));
    }

    @Override
    public String generateNextOrderId() throws SQLException {
        return splitId(buyOrderDAO.getLastID());
    }

    @Override
    public String splitId(String current) {
        if (current != null) {
            String[] split = current.split("O");
            int id = Integer.parseInt(split[1]);
            id++;
            if (9 >= id && id > 0) return "O00" + id;
            else if (99 >= id && id > 9) return "O0" + id;
            else if (id > 99) return String.valueOf(id);
        }
        return "O001";
    }

    public boolean saveBuyOrder(PlaceOrderDto placeOrderDto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isSaved = buyOrderDAO.save(new BuyOrder(placeOrderDto.getOrderId(), placeOrderDto.getCusId()));
            if (isSaved) {
                String id = paymentBO.generateNextId();
                String uId = userDAO.searchUser(SignInFormController.uname, SignInFormController.pword).getId();
                boolean isPaid = paymentDAO.save(new Payment(id, placeOrderDto.getAmount(), placeOrderDto.getDate(), placeOrderDto.getCusId(), null, uId));
                if (isPaid) {
                    boolean isOrderDetailSaved = true;
                    for (String vehivle:placeOrderDto.getItems()){
                        if (!buyOrderDetailDAO.save(new BuyOrderDetail(String.valueOf(placeOrderDto.getDate()),placeOrderDto.getOrderId(),vehivle))){
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

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }

    @Override
    public VehicleDto getVehicleInfo(String value) throws SQLException {
        Vehicle vehicle = vehicleBO.getInfo(value);
        return new VehicleDto(vehicle.getId(), vehicle.getType(), vehicle.getBrand(), vehicle.getModel(), vehicle.getYear(), vehicle.getFuelType(), vehicle.getEnginCapacity(), vehicle.getMileage(), vehicle.getVehicleNumber(), vehicle.getPrice(), vehicle.getStatus());
    }

    @Override
    public String getBuyerName(String cusId) throws SQLException {
        return buyerDAO.getInfo(cusId).getName();
    }

}
