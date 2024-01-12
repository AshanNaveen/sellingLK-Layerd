package lk.ijse.sellingLk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Buyer {
    private String id;
    private String name;
    private String nic;
    private String email;
    private String address;
    private String phone;
    private String uId;
    private String loyalId;
}
