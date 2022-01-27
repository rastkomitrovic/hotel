package com.fon.hotel.dto;

import java.util.Objects;

public class ReservationServiceDTO {

    private ReservationServiceEmbeddedIdDTO reservationServiceEmbeddedId;

    private int numberOfUsages;

    public ReservationServiceDTO(){

    }

    public ReservationServiceDTO(ReservationServiceEmbeddedIdDTO reservationServiceEmbeddedId, int numberOfUsages) {
        this.reservationServiceEmbeddedId = reservationServiceEmbeddedId;
        this.numberOfUsages = numberOfUsages;
    }

    public ReservationServiceEmbeddedIdDTO getReservationServiceEmbeddedId() {
        return reservationServiceEmbeddedId;
    }

    public void setReservationServiceEmbeddedId(ReservationServiceEmbeddedIdDTO reservationServiceEmbeddedId) {
        this.reservationServiceEmbeddedId = reservationServiceEmbeddedId;
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
        return Objects.equals(reservationServiceEmbeddedId, that.reservationServiceEmbeddedId);
    }

    @Override
    public String toString() {
        return "ReservationServiceDTO{" +
                "reservationServiceEmbeddedIdDTO=" + reservationServiceEmbeddedId +
                ", numberOfUsages=" + numberOfUsages +
                '}';
    }
}
