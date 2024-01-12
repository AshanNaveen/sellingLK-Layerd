package lk.ijse.sellingLk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BuyOrderDto {
    private String id;
    private String sellerId;
}
