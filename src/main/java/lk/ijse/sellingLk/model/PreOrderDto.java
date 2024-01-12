package lk.ijse.sellingLk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreOrderDto {
    private String id;
    private int year;
    private String brand;
    private String model;
    private String date;
    private int Status;
    private String buyerId;
}
