package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Customer {

   // private String customerId;
    private String name;
    private String address;
    @Id
    private String nic;
    private String contactNumber;
    private String email;
    private String user_name;
    private String password;
    private String imageLocation;

    @OneToMany(mappedBy = "nic")
    private List<Rental> rentalDetails;


}
