package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;


public interface CustomerService {

    void saveCustomer(CustomerDTO customerDTO);

    String updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer (String id);

    CustomerDTO getCustomerDetail(String id);

    List<CustomerDTO> getAllCustomerDetail();

    List<CustomerDTO> getTodayRegisteredCustomers();

    CustomerDTO checkCustomerLogIn(String user_name, String password);

    void changeCustomerUsernameAndPassword(UserDTO userDTO);
}
