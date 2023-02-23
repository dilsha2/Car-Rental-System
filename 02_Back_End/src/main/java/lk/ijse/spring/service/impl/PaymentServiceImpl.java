package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.PaymentDTO;
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
        return null;
    }

    @Override
    public void makePaymentForReservation(PaymentDTO paymentDTO) {

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
