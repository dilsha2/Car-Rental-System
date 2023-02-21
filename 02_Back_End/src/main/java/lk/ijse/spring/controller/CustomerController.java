package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.service.CustomerService;
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


   @PostMapping
   public ResponseUtil RegisterCustomer(@RequestBody CustomerDTO dto){
       service.saveCustomer(dto);
       System.out.println(dto.toString());
       return new ResponseUtil(200, "Registration Successfully....", dto);

   }

   @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseUtil UpdateCustomer(CustomerDTO dto){
       service.saveCustomer(dto);
       return new ResponseUtil(200,"Update Successfully",dto);

   }

}
