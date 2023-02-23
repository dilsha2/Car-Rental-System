package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
    public void updateCustomer(CustomerDTO customerDTO) {
        if (repo.existsById(customerDTO.getNic())){
            throw new RuntimeException("Customer "+customerDTO.getNic()+"Already Exists");
        }
        repo.save(mapper.map(customerDTO,Customer.class));
    }

    @Override
    public void deleteCustomer(String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Something Wrong,Cant Delete Your Details.Please Contact Admin");
        }

    }

    @Override
    public CustomerDTO getCustomerDetail(String id) {
        if (repo.existsById(id)) {
            return mapper.map(repo.findById(id).get(), CustomerDTO.class);
        } else {
            throw new RuntimeException("Something Wrong,Cant Get Your Details.Please Contact Admin");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomerDetail() {
        return mapper.map(repo.findAll(), new TypeToken<List<CustomerDTO>>() {
        }.getType());
    }

    @Override
    public List<CustomerDTO> getTodayRegisteredCustomers() {
//        return mapper.map(repo.getTodayRegisteredCustomers(), new TypeToken<List<CustomerDTO>>() {
//        }.getType());

        return null;
    }

    @Override
    public CustomerDTO checkCustomerLogIn(String user_name, String password) {
        Customer customer = repo.checkCustomerLogIn(user_name, password);
        if (!(customer == null)) {
            return mapper.map(customer, CustomerDTO.class);
        } else {
            return null;
        }
    }
}
