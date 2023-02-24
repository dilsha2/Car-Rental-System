package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer {

    private String name;
    private String address;
    @Id
    private String nic;
    private String contactNumber;
    private String email;
    private String user_name;
    private String password;
    private String drivingLicenseNo;
    private String imageLocation;
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "nic")
    private List<Rental> rentalDetails;


}
