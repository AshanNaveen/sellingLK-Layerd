package lk.ijse.sellingLk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BuyOrderDetailDto {
    private String date;
    private String orderId;
    private String itemId;
}
