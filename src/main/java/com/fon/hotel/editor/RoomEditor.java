package com.fon.hotel.editor;

import com.fon.hotel.dto.RoomDTO;
import com.fon.hotel.dto.RoomTypeDTO;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.service.RoomService;
import com.fon.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

@Component
public class RoomEditor extends PropertyEditorSupport {

    @Autowired
    private RoomTypeService roomTypeService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            Long id = Long.parseLong(text);
            Optional<RoomTypeDTO> roomTypeDTO = roomTypeService.findById(id);
            if (roomTypeDTO.isPresent()) {
                RoomDTO roomDTO = new RoomDTO();
                roomDTO.setRoomType(roomTypeDTO.get());
                this.setValue(roomDTO);
            }
        } catch (HotelServiceException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Exception at RoomEditor.setAsText:" + ex.getMessage());
        }
    }
}
