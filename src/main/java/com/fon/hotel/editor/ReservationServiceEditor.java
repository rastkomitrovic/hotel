package com.fon.hotel.editor;

import com.fon.hotel.dto.ReservationServiceDTO;
import com.fon.hotel.dto.ReservationServiceEmbeddedIdDTO;
import com.fon.hotel.dto.ServiceDTO;
import com.fon.hotel.exception.HotelServiceException;
import com.fon.hotel.service.ReservationService;
import com.fon.hotel.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

@Component
public class ReservationServiceEditor extends PropertyEditorSupport {

    @Autowired
    private ServiceService serviceService;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try{
            Long id = Long.parseLong(text);
            Optional<ServiceDTO> serviceDTO = serviceService.findById(id);
            if(serviceDTO.isPresent()){
                ReservationServiceDTO reservationServiceDTO = new ReservationServiceDTO();
                ReservationServiceEmbeddedIdDTO reservationServiceEmbeddedIdDTO = new ReservationServiceEmbeddedIdDTO();
                reservationServiceEmbeddedIdDTO.setService(serviceDTO.get());
                reservationServiceDTO.setReservationServiceEmbeddedIdDTO(reservationServiceEmbeddedIdDTO);
                reservationServiceDTO.setNumberOfUsages(1);
                setValue(reservationServiceDTO);
            }
        }catch(HotelServiceException ex){
            ex.printStackTrace();
            throw new IllegalArgumentException("Exception at ServiceEditor.setAsText:"+ex.getMessage());
        }
    }

}
