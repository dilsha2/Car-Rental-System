package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.UserDTO;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.entity.Rental;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.repo.RentalRepo;
import lk.ijse.spring.service.CustomerService;
import lk.ijse.spring.util.PasswordEncryptor;
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

    @Autowired
    RentalRepo rentalRepo;

    @Autowired
    PasswordEncryptor encryptor;


    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
//        if (repo.existsById(customerDTO.getNic())){
//            throw new RuntimeException("Customer "+customerDTO.getNic()+"Already Exists");
//        }
//        repo.save(mapper.map(customerDTO,Customer.class));

        if (!repo.existsById(customerDTO.getNic())) {
            String password = encryptor.getPassword(customerDTO.getPassword());
            customerDTO.setPassword(password);
            repo.save(mapper.map(customerDTO, Customer.class));
        } else {
            throw new RuntimeException("Customer Already Registered..!");
        }
    }

    @Override
    public String updateCustomer(CustomerDTO customerDTO) {
        if (repo.existsById(customerDTO.getNic())) {
            Rental carReservation = rentalRepo.checkTodayCustomerInReservationOrNot(customerDTO.getNic());
            if (carReservation == null) {
                repo.save(mapper.map(customerDTO, Customer.class));
                return "Update Details Successfully....";
            } else {
                Customer customer = repo.findById(customerDTO.getNic()).get();
                customer.setContactNumber(customerDTO.getContactNumber());
                repo.save(customer);
                return "In Our Privacy Policy Ruled,You can Update your Mobile Number Only Details till Reservation is End ";
            }
        } else {
            throw new RuntimeException("Something Wrong,Cant Update Your Details.Please Contact Admin");
        }
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
        return mapper.map(repo.getTodayRegisteredCustomers(), new TypeToken<List<CustomerDTO>>() {
        }.getType());
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

    @Override
    public void changeCustomerUsernameAndPassword(UserDTO userDTO) {
        if (repo.existsById(userDTO.getCustomer_id())) {
            Customer customer = repo.findById(userDTO.getCustomer_id()).get();

            String password = encryptor.getPassword(userDTO.getPassword());
            System.out.println(password);
            customer.setPassword(password);
            customer.setUser_name(userDTO.getUser_name());
            repo.save(customer);
        } else {
            throw new RuntimeException("Something Wrong,Cant Change Your Username & password.Please Contact Admin");
        }
    }
}
