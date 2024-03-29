package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.repo.DriverRepo;
import lk.ijse.spring.service.DriverService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepo repo;

    @Autowired
    ModelMapper mapper;

    @Override
    public DriverDTO checkDriverLogIn(String name, String password) {
        Driver driver = repo.checkDriverLogIn(name, password);
        if (!(driver == null)) {
            return mapper.map(driver, DriverDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void saveDriver(DriverDTO driverDTO) {
        if (repo.existsById(driverDTO.getDriverNic())){
            throw new RuntimeException("Driver "+driverDTO.getDriverNic()+"Already exists");

        }
        repo.save(mapper.map(driverDTO, Driver.class));
    }

    @Override
    public void UpdateDriver(DriverDTO driverDTO) {
        if (repo.existsById(driverDTO.getDriverNic())) {

            repo.save(mapper.map(driverDTO, Driver.class));
        } else {
            throw new RuntimeException("Update Failed.!  This Driver's Previous Record is Missing..Add Again");
        }
    }

    @Override
    public void deleteDriver(String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Can't Delete.!  This Driver's Previous Record is Missing..Add Again");
        }
    }

    @Override
    public DriverDTO getDriverDetail(String id) {
        if (repo.existsById(id)) {
            return mapper.map(repo.findById(id).get(), DriverDTO.class);
        }
        throw new RuntimeException("Can't Get Details.!  This Driver's Previous Record is Missing..Add Again");
    }

    @Override
    public List<DriverDTO> getAllDriverDetail() {
        return mapper.map(repo.findAll(), new TypeToken<List<DriverDTO>>() {
        }.getType());
    }

    @Override
    public List<DriverDTO> getTodayAvailableAndOccupiedDrivers(String status) {
        return null;
    }
}
