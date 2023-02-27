package lk.ijse.spring.dto;

import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.entity.Rental;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DriverScheduleDTO {

    private int schedule_id;
    private LocalTime start_time;
    private LocalDate start_date;
    private LocalDate end_date;
    private DriverDTO driverNic;
    private ReservationDTO rentalId;

    public DriverScheduleDTO( LocalTime start_time, LocalDate start_date, LocalDate end_date, DriverDTO driverNic, ReservationDTO rentalId) {
        this.start_time = start_time;
        this.start_date = start_date;
        this.end_date = end_date;
        this.driverNic = driverNic;
        this.rentalId = rentalId;
    }
}
