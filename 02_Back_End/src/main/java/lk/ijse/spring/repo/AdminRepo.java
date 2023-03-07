package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepo extends JpaRepository<Admin,String> {
    @Query(value = "SELECT * FROM Admin WHERE userName=?1 AND password=?2",nativeQuery = true)
    Admin checkAdminLogIn(String userName, String password);
}
