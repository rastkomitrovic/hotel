package com.fon.hotel.controller.login;

import com.fon.hotel.dao.User;
import com.fon.hotel.dto.RoleDTO;
import com.fon.hotel.dto.UserDTO;
import com.fon.hotel.editor.RoleEditor;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Controller
public class LoginPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleEditor roleEditor;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(RoleDTO.class,this.roleEditor);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(sdf,false));
    }

    @RequestMapping("/loginFailed")
    public String errorLogin(Model model){
        model.addAttribute("user",new UserDTO());
        model.addAttribute("errorMessage","Ne postoji korisnik sa unetim kredencijalima");
        return "loginPage";
    }

    @RequestMapping({"/logoutSuccess", "/login"})
    public String loginPage(Model model) throws ParseException, HotelServiceException {
        model.addAttribute("user",new UserDTO());
        return "loginPage";
    }

    @RequestMapping("/newUser")
    public String registerPage(Model model){
        model.addAttribute("user",new UserDTO());
        return "registerPage";
    }

    @PostMapping(value = "/register")
    public String registerNewUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Model model){
        try{
            if(bindingResult.hasErrors()) {
                model.addAttribute("user",userDTO);
                return "registerPage";
            }

            if(userDTO.getRole()==null || userDTO.getRole().getRoleName().isEmpty()){
                userDTO.setRole(new RoleDTO(3,"USER"));
            }
            if(!userDTO.getPassword().equals(userDTO.getRepeatPassword()))
                throw new HotelServiceException("Sifre se ne podudaraju");

            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userDTO.setRepeatPassword(passwordEncoder.encode(userDTO.getRepeatPassword()));

            LocalDate locaDateOfBirth = userDTO.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(Period.between(locaDateOfBirth,LocalDate.now()).getYears()<18)
                throw new HotelServiceException("Da biste se registrovali morate biti punoletni");

            UserDTO responseUserDto = userService.save(userDTO);
            model.addAttribute("infoMessage","Uspesno ste se registrovali:"+responseUserDto.getUsername());
            model.addAttribute("user",new UserDTO());
            return "loginPage";
        }catch(HotelServiceException ex){
            model.addAttribute("user",userDTO);
            model.addAttribute("errorMessage",ex.getMessage());
            return "registerPage";
        }
    }
}
