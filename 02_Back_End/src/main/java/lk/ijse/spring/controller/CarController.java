package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.service.CarService;
import lk.ijse.spring.service.CustomerService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@CrossOrigin
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService service;

    @PostMapping( produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil saveCar(@RequestBody CarDTO  dto){
        System.out.println(dto);
        service.saveCar(dto);
        return new ResponseUtil(200, "New Vehicle Registered Successfully...", null);

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

    @PutMapping(params = {"id", "status"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateCarStatusForUnavailableOrAvailable(@RequestParam String id, @RequestParam String status) {
        service.setCarStatusUnavailableOrAvailable(id, status);
        return new ResponseUtil(200, "Set Car " + id + " As " + status, null);
    }

    //return cars they are statuses is maintained
    @GetMapping(path = "carsUnderMaintain", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getCarsUnderMaintain() {
        return new ResponseUtil(200, "Done", service.getCarsUnderMaintain());
    }

    //return cars they are needed to maintaining
    @GetMapping(path = "carsNeedMaintain", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getCarsNeedMaintain() {
        return new ResponseUtil(200, "Done", service.getCarsNeedMaintain());
    }

    //sent status as Available or Unavailable,Then check it with cars
    @GetMapping(path = "unavailableOrAvailableCarsByStatus/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getUnavailableOrAvailableCars(@PathVariable String status) {
        return new ResponseUtil(200, "Done", service.getUnavailableOrAvailableCarsByStatus(status));
    }

    @DeleteMapping(path = "removeCar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteCarDetail(@PathVariable String id) {
        service.deleteCar(id);
        return new ResponseUtil(200, "Vehicle Details Deleted Successfully", null);
    }

    @GetMapping(path = "carsSortByAttribute", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil carsSortByAttribute(@RequestBody CarDTO carDTO) {
        return new ResponseUtil(200, "Done", service.sortCarsByAttributes(carDTO));
    }

}
