package com.fon.hotel.controller.login;

import com.fon.hotel.dao.User;
import com.fon.hotel.dto.RoleDTO;
import com.fon.hotel.dto.UserDTO;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class LoginPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/loginFailed")
    public String errorLogin(Model model){
        model.addAttribute("user",new UserDTO());
        model.addAttribute("errorMessage","Ne postoji korisnik sa unetim kredencijalima");
        return "loginPage";
    }

    @RequestMapping({"/logoutSuccess", "/login"})
    public String loginPage(Model model) throws ParseException, HotelServiceException {
        //SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        //userService.save(new UserDTO(-1,"boris",passwordEncoder.encode("BorisZivkov1996!"),"Boris","Zivkov",sdf.parse("05.07.1996"),"Marka Tajcevica 6b",new RoleDTO(1,"ADMIN")));
        model.addAttribute("user",new UserDTO());
        return "loginPage";
    }

    @RequestMapping("/newUser")
    public String registerPage(Model model){
        model.addAttribute("user",new UserDTO());
        return "registerPage";
    }

    @PostMapping(value = "/register")
    public String registerNewUser(UserDTO userDTO,Model model){
        try{
            if(userDTO.getRole()==null || userDTO.getRole().getRoleName().isEmpty()){
                userDTO.setRole(new RoleDTO(3,"USER"));
            }
            UserDTO responseUserDto = userService.save(userDTO);
            model.addAttribute("infoMessage","Uspesno ste se registrovali:"+responseUserDto.getUsername());
            model.addAttribute("user",new UserDTO());
            return "loginPage";
        }catch(Exception ex){
            model.addAttribute("user",userDTO);
            model.addAttribute("errorMessage","Vec postoji korisnik sa unetim korisnickim imenom!");
            return "forward:/newUser";
        }
    }
}
