package com.fon.hotel.controller.login;

import com.fon.hotel.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginPageController {

    @RequestMapping("/loginFailed")
    public String errorLogin(Model model){
        model.addAttribute("user",new UserDTO());
        model.addAttribute("message","Ne postoji korisnik sa unetim kredencijalima");
        return "loginPage";
    }

    @RequestMapping({"/logoutSuccess", "/login"})
    public String loginPage(Model model){
        model.addAttribute("user",new UserDTO());
        return "loginPage";
    }

}
