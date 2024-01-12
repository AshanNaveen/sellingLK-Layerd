package lk.ijse.sellingLk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreOrder {
    private String id;
    private int year;
    private String brand;
    private String model;
    private String date;
    private int Status;
    private String buyerId;
}
