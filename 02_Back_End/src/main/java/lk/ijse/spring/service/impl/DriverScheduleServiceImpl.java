package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.DriverScheduleDTO;
import lk.ijse.spring.entity.DriverSchedule;
import lk.ijse.spring.entity.Rental;
import lk.ijse.spring.repo.DriverScheduleRepo;
import lk.ijse.spring.repo.RentalRepo;
import lk.ijse.spring.service.DriverScheduleService;
import lk.ijse.spring.util.DateFinder;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DriverScheduleServiceImpl implements DriverScheduleService {

    @Autowired
    DriverScheduleRepo driverScheduleRepo;

    @Autowired
    RentalRepo carReservationRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<DriverScheduleDTO> getDriverSchedulesByDate(String start_date, String end_date) {
        return mapper.map(driverScheduleRepo.getDriverSchedulesByDate(start_date, end_date), new TypeToken<List<DriverScheduleDTO>>() {
        }.getType());
    }

    @Override
    public List<DriverScheduleDTO> getDriverWeeklyScheduleByDate(String driver_id, String date_range) {
        if (date_range.equals("Monthly")) {
            LocalDate monthStartDate = DateFinder.getMonthStartDate();
            LocalDate monthEndDate = DateFinder.getMonthEndDate();

            List<DriverSchedule> schedule = driverScheduleRepo.getDriverWeeklyOrMonthlyScheduleByDate(driver_id, monthStartDate, monthEndDate);
            if (!schedule.isEmpty()) {
                return mapper.map(schedule, new TypeToken<List<DriverScheduleDTO>>() {
                }.getType());
            } else {
                throw new RuntimeException("You Have Not any Rides In this Month ");
            }
        } else if (date_range.equals("Weekly")) {
            LocalDate weekStartDate = DateFinder.getWeekStartDate();
            LocalDate weekEndDate = DateFinder.getWeekEndDate();
            List<DriverSchedule> schedule = driverScheduleRepo.getDriverWeeklyOrMonthlyScheduleByDate(driver_id, weekStartDate, weekEndDate);
            if (!schedule.isEmpty()) {
                return mapper.map(schedule, new TypeToken<List<DriverScheduleDTO>>() {
                }.getType());
            } else {
                throw new RuntimeException("You Have Not any Rides In this Week");
            }
        } else {
            throw new RuntimeException("Please Select Weekly or Monthly Schedule");
        }
    }

    @Override
    public List<DriverScheduleDTO> getDriverScheduleForSendCustomer(String customer_id) {
        List<Rental> accept = carReservationRepo.getCustomerReservationByStatus(customer_id, "Accept");
        List<DriverScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Rental reservation : accept) {
            if (!reservation.getDriver_status().equals("NO")) {
                DriverScheduleDTO dto = mapper.map(driverScheduleRepo.getDriverSchedulesByReservationId(reservation.getReserve_id()), DriverScheduleDTO.class);
                scheduleDTOS.add(dto);
            }
        }
        return scheduleDTOS;
    }

    @Override
    public String getDriverByReservationId(String id) {
        return driverScheduleRepo.getDriverIdByReservationId(id);
    }
}
