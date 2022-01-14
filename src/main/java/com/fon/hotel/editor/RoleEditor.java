package com.fon.hotel.editor;

import com.fon.hotel.dto.RoleDTO;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

@Component
public class RoleEditor extends PropertyEditorSupport {

    @Autowired
    private RoleService roleService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            Long id = Long.parseLong(text);
            Optional<RoleDTO> roleDTO = roleService.findById(id);
            roleDTO.ifPresent(this::setValue);
            throw new IllegalArgumentException("Exception at RoleEditor.setAsText: no Role with id = " + id);
        } catch (HotelServiceException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Exception at RoleEditor.setAsText:" + ex.getMessage());
        }
    }
}
