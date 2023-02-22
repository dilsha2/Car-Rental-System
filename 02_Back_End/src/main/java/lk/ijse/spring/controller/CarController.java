package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.service.CarService;
import lk.ijse.spring.service.CustomerService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService service;

    @PostMapping
    public ResponseUtil saveCar(@RequestBody CarDTO dto){
        service.saveCar(dto);
        return new ResponseUtil(200, "Registration Successfully....", dto);
    }

    @PostMapping(path = "updateCarDetail", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil updateCar(@RequestBody CarDTO dto){
        service.saveCar(dto);
        return new ResponseUtil(200, "Registration Successfully....", dto);
    }

    @GetMapping(path = "carDetail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getCarDetail(@PathVariable String id) {
        CarDTO carDTO = service.getCarDetail(id);
        return new ResponseUtil(200, "Done", carDTO);
    }

    @GetMapping(path = "allCarDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllCarDetail() {
        return new ResponseUtil(200, "Done", service.getAllCarDetail());
    }

}
