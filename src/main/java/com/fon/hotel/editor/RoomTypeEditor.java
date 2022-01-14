package com.fon.hotel.editor;

import com.fon.hotel.dto.RoomTypeDTO;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

@Component
public class RoomTypeEditor extends PropertyEditorSupport {

    @Autowired
    private RoomTypeService roomTypeService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            Long id = Long.parseLong(text);
            Optional<RoomTypeDTO> roomTypeDTO = roomTypeService.findById(id);
            roomTypeDTO.ifPresent(this::setValue);
            throw new IllegalArgumentException("Exception at RoomTypeEditor.setAsText: No RoomType with id = "+id);
        }catch (HotelServiceException ex){
            ex.printStackTrace();
            throw new IllegalArgumentException("Exception at RoomTypeEditor.setAsText:"+ex.getMessage());
        }
    }
}
