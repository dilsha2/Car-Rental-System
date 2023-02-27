package lk.ijse.spring.dto;

import lk.ijse.spring.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Time;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ReservationDTO {

    private String rentalId;
    private CustomerDTO nic;
    private Time pickupTime;
    private LocalDate pickupDate;
    private String pick_up_and_return_venue;
    private LocalDate returnDate;
    private int no_of_days;
    private String bank_slip_img;
    private String reservation_status;
    private String driver_status;


    private CarDTO registrationId;
}
