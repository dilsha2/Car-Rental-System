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
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int schedule_id;
//
//    private LocalTime start_time;
//    private LocalDate start_date;
//    private LocalDate end_date;
//
//    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH})
//    private Driver driverNic;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    private Rental rentalId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schedule_id;

    private Time start_time;
    private Date start_date;
    private Date end_date;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn(name="driver_nic")
    private Driver driver;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reserve_id")
    private Rental carReservation;
}
