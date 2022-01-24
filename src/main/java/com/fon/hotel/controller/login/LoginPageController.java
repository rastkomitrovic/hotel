package com.fon.hotel.controller.login;

import com.fon.hotel.config.auth.HotelUserDetails;
import com.fon.hotel.dao.User;
import com.fon.hotel.dto.RoleDTO;
import com.fon.hotel.dto.UserDTO;
import com.fon.hotel.editor.RoleEditor;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Controller
public class LoginPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleEditor roleEditor;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(RoleDTO.class, this.roleEditor);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }

    @RequestMapping("/loginFailed")
    public String errorLogin(Model model) {
        model.addAttribute("user", new UserDTO());
        model.addAttribute("errorMessage", "Ne postoji korisnik sa unetim kredencijalima");
        return "loginPage";
    }

    @RequestMapping({"/logoutSuccess", "/login"})
    public String loginPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "loginPage";
    }

    @RequestMapping("/newUser")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registerPage";
    }

    @PostMapping(value = "/register")
    public String registerNewUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("user", userDTO);
                return "registerPage";
            }

            if (userDTO.getRole() == null || userDTO.getRole().getRoleName().isEmpty()) {
                userDTO.setRole(new RoleDTO(3, "USER"));
            }
            if (!userDTO.getPassword().equals(userDTO.getRepeatPassword()))
                throw new HotelServiceException("Sifre se ne podudaraju");

            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userDTO.setRepeatPassword(passwordEncoder.encode(userDTO.getRepeatPassword()));

            LocalDate locaDateOfBirth = userDTO.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (Period.between(locaDateOfBirth, LocalDate.now()).getYears() < 18)
                throw new HotelServiceException("Da biste se registrovali morate biti punoletni");

            UserDTO responseUserDto = userService.save(userDTO);
            redirectAttributes.addFlashAttribute("infoMessage", "Uspesno ste se registrovali:" + responseUserDto.getUsername());
            redirectAttributes.addFlashAttribute("user", new UserDTO());
            return "redirect:/login";
        } catch (HotelServiceException ex) {
            model.addAttribute("user", userDTO);
            model.addAttribute("errorMessage", ex.getMessage());
            return "registerPage";
        }
    }


    @RequestMapping(value = "/changeAccountInfoPage")
    public String changeAccountInfo(Model model, Principal principal) {
        try {
            Optional<UserDTO> userDTO = userService.findByUsername(principal.getName());
            if (!userDTO.isPresent())
                throw new HotelServiceException("Trenutni korisnik ne postoji");
            model.addAttribute("user", userDTO.get());
        } catch (HotelServiceException ex) {
            ex.printStackTrace();
            model.addAttribute("errorMessage", ex.getMessage());
        }
        return "changeUserInfoPage";
    }

    @PostMapping(value = "/saveAccountInfo")
    public String saveAccountInfo(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("user", userDTO);
                return "changeUserInfoPage";
            }

            Optional<UserDTO> oldUser = userService.findByUsername(principal.getName());
            if (!oldUser.isPresent())
                throw new HotelServiceException("Greska u vadjenju podataka o korisniku iz baze");

            if (!passwordEncoder.matches(userDTO.getPassword(), oldUser.get().getPassword()))
                throw new HotelServiceException("Sifra nije dobra");

            LocalDate locaDateOfBirth = userDTO.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (Period.between(locaDateOfBirth, LocalDate.now()).getYears() < 18)
                throw new HotelServiceException("Morate biti punoletni");

            userDTO.setUserId(oldUser.get().getUserId());
            userDTO.setRole(oldUser.get().getRole());
            userDTO.setPassword(oldUser.get().getPassword());
            userDTO.setUsername(principal.getName());
            UserDTO newUser = userService.update(userDTO);

            if (!oldUser.get().getUsername().equals(userDTO.getUsername())) {
                HotelUserDetails hotelUserDetails = newUser.toHotelUserDetails();
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(hotelUserDetails.getUsername(), hotelUserDetails.getPassword(), newUser.toHotelUserDetails().getAuthorities()));
            }
            redirectAttributes.addFlashAttribute("infoMessage", "Izmene su sacuvane");
            return "redirect:/main";
        } catch (HotelServiceException ex) {
            ex.printStackTrace();
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("user", userDTO);
            return "changeUserInfoPage";
        }
    }

    @RequestMapping(value = "/changePasswordPage")
    public String changePasswordPage(Model model, Principal principal) {
        try {
            Optional<UserDTO> userDTO = userService.findByUsername(principal.getName());
            if (!userDTO.isPresent())
                throw new HotelServiceException("Trenutni korisnik ne postoji");
            model.addAttribute("user", userDTO.get());
        } catch (HotelServiceException ex) {
            ex.printStackTrace();
            model.addAttribute("errorMessage", ex.getMessage());
        }
        return "changePasswordPage";
    }

    @PostMapping("/changePassword")
    public String savePassword(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("user", userDTO);
                return "changePasswordPage";
            }

            Optional<UserDTO> oldUser = userService.findByUsername(principal.getName());
            if (!oldUser.isPresent())
                throw new HotelServiceException("Greska u vadjenju podataka o korisniku iz baze");

            if (!passwordEncoder.matches(userDTO.getOldPassword(), oldUser.get().getPassword()))
                throw new HotelServiceException("Stara sifra nije dobra");

            if (!userDTO.getPassword().equals(userDTO.getRepeatPassword()))
                throw new HotelServiceException("Nove sifre se ne podudaraju");

            userService.updatePassword(principal.getName(), passwordEncoder.encode(userDTO.getPassword()));
            redirectAttributes.addFlashAttribute("infoMessage", "Uspesno ste izmenili sifru");
            return "redirect:/main";
        } catch (HotelServiceException ex) {
            ex.printStackTrace();
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("user", userDTO);
            return "changePasswordPage";
        }
    }
}
