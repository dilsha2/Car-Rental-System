package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.List;


public interface CustomerService {

    void saveCustomer(CustomerDTO customerDTO);

    void updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer (String id);

    CustomerDTO getCustomerDetail(String id);

    List<CustomerDTO> getAllCustomerDetail();

    List<CustomerDTO> getTodayRegisteredCustomers();
}
