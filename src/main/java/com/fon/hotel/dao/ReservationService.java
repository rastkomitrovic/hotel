package com.fon.hotel.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reservationservice")
public class ReservationService {

    @EmbeddedId
    private ReservationServiceEmbeddedId reservationServiceEmbeddedId;

    @Column(name = "number_of_usages", nullable = false)
    private int numberOfUsages;

    public ReservationService() {

    }

    public ReservationService(Reservation reservation, Service service, int numberOfUsages, ReservationServiceEmbeddedId reservationServiceEmbeddedId) {
        this.reservationServiceEmbeddedId = reservationServiceEmbeddedId;
        this.numberOfUsages = numberOfUsages;
    }

    public ReservationServiceEmbeddedId getReservationServiceEmbeddedId() {
        return reservationServiceEmbeddedId;
    }

    public void setReservationServiceEmbeddedId(ReservationServiceEmbeddedId reservationServiceEmbeddedId) {
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
        ReservationService that = (ReservationService) o;
        return Objects.equals(reservationServiceEmbeddedId, that.reservationServiceEmbeddedId);
    }

    @Override
    public String toString() {
        return "ReservationService{" +
                "reservationServiceEmbeddedId=" + reservationServiceEmbeddedId +
                ", numberOfUsages=" + numberOfUsages +
                '}';
    }
}
