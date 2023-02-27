package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.service.CarService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepo repo;

    @Autowired
    ModelMapper mapper;

    @Override
    public void saveCar(CarDTO carDTO) {
        if (repo.existsById(carDTO.getRegistrationId())){
            throw new RuntimeException("Car "+carDTO.getRegistrationId()+"Already Exists");
        }
        repo.save(mapper.map(carDTO, Car.class));
    }

    @Override
    public void updateCar(CarDTO carDTO) {
        if (repo.existsById(carDTO.getRegistrationId())) {
            repo.save(mapper.map(carDTO, Car.class));
        } else {
            throw new RuntimeException("Update Failed.!  This Vehicle's Previous Record is Missing..Add Again");
        }
    }

    @Override
    public void deleteCar(String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Can't Delete.!  This Vehicle's Previous Record is Missing..Add Again");
        }
    }

    @Override
    public CarDTO getCarDetail(String id) {
        if (repo.existsById(id)) {
            return mapper.map(repo.findById(id).get(), CarDTO.class);
        } else {
            throw new RuntimeException("Can't Get Details.!  This Vehicle's Previous Record is Missing..Add Again");
        }
    }

    @Override
    public List<CarDTO> getAllCarDetail() {
        return mapper.map(repo.findAll(), new TypeToken<List<CarDTO>>() {
        }.getType());
    }

    @Override
    public List<CarDTO> getCarsUnderMaintain() {
        return mapper.map(repo.getCarsUnderMaintain(), new TypeToken<List<CarDTO>>() {
        }.getType());
    }

    @Override
    public List<CarDTO> getCarsNeedMaintain() {
        return mapper.map(repo.getCarsNeedMaintain(), new TypeToken<List<CarDTO>>() {
        }.getType());
    }

    @Override
    public List<CarDTO> getUnavailableOrAvailableCarsByStatus(String status) {
        return mapper.map(repo.getUnavailableOrAvailableCarsByStatus(status), new TypeToken<List<CarDTO>>() {
        }.getType());
    }
    @Override
    public List<CarDTO> getAvailableAndRentalCarsForReservation(String pick_up_date, String return_date, String status) {
        if ((pick_up_date.isEmpty() || return_date.isEmpty()) && pick_up_date.isEmpty()) {
            throw new RuntimeException("Please Select date for Show Available Vehicles.!");
        }
        //check the status for filter results of cars as rental and available
        if (status.equals("Available")) {
            if (return_date.isEmpty()) {
                return mapper.map(repo.getAvailableCarsForReservationOnDay(pick_up_date), new TypeToken<List<CarDTO>>() {
                }.getType());
            } else {
                return mapper.map(repo.getAvailableCarsForReservation(pick_up_date, return_date), new TypeToken<List<CarDTO>>() {
                }.getType());
            }
        } else if (status.equals("Rental")) {
            if (return_date.isEmpty()) {
                return mapper.map(repo.getRentalCarsOnDay(pick_up_date), new TypeToken<List<CarDTO>>() {
                }.getType());
            } else {
                return mapper.map(repo.getRentalCars(pick_up_date, return_date), new TypeToken<List<CarDTO>>() {
                }.getType());
            }
        } else {
            throw new RuntimeException("Select Available Or Rental Cars You Want");
        }
    }

    @Override
    public void setCarStatusUnavailableOrAvailable(String id, String status) {
        if (repo.existsById(id)) {
            Car car = repo.findById(id).get();
            car.setAvailability(status);
            repo.save(car);
        } else {
            throw new RuntimeException("Can't Get Details.!  This Vehicle's Previous Record is Missing..Add Again");

        }
    }
    @Override
    public List<CarDTO> sortCarsByAttributes(CarDTO carDTO) {
        return mapper.map(repo.sortCarsByAttributes(carDTO.getTransmissionType(), carDTO.getBrand()
                , carDTO.getType(), carDTO.getFuelType(), carDTO.getNoOfPassenger()), new TypeToken<List<CarDTO>>() {
        }.getType());
    }
}
