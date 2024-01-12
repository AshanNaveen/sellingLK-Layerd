package lk.ijse.sellingLk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SalaryDto {
    private String id;
    private String otPay;
    private String basicPay;
    private String empId;
}
