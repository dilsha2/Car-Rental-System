package lk.ijse.spring.controller;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.service.DriverService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    DriverService service;

    @PostMapping
    public ResponseUtil SaveDriver(@RequestBody DriverDTO dto){
        service.saveDriver(dto);
        return new ResponseUtil(200, "Added Successfully....", dto);
    }
}
