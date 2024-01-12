package lk.ijse.sellingLk.dao;

import lk.ijse.sellingLk.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory = null;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        BUYER, BUYORDER, BUYORDERDETAI, EMPLOYEE, LOYAID, PAYMENT, PREORDER, SELLER, SELLORDER, SELLORDERDETAIL, USER, VEHICLE, WEBSEARCH
    }

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case BUYER:
                return new BuyerDAOImpl();
            case BUYORDER:
                return new BuyOrderDAOImpl();
            case BUYORDERDETAI:
                return new BuyOrderDetailDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case LOYAID:
                return new LoyalIdDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case PREORDER:
                return new PreOrderDAOImpl();
            case SELLER:
                return new SellerDAOImpl();
            case SELLORDER:
                return new SellOrderDAOImpl();
            case SELLORDERDETAIL:
                return new SellOrderDetailDAOImpl();
            case USER:
                return new UserDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            case WEBSEARCH:
                return new WebSearchDAOImpl();
            default:
                return null;
        }
    }
}
