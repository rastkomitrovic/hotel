package com.fon.hotel.config.auth;

import com.fon.hotel.dto.UserDTO;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class HotelUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            Optional<UserDTO> userDTO = userService.findByUsername(s);
            if (userDTO.isPresent())
                return userDTO.get().toHotelUserDetails();
        } catch (HotelServiceException ex) {
            ex.printStackTrace();
        }
        throw new UsernameNotFoundException("Ne postoji korsinik sa unetim kredencijalima");
    }
}
