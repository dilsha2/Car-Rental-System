package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CustomerDTO {
    private String customer_name;
    private String nic;
    private String user_name;
    private String password;
    private String mobile;
    private LocalDate register_date;
    private String address;
    private String email;
    private String image;
}
