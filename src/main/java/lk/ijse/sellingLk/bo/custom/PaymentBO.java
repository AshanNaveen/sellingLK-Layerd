package lk.ijse.sellingLk.bo.custom;

import lk.ijse.sellingLk.bo.SuperBO;
import lk.ijse.sellingLk.model.PaymentDto;

import java.sql.SQLException;

public interface PaymentBO extends SuperBO {
    String generateNextId() throws SQLException ;

    String splitId(String current) ;

    boolean savePayment(PaymentDto paymentDto) throws SQLException ;
}
