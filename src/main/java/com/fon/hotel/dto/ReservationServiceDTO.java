package com.fon.hotel.dto;

import java.util.Objects;

public class ReservationServiceDTO {

    private ReservationServiceEmbeddedIdDTO reservationServiceEmbeddedIdDTO;

    private int numberOfUsages;

    public ReservationServiceDTO(){

    }

    public ReservationServiceDTO(ReservationServiceEmbeddedIdDTO reservationServiceEmbeddedIdDTO, int numberOfUsages) {
        this.reservationServiceEmbeddedIdDTO = reservationServiceEmbeddedIdDTO;
        this.numberOfUsages = numberOfUsages;
    }

    public ReservationServiceEmbeddedIdDTO getReservationServiceEmbeddedIdDTO() {
        return reservationServiceEmbeddedIdDTO;
    }

    public void setReservationServiceEmbeddedIdDTO(ReservationServiceEmbeddedIdDTO reservationServiceEmbeddedIdDTO) {
        this.reservationServiceEmbeddedIdDTO = reservationServiceEmbeddedIdDTO;
    }

    public int getNumberOfUsages() {
        return numberOfUsages;
    }

    public void setNumberOfUsages(int numberOfUsages) {
        this.numberOfUsages = numberOfUsages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationServiceDTO that = (ReservationServiceDTO) o;
        return Objects.equals(reservationServiceEmbeddedIdDTO, that.reservationServiceEmbeddedIdDTO);
    }

    @Override
    public String toString() {
        return "ReservationServiceDTO{" +
                "reservationServiceEmbeddedIdDTO=" + reservationServiceEmbeddedIdDTO +
                ", numberOfUsages=" + numberOfUsages +
                '}';
    }
}
