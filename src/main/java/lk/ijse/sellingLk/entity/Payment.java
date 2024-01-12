package lk.ijse.sellingLk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class Payment {
    private String id;
    private int amount;
    private Date date;
    private String sellerId;
    private String buyerId;
    private String uId;
}
