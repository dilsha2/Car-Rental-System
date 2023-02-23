package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.ReservationDTO;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.repo.DriverRepo;
import lk.ijse.spring.repo.RentalRepo;
import lk.ijse.spring.service.ReservationService;
import org.modelmapper.ModelMapper;
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
        return null;
    }

    @Override
    public void requestReservation(ReservationDTO ReservationDTO) {

    }

    @Override
    public void updateReservationStatus(String reserve_id, String driver_id, String status) {

    }

    @Override
    public List<ReservationDTO> getAllPendingReservation() {
        return null;
    }

    @Override
    public ReservationDTO getReservationDetail(String id) {
        return null;
    }

    @Override
    public List<ReservationDTO> getAllTodayReservation() {
        return null;
    }

    @Override
    public List<ReservationDTO> getAllTodayPickUps() {
        return null;
    }

    @Override
    public List<ReservationDTO> getCustomerReservationByStatus(String id, String status) {
        return null;
    }
}
