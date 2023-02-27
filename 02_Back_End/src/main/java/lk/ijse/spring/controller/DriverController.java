package lk.ijse.spring.controller;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.service.DriverScheduleService;
import lk.ijse.spring.service.DriverService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    DriverService service;


    @Autowired
    DriverScheduleService driverScheduleService;

    @PostMapping
    public ResponseUtil SaveDriver(@RequestBody DriverDTO dto){
        service.saveDriver(dto);
        return new ResponseUtil(200, "Added Successfully....", dto);
    }

    @PutMapping(path="updateDriver",produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseUtil updateDriverDetail(@RequestBody DriverDTO driverDTO) {
        service.UpdateDriver(driverDTO);
        return new ResponseUtil(200, "Driver Updated Successfully", null);
    }

    @DeleteMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteDriverDetail(@PathVariable String id) {
        service.deleteDriver(id);
        return new ResponseUtil(200, "Driver Details Deleted Successfully", null);
    }

    @GetMapping(path = "driverDetail/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getDriverDetail(@PathVariable String id) {
        return new ResponseUtil(200, "Done", service.getDriverDetail(id));
    }

    @GetMapping(path = "allDriverDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllDriverDetail() {
        return new ResponseUtil(200, "Done", service.getAllDriverDetail());
    }

    @GetMapping(path = "driverScheduleByDate", params = {"start_date", "end_date"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getDriverScheduleByDate(@RequestParam String start_date, @RequestParam String end_date) {
        return new ResponseUtil(200, "Done", driverScheduleService.getDriverSchedulesByDate(start_date, end_date));
    }

    @GetMapping(path = "todayAvailableAndOccupiedDrivers/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getTodayAvailableAndOccupiedDrivers(@PathVariable String status) {
        return new ResponseUtil(200, "Done", service.getTodayAvailableAndOccupiedDrivers(status));
    }

    //get weekly and monthly driver schedule
    @GetMapping(path = "weeklyAndMonthlyScheduleByDriver", params = {"id", "date"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil weeklyScheduleByDriver(@RequestParam String id, @RequestParam String date) {
        return new ResponseUtil(200, "Done", driverScheduleService.getDriverWeeklyScheduleByDate(id,date));
    }

    @GetMapping(path = "getSchedule/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil weeklyScheduleByDriver(@PathVariable String id) {
        return new ResponseUtil(200, "Done", driverScheduleService.getDriverByReservationId(id));
    }
}
