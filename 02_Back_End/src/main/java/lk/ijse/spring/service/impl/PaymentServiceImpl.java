package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.PaymentDTO;
import lk.ijse.spring.entity.Payment;
import lk.ijse.spring.entity.Rental;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.repo.PaymentRepo;
import lk.ijse.spring.repo.RentalRepo;
import lk.ijse.spring.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepo paymentRepo;

    @Autowired
    RentalRepo rentalRepo;

    @Autowired
    CarRepo carRepo;

    @Autowired
    ModelMapper mapper;
    @Override
    public String generateReservationBillIdId() {
        String id = paymentRepo.generateReservationBillId();
        if (!(id == null)) {
            int tempId = Integer.parseInt(id.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "BID-000" + tempId;

            } else if (tempId <= 99) {
                return "BID-00" + tempId;

            } else if (tempId <= 999) {
                return "BID-0" + tempId;

            } else {
                return "BID-" + tempId;
            }
        } else {
            return "BID-0001";
        }
    }

    @Override
    public void makePaymentForReservation(PaymentDTO paymentDTO) {
        if (!paymentRepo.existsById(paymentDTO.getBill_id())) {
            Rental carReservation = rentalRepo.findById(paymentDTO.getCarReservation().getReserve_id()).get();
            Payment reservationPayment = mapper.map(paymentDTO, Payment.class);
            carReservation.getCar().setMileage(carReservation.getCar().getMileage() + reservationPayment.getNo_of_km());
            carReservation.setReservation_status("Done");
            reservationPayment.setCarReservation(carReservation);
            paymentRepo.save(reservationPayment);
        } else {
            throw new RuntimeException("Payment Already Done");
        }
    }

    @Override
    public String getIncomeByDate(String type, String start_date, String end_date) {
        return null;
    }

    @Override
    public List<PaymentDTO> getTodayIncomeList() {
        return null;
    }
}
