package com.fon.hotel.session;

import com.fon.hotel.dto.RoomTypeDTO;
import com.fon.hotel.dto.ServiceDTO;
import com.fon.hotel.dto.UserDTO;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.service.RoomTypeService;
import com.fon.hotel.service.ServiceService;
import com.fon.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private HttpSession httpSession;

    public Object getValue(SessionConstants sessionConstant, HashMap<String, Object> params) throws HotelServiceException {
        switch (sessionConstant) {
            case CURRENT_USER:
                if (httpSession.getAttribute(SessionConstants.CURRENT_USER.toString()) != null)
                    return httpSession.getAttribute(SessionConstants.CURRENT_USER.toString());
                else {
                    String username = (String) params.get("username");
                    Optional<UserDTO> userDTO = userService.findByUsername(username);
                    if(!userDTO.isPresent())
                        throw new HotelServiceException("Nije pronadjen korisnik sa korisnickim imenom "+username);
                    httpSession.setAttribute(SessionConstants.CURRENT_USER.toString(),userDTO.get());
                    return userDTO.get();
                }
            case USERS:
                if(httpSession.getAttribute(SessionConstants.USERS.toString())!=null)
                    return httpSession.getAttribute(SessionConstants.USERS.toString());
                else{
                    List<UserDTO> users = userService.getAll();
                    httpSession.setAttribute(SessionConstants.USERS.toString(),users);
                    return users;
                }
            case ROOM_TYPES:
                if(httpSession.getAttribute(SessionConstants.ROOM_TYPES.toString())!=null)
                    return httpSession.getAttribute(SessionConstants.ROOM_TYPES.toString());
                else{
                    List<RoomTypeDTO> roomTypes = roomTypeService.getAll();
                    httpSession.setAttribute(SessionConstants.ROOM_TYPES.toString(),roomTypes);
                    return roomTypes;
                }
            case SERVICES:
                if(httpSession.getAttribute(SessionConstants.SERVICES.toString())!=null)
                    return httpSession.getAttribute(SessionConstants.SERVICES.toString());
                else{
                    List<ServiceDTO> services = serviceService.getAll();
                    httpSession.setAttribute(SessionConstants.SERVICES.toString(),services);
                    return services;
                }
            default:
                return "";
        }
    }

    public void setValueToNull(SessionConstants sessionConstant){
        httpSession.setAttribute(sessionConstant.toString(),null);
    }

}
