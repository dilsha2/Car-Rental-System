package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CustomerDTO {
    private String name;
    private String address;
    private String nic;
    private String contactNumber;
    private String email;
    private String user_name;
    private String password;
    private String imageLocation;
    private String drivingLicenseNo;

}
