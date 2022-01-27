package com.fon.hotel.dto;

import java.util.Objects;

public class ReservationServiceEmbeddedIdDTO {

    private ReservationDTO reservation;

    private ServiceDTO service;

    public ReservationServiceEmbeddedIdDTO() {

    }

    public ReservationServiceEmbeddedIdDTO(ReservationDTO reservation, ServiceDTO service) {
        this.reservation = reservation;
        this.service = service;
    }

    public ReservationDTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationDTO reservation) {
        this.reservation = reservation;
    }

    public ServiceDTO getService() {
        return service;
    }

    public void setService(ServiceDTO service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationServiceEmbeddedIdDTO that = (ReservationServiceEmbeddedIdDTO) o;
        return Objects.equals(reservation, that.reservation) &&
                Objects.equals(service, that.service);
    }

    @Override
    public String toString() {
        return "ReservationServiceEmbeddedIdDTO{" +
                ", serviceDTO=" + service +
                '}';
    }
}
