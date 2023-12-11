package fit.iuh.edu.vn.controller;

import fit.iuh.edu.vn.entity.User;
import fit.iuh.edu.vn.responsitory.RoleResponsitory;
import fit.iuh.edu.vn.responsitory.UserResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserResponsitory userResponsitory;
    @Autowired
    private RoleResponsitory roleResponsitory;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/home")
    public String getHome(Model model){
        List<User> users = userResponsitory.findAll();
        model.addAttribute("getAllUser",users);
        return "home";
    }

    @GetMapping("/admin")
    public String getAdmin(){
        return "this is admin";
    }

    @GetMapping("/index")
    public String getIndex(){
        return "this is index";
    }

    @GetMapping("/logouts")
    public String getLogout(Model model){
        return "redirect:/formlogin";
    }

    @GetMapping("/add")
    public String getAdd(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("roles",roleResponsitory.findAll());
        return "Add";
    }

    @PostMapping("/add-action")
    public String getAddAction(@ModelAttribute("user") User user){
        if(user != null){
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
            userResponsitory.save(user);
        }
        return "redirect:/home";
    }

    @GetMapping("/form-update/{userId}")
    public String getformUpdate(@PathVariable("userId") Long userId , Model model){
        Optional<User> optional = userResponsitory.findById(userId);
        if(optional.isPresent()){
            model.addAttribute("roles",roleResponsitory.findAll());
            model.addAttribute("userUpdate",optional.get());
        }
        return "Update";
    }

    @PostMapping("/update")
    public String getUpdate(@ModelAttribute("userUpdate") User user){
        if(user != null){
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
            userResponsitory.save(user);
        }
        return "redirect:/home";
    }

    @GetMapping("/delete/{userId}")
    public String getDelete(@PathVariable("userId") Long userId){
        Optional<User> optional = userResponsitory.findById(userId);
        if(optional.isPresent()){
            userResponsitory.delete(optional.get());
        }
        return "redirect:/home";

    }
}
