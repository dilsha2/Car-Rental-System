package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Driver {
    @Id
    private String driverNic;
    private String driver_name;
    private String address;
    private String mobile;
    private String license_no;
    private String join_date;
    private String user_name;
    private String password;

    @OneToMany(mappedBy = "driverNic")
    private List<RentalDetails> rentalDetailsList;
}
