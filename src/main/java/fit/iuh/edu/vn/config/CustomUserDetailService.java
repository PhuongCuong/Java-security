package fit.iuh.edu.vn.config;

import fit.iuh.edu.vn.entity.User;
import fit.iuh.edu.vn.responsitory.UserResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserResponsitory userResponsitory;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userResponsitory.findUserByUsername(username);
        System.out.println(user);
        if(user == null){
            throw new UsernameNotFoundException("not found user");
        }
        return new CustomUserDetail(user);
    }
}
