package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity(name = "driver_schedule")
public class DriverSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schedule_id;

    private LocalTime start_time;
    private LocalDate start_date;
    private LocalDate end_date;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH})
    private Driver driverNic;

    @OneToOne(cascade = CascadeType.ALL)
    private Rental rentalId;
}
