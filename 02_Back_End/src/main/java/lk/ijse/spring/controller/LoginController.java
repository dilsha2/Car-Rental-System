package lk.ijse.spring.controller;

import lk.ijse.spring.dto.AdminDTO;
import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.dto.UserDTO;
import lk.ijse.spring.service.AdminService;
import lk.ijse.spring.service.CustomerService;
import lk.ijse.spring.service.DriverService;
import lk.ijse.spring.util.PasswordEncryptor;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    CustomerService customerService;

    @Autowired
    AdminService adminService;

    @Autowired
    DriverService driverService;

    @Autowired
    PasswordEncryptor passwordEncryptor;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil checkUserNameAndPassword(@RequestBody UserDTO userDTO) {

        //convert string variable to encrypted hex value
        String encryptedPassword = passwordEncryptor.getPassword(userDTO.getPassword());

        CustomerDTO customerDTO = customerService.checkCustomerLogIn(userDTO.getUser_name(), encryptedPassword);
        if (customerDTO == null) {
            AdminDTO adminDTO = adminService.checkAdminLogIn(userDTO.getUser_name(), encryptedPassword);
            if (adminDTO == null) {
                DriverDTO driverDTO = driverService.checkDriverLogIn(userDTO.getUser_name(), encryptedPassword);
                if (!(driverDTO == null)) {
                    return new ResponseUtil(200, "Driver", driverDTO);
                } else {
                    return new ResponseUtil(200, "Incorrect username and password", null);
                }
            } else {
                return new ResponseUtil(200, "Admin", adminDTO);
            }
        } else {
            return new ResponseUtil(200, "Customer", customerDTO);
        }
    }


}
