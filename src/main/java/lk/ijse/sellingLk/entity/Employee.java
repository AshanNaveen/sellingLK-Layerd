package lk.ijse.sellingLk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Employee {
    private String id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String role;
    private String uId;
}
