package com.fon.hotel.editor;

import com.fon.hotel.dto.ServiceDTO;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

@Component
public class ServiceEditor extends PropertyEditorSupport {

    @Autowired
    private ServiceService serviceService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try{
            Long id = Long.parseLong(text);
            Optional<ServiceDTO> serviceDTO = serviceService.findById(id);
            serviceDTO.ifPresent(this::setValue);
            throw new IllegalArgumentException("Exception at ServiceEditor.setAsText: no Service with id = "+id);
        }catch(HotelServiceException ex){
            ex.printStackTrace();
            throw new IllegalArgumentException("Exception at ServiceEditor.setAsText:"+ex.getMessage());
        }
    }
}
