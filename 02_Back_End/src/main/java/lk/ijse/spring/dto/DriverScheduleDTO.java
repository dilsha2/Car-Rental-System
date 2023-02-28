package lk.ijse.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "HH:mm:ss")
    private Time start_time;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Kolkata")
    private Date start_date;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Kolkata")
    private Date end_date;

    private DriverDTO driver;

    private ReservationDTO carReservation;

    public DriverScheduleDTO(Time start_time, Date start_date, Date end_date, DriverDTO driver, ReservationDTO carReservation) {
        this.start_time = start_time;
        this.start_date = start_date;
        this.end_date = end_date;
        this.driver = driver;
        this.carReservation = carReservation;
    }
}
