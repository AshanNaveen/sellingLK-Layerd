package lk.ijse.sellingLk.bo.custom.impl;

import lk.ijse.sellingLk.bo.custom.PaymentBO;
import lk.ijse.sellingLk.dao.DAOFactory;
import lk.ijse.sellingLk.dao.custom.PaymentDAO;
import lk.ijse.sellingLk.entity.Payment;
import lk.ijse.sellingLk.model.PaymentDto;

import java.sql.SQLException;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    public String generateNextId() throws SQLException {
        return splitId(paymentDAO.getLastID());
    }

    public String splitId(String current) {
        if (current != null) {
            String[] split = current.split("P");
            int id = Integer.parseInt(split[1]);
            id++;
            if (9>=id && id > 0) return "P00" + id;
            else if (99>=id && id > 9) return "P0" + id;
            else if (id > 99) return "P"+id;
        }
        return "P001";
    }

    public boolean savePayment(PaymentDto paymentDto) throws SQLException {
        return paymentDAO.save(new Payment(paymentDto.getId(), paymentDto.getAmount(), paymentDto.getDate(),paymentDto.getSellerId(), paymentDto.getBuyerId(), paymentDto.getUId()));
    }
}
