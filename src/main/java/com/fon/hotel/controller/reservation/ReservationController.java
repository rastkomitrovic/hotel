package com.fon.hotel.controller.reservation;

import com.fon.hotel.dto.*;
import com.fon.hotel.editor.*;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserEditor userEditor;

    @Autowired
    private RoomEditor roomEditor;

    @Autowired
    private RoomTypeEditor roomTypeEditor;

    @Autowired
    private ReservationServiceEditor reservationServiceEditor;

    @Autowired
    private DateEditor dateEditor;

    @Autowired
    private HttpSession httpSession;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, this.dateEditor);
        webDataBinder.registerCustomEditor(RoomDTO.class, this.roomEditor);
        webDataBinder.registerCustomEditor(RoomTypeDTO.class, this.roomTypeEditor);
        webDataBinder.registerCustomEditor(ReservationServiceDTO.class, this.reservationServiceEditor);
        webDataBinder.registerCustomEditor(UserDTO.class, this.userEditor);
    }

    @RequestMapping("myReservationsPage/{page}/{size}/{sort}")
    private String myReservationsPage(Model model, Principal principal, @PathVariable int page, @PathVariable int size, @PathVariable String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        Page<ReservationDTO> pageResult = reservationService.findAllForUser(pageable, principal.getName());
        model.addAttribute("isEmpty", pageResult.isEmpty());
        if (pageResult.isEmpty())
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

    @RequestMapping("employee/newReservationPage")
    public String newReservationPage(Model model, RedirectAttributes redirectAttributes) {
        try {
            setModelAttributesForNewReservation(model,new ReservationDTO());
            return "newReservationPage";
        } catch (HotelServiceException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/main";
        }
    }

    @PostMapping("/employee/saveReservation")
    public String saveReservation(@Valid @ModelAttribute("reservation") ReservationDTO reservationDTO, BindingResult bindingResult, Model model, Principal principal, RedirectAttributes redirectAttributes) throws HotelServiceException{
        try {
            if(bindingResult.hasErrors()){
                model.addAttribute("reservation",reservationDTO);
                setModelAttributesForNewReservation(model,reservationDTO);
                return "newReservationPage";
            }
            validateDatesForReservation(reservationDTO);
            prepareReservationRooms(reservationDTO);
            prepareReservationServices(reservationDTO);
            setEmployee(reservationDTO, principal);
            //reservationService.save(reservationDTO);
            redirectAttributes.addFlashAttribute("infoMessage", "Uspesno ste kreirali novu rezervaciju");
            return "redirect:/main";
        } catch (HotelServiceException ex) {
            ex.printStackTrace();
            setModelAttributesForNewReservation(model,reservationDTO);
            model.addAttribute("reservation", reservationDTO);
            model.addAttribute("errorMessage", ex.getMessage());
            return "newReservationPage";
        }
    }

    private void setModelAttributesForNewReservation(Model model, ReservationDTO reservationDTO) throws HotelServiceException{
        List<UserDTO> users;
        List<RoomTypeDTO> roomTypes;
        List<ServiceDTO> services;

        if(httpSession.getAttribute("users")!=null){
            users = (List<UserDTO>) httpSession.getAttribute("users");
        }else{
            users = userService.getAll();
            httpSession.setAttribute("users",users);
        }

        if(httpSession.getAttribute("roomTypes")!=null){
            roomTypes = (List<RoomTypeDTO>) httpSession.getAttribute("roomTypes");
        }else{
            roomTypes = roomTypeService.getAll();
            httpSession.setAttribute("roomTypes",roomTypes);
        }

        if(httpSession.getAttribute("services")!=null){
            services = (List<ServiceDTO>) httpSession.getAttribute("services");
        }else{
            services = serviceService.getAll();
            httpSession.setAttribute("services",services);
        }

        model.addAttribute("reservation", reservationDTO);
        model.addAttribute("users", users);
        model.addAttribute("roomTypes",roomTypes);
        model.addAttribute("services", services);
    }
    private void validateDatesForReservation(ReservationDTO reservationDTO) throws HotelServiceException{
        if(reservationDTO.getStartDate().after(reservationDTO.getEndDate()))
            throw new HotelServiceException("Datum pocetka rezervacije ne moze biti nakon datuma zavrsetka rezervacije");
        reservationDTO.setDateCreated(new Date());
    }
    private void prepareReservationRooms(ReservationDTO reservationDTO) throws HotelServiceException{
        if(reservationDTO.getRooms().isEmpty())
            throw new HotelServiceException("Niste izabrali nijednu sobu");
        List<RoomDTO> rooms = roomService.getAvailableRoomsForPeriod(reservationDTO.getStartDate(), reservationDTO.getEndDate());
        List<RoomDTO> roomsToSet = new LinkedList<>();
        for(RoomDTO room: reservationDTO.getRooms()){
            RoomDTO roomToRemove = null;
            for(RoomDTO roomAvailable:rooms){
                if(roomAvailable.getRoomType().equals(room.getRoomType())){
                    roomToRemove = roomAvailable;
                    roomsToSet.add(roomAvailable);
                    break;
                }
            }
            if(roomToRemove != null)
                rooms.remove(roomToRemove);
        }
        reservationDTO.setRooms(roomsToSet);
    }

    private void prepareReservationServices(ReservationDTO reservationDTO) throws HotelServiceException{
        List<ReservationServiceDTO> servicesToSet = new LinkedList<>();
        for(ReservationServiceDTO reservationServiceDTO: reservationDTO.getReservationServices()){
            boolean notFound = true;
            for(ReservationServiceDTO existing: servicesToSet){
                if(existing.getReservationServiceEmbeddedIdDTO().getService().equals(reservationServiceDTO.getReservationServiceEmbeddedIdDTO().getService())) {
                    existing.setNumberOfUsages(existing.getNumberOfUsages() + 1);
                    notFound = false;
                }
            }
            if(notFound)
                servicesToSet.add(reservationServiceDTO);
        }

        for(ReservationServiceDTO service:servicesToSet)
            service.getReservationServiceEmbeddedIdDTO().setReservation(reservationDTO);
        reservationDTO.setReservationServices(servicesToSet);
    }

    private void setEmployee(ReservationDTO reservationDTO, Principal principal) throws HotelServiceException{
        Optional<UserDTO> userDTO = userService.findByUsername(principal.getName());
        if(!userDTO.isPresent())
            throw new HotelServiceException("Greska u verifikaciji trenutno ulogovanog korisinika");
        reservationDTO.setEmployee(userDTO.get());
    }
}
