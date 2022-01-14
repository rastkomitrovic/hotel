package com.fon.hotel.editor;

import com.fon.hotel.dto.RoomDTO;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

@Component
public class RoomEditor extends PropertyEditorSupport {

    @Autowired
    private RoomService roomService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try{
            Long id = Long.parseLong(text);
            Optional<RoomDTO> roomDTO = roomService.findById(id);
            roomDTO.ifPresent(this::setValue);
            throw new IllegalArgumentException("Exception at RoomEditor.setAsText: no Room with id = "+id);
        }catch (HotelServiceException ex){
            ex.printStackTrace();
            throw new IllegalArgumentException("Exception at RoomEditor.setAsText:"+ex.getMessage());
        }
    }
}
