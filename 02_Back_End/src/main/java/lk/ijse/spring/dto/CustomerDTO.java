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

//    private MultipartFile image;
//
//    public CustomerDTO(String name, String address, String nic, String contactNumber, String email, String user_name, String password, String imageLocation) {
//        this.name = name;
//        this.address = address;
//        this.nic = nic;
//        this.contactNumber = contactNumber;
//        this.email = email;
//        this.user_name = user_name;
//        this.password = password;
//        this.imageLocation = imageLocation;
//    }
//
//    public CustomerDTO(String name, String address, String nic, String contactNumber, String email, String user_name, String password, MultipartFile image) {
//        this.name = name;
//        this.address = address;
//        this.nic = nic;
//        this.contactNumber = contactNumber;
//        this.email = email;
//        this.user_name = user_name;
//        this.password = password;
//        this.image = image;
//    }
}
