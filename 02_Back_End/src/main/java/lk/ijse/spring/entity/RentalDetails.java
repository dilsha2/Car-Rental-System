package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@ToString
public class RentalDetails {

    @EmbeddedId
    private RentalDetails_PK id;

    @ManyToOne
    private Driver driverNic;
    private String rentalStatus;
    private String paymentSlip;

}
