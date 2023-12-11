package fit.iuh.edu.vn.controller;

import fit.iuh.edu.vn.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/formlogin")
    public String formlogin(Model model)
    {
        return "login";
    }
}
