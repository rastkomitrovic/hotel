package com.fon.hotel.editor;

import com.fon.hotel.dto.UserDTO;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

@Component
public class UserEditor extends PropertyEditorSupport {

    @Autowired
    private UserService userService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            Long userId = Long.parseLong(text);
            Optional<UserDTO> userDTO = userService.findById(userId);
            userDTO.ifPresent(this::setValue);
        } catch (HotelServiceException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Exception at UserEditor.setAsText:" + ex.getMessage());
        }
    }
}
