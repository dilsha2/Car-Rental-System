package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.AdminDTO;
import lk.ijse.spring.entity.Admin;
import lk.ijse.spring.repo.AdminRepo;
import lk.ijse.spring.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public AdminDTO checkAdminLogIn(String userName, String password) {
        Admin admin = adminRepo.checkAdminLogIn(userName, password);
        if (!(admin == null)) {
            return mapper.map(admin, AdminDTO.class);
        } else {
            return null;
        }
    }
}
