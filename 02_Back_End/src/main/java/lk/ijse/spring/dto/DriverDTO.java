package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DriverDTO {
    private String driverNic;
    private String driver_name;
    private String address;
    private String mobile;
    private String license_no;
    private String join_date;
    private String user_name;
    private String password;
}
