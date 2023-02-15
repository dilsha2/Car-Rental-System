package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Driver {
    @Id
    private String driverId;
    private String name;
    private String nic;
    private String drivingLicenseNumber;
    private LocalDate dob;
    private String status;

}
