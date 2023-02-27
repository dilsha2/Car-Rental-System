package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.ReservationDTO;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.repo.DriverRepo;
import lk.ijse.spring.repo.RentalRepo;
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
    public void requestReservation(ReservationDTO ReservationDTO) {

    }

    @Override
    public void updateReservationStatus(String reserve_id, String driver_id, String status) {

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
