package lk.ijse.sellingLk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BuyOrderDetail {
    private String date;
    private String orderId;
    private String itemId;
}
