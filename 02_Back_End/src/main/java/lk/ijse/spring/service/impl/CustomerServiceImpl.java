package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.jws.WebParam;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo repo;

    @Autowired
    ModelMapper mapper;


    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        if (repo.existsById(customerDTO.getNic())){
            throw new RuntimeException("Customer "+customerDTO.getNic()+"Already Exists");
        }
        repo.save(mapper.map(customerDTO,Customer.class));
    }

    @Override
    public String updateCustomer(CustomerDTO customerDTO) {
//        customerRepo.save(mapper.map(customerDTO, Customer.class));
        return null;
    }

    @Override
    public void deleteCustomer(String id) {
//        customerRepo.existsById(id);

    }

    @Override
    public CustomerDTO getCustomerDetail(String id) {
        return null;
    }

    @Override
    public List<CustomerDTO> getAllCustomerDetail() {
        return null;
    }

    @Override
    public List<CustomerDTO> getTodayRegisteredCustomers() {
        return null;
    }
}
