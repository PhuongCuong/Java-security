package fit.iuh.edu.vn.responsitory;

import fit.iuh.edu.vn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserResponsitory extends JpaRepository<User,Long> {

    User findUserByUsername(String username);
}
