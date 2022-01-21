package com.fon.hotel.controller.reservation;

import com.fon.hotel.dto.*;
import com.fon.hotel.editor.RoomEditor;
import com.fon.hotel.editor.RoomTypeEditor;
import com.fon.hotel.editor.ServiceEditor;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.service.ReservationService;
import com.fon.hotel.service.ServiceService;
import com.fon.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomEditor roomEditor;

    @Autowired
    private RoomTypeEditor roomTypeEditor;

    @Autowired
    private ServiceEditor serviceEditor;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddd");
        sdf.setLenient(true);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
        webDataBinder.registerCustomEditor(RoomDTO.class, this.roomEditor);
        webDataBinder.registerCustomEditor(RoomTypeDTO.class, this.roomTypeEditor);
        webDataBinder.registerCustomEditor(ReservationServiceDTO.class, this.serviceEditor);
    }

    @RequestMapping("myReservationsPage/{page}/{size}/{sort}")
    private String myReservationsPage(Model model, Principal principal, @PathVariable int page, @PathVariable int size, @PathVariable String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        Page<ReservationDTO> pageResult = reservationService.findAllForUser(pageable, principal.getName());
        model.addAttribute("isEmpty", pageResult.isEmpty());
        if(pageResult.isEmpty())
            return "myReservationsPage";

        model.addAttribute("sort", sort);
        model.addAttribute("totalNumberOfFoundElements", pageResult.getTotalElements());
        model.addAttribute("isLastPage", pageResult.isLast());
        model.addAttribute("isFirstPage", pageResult.isFirst());
        model.addAttribute("totalPages", pageResult.getTotalPages());
        model.addAttribute("currentPage", pageResult.getNumber());
        model.addAttribute("reservations", pageResult.getContent());
        return "myReservationsPage";
    }

    @RequestMapping("/employee/newReservationPage")
    public String newReservationPage(Model model, RedirectAttributes redirectAttributes){
        try{
            model.addAttribute("reservation",new ReservationDTO());
            model.addAttribute("users",userService.getAll());
            model.addAttribute("services",serviceService.getAll());
            return "newReservationPage";
        }catch(HotelServiceException ex){
            redirectAttributes.addFlashAttribute("errorMessage",ex.getMessage());
            return "redirect:/main";
        }
    }

    @PostMapping("/employee/saveReservation")
    public String saveReservation(@Valid @ModelAttribute("reservation") ReservationDTO reservationDTO, Model model, Principal principal){

        model.addAttribute("infoMessage","Uspesno ste kreirali novu rezervaciju");
        return "mainPage";
    }
}
