package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.service.CustomerService;
import lk.ijse.spring.service.DriverScheduleService;
import lk.ijse.spring.service.ReservationService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

   @Autowired
   CustomerService service;

    @Autowired
    DriverScheduleService driverScheduleService;

    @Autowired
    ReservationService carReservationService;


   @PostMapping
   public ResponseUtil RegisterCustomer(@RequestBody CustomerDTO dto){
       service.saveCustomer(dto);
       return new ResponseUtil(200, "Registration Successfully....", dto);

   }

   @PutMapping(path = "updateCustomer",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseUtil UpdateCustomer(CustomerDTO dto){
       service.saveCustomer(dto);
       return new ResponseUtil(200,"Update Successfully",dto);

   }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllCustomerDetail() {
        return new ResponseUtil(200, "Done", service.getAllCustomerDetail());
    }

    @DeleteMapping(path = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteCustomer(@PathVariable String id) {
        service.deleteCustomer(id);
        return new ResponseUtil(200, "Delete Your Account Successfully", null);
    }

    @GetMapping(path = "customerDetail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getCustomerDetail(@PathVariable String id) {
        CustomerDTO customerDTO = service.getCustomerDetail(id);
        System.out.println(customerDTO.toString());
        return new ResponseUtil(200, "Done", customerDTO);
    }

    @GetMapping(path = "todayRegisteredUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getTodayRegisteredCustomers() {
        return new ResponseUtil(200, "Done", service.getTodayRegisteredCustomers());
    }


    //This can be use for get customer reservation history and upcoming reservations.
    // status="Done" or "Accept"
    @GetMapping(path = "customerReservationByStatus", params = {"id", "status"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getCustomerReservationByStatus(@RequestParam String id, @RequestParam String status) {
        return new ResponseUtil(200, "Done", carReservationService.getCustomerReservationByStatus(id, status));
    }

    //When admin accept the reservation request driver info should need to send customer if customer request driver
    @GetMapping(path = "sendDriverInfoForAcceptReservations/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil sendDriverInfoForAcceptReservations(@PathVariable String id) {
        return new ResponseUtil(200, "Done", driverScheduleService.getDriverScheduleForSendCustomer(id));
    }


}
