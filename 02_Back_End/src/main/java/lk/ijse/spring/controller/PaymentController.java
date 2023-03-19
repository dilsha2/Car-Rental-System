package lk.ijse.spring.controller;

import lk.ijse.spring.dto.PaymentDTO;
import lk.ijse.spring.service.PaymentService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping(path = "generateBillId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generatePaymentId() {
        return new ResponseUtil(200, "Done", paymentService.generateReservationBillIdId());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil makePaymentForReservation(@RequestBody PaymentDTO reservationPaymentDTO) {
        paymentService.makePaymentForReservation(reservationPaymentDTO);
        return new ResponseUtil(200, "Transaction Successfully", null);
    }

    //get Daily,Monthly,Weekly & yearly as type para.Otherwise, get two dates for find the between income
    @GetMapping(path = "daily_weekly_Annually_Income", params = {"type", "start_date", "end_date"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil DailyOrWeekOrAnnualIncome(@RequestParam String type, @RequestParam String start_date, @RequestParam String end_date) {
        return new ResponseUtil(200, "Done", paymentService.getIncomeByDate(type, start_date, end_date));
    }

    @GetMapping(path = "todayIncomeList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil todayIncomeList() {
        return new ResponseUtil(200, "Done", paymentService.getTodayIncomeList());
    }
}
