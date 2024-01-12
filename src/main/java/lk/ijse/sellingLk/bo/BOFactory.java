package lk.ijse.sellingLk.bo;

import lk.ijse.sellingLk.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory = null;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        BUYER, BUYORDER, EMPLOYEE, LOYALID, OVERVIEW, PAYMENT, PREORDER, SEARCH, SELLER, SELLORDER, SIGNIN, SIGNUP, VEHICLE
    }

    public SuperBO getBO(BOTypes boType) {
        switch (boType) {
            case BUYER:
                return new BuyerBOImpl();
            case BUYORDER:
                return new BuyOrderBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case LOYALID:
                return new LoyalIdBOImpl();
            case OVERVIEW:
                return new OverviewBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case PREORDER:
                return new PreOrderBOImpl();
            case SEARCH:
                return new SearchBOImpl();
            case SELLER:
                return new SellerBOImpl();
            case SELLORDER:
                return new SellOrderBOImpl();
            case SIGNIN:
                return new SignInBOImpl();
            case SIGNUP:
                return new SignUpBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            default:
                return null;
        }
    }

}
