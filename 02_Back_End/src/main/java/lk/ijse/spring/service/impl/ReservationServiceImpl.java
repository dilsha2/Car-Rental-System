package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.dto.DriverScheduleDTO;
import lk.ijse.spring.dto.ReservationDTO;
import lk.ijse.spring.entity.*;
import lk.ijse.spring.repo.*;
import lk.ijse.spring.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    RentalRepo carReservationRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CarRepo carRepo;

    @Autowired
    DriverRepo driverRepo;

    @Autowired
    ModelMapper mapper;

    @Autowired
    DriverScheduleRepo driverScheduleRepo;

    @Override
    public String generateReservationId() {
        String id = carReservationRepo.generateReservationId();
        if (!(id == null)) {
            int tempId = Integer.parseInt(id.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "RID-000" + tempId;

            } else if (tempId <= 99) {
                return "RID-00" + tempId;

            } else if (tempId <= 999) {
                return "RID-0" + tempId;

            } else {
                return "RID-" + tempId;
            }
        } else {
            return "RID-0001";
        }
    }

    @Override
    public void requestReservation(ReservationDTO reservationDTO) {
        if (!carReservationRepo.existsById(reservationDTO.getReserve_id())) {

            Rental carReservation = mapper.map(reservationDTO, Rental.class);

            Customer customer = customerRepo.findById(reservationDTO.getCustomer().getNic()).get();
            Car car = carRepo.findById(reservationDTO.getCar().getRegistrationId()).get();

            carReservation.setCustomer(customer);
            carReservation.setCar(car);

            if (reservationDTO.getDriver_status().equals("YES")) {

                Driver driver = driverRepo.selectDriverForReservation(
                        reservationDTO.getPick_up_date(),
                        reservationDTO.getReturn_date());

                DriverScheduleDTO driverScheduleDTO = new DriverScheduleDTO(
                        reservationDTO.getPick_up_time(),
                        reservationDTO.getPick_up_date(),
                        reservationDTO.getReturn_date(),
                        mapper.map(driver, DriverDTO.class),
                        mapper.map(carReservation, ReservationDTO.class));

                driverScheduleRepo.save(mapper.map(driverScheduleDTO, DriverSchedule.class));

            } else {
                carReservationRepo.save(carReservation);
            }
        } else {
            throw new RuntimeException("Your Reservation Request can't Send in this moment,Try Again..!");
        }
    }

    @Override
    public void updateReservationStatus(String reserve_id, String driver_id, String status) {
        if (carReservationRepo.existsById(reserve_id)) {

            Rental carReservation = carReservationRepo.findById(reserve_id).get();
            carReservation.setReservation_status(status);

            if (!driver_id.isEmpty()) {
                Driver driver = driverRepo.findById(driver_id).get();
                DriverSchedule driverSchedule = driverScheduleRepo.getDriverSchedulesByReservationId(reserve_id);
                driverSchedule.setDriver(driver);
                driverSchedule.setCarReservation(carReservation);
            } else {
                carReservationRepo.save(carReservation);
            }
        } else {
            throw new RuntimeException("Can't Review This Reservation,This Reservation Previous Record is Missing.Try Again..!");
        }
    }

    @Override
    public List<ReservationDTO> getAllPendingReservation() {
        return mapper.map(carReservationRepo.getAllPendingReservation(), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

    @Override
    public ReservationDTO getReservationDetail(String id) {
        if (carReservationRepo.existsById(id)) {
            return mapper.map(carReservationRepo.findById(id).get(), ReservationDTO.class);
        } else {
            throw new RuntimeException("Can't Get Details..!  This Reservation Previous Record is Missing.Try Again..!");
        }
    }

    @Override
    public List<ReservationDTO> getAllTodayReservation() {
        return mapper.map(carReservationRepo.getAllTodayReservation(), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

    @Override
    public List<ReservationDTO> getAllTodayPickUps() {
        return mapper.map(carReservationRepo.getAllTodayPickUps(), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }


    @Override
    public List<ReservationDTO> getCustomerReservationByStatus(String id, String status) {
        return mapper.map(carReservationRepo.getCustomerReservationByStatus(id, status), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }
}
