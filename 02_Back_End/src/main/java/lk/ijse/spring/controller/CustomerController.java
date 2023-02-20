package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.service.CustomerService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

   @Autowired
   CustomerService service;

   @PostMapping
   public ResponseUtil RegisterCustomer(@ModelAttribute CustomerDTO dto){
       service.saveCustomer(dto);
       return new ResponseUtil(200, "Registration Successfully....", dto);

   }

}
