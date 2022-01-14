package com.fon.hotel.dao;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservationId")
    private long reservationId;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Column(name = "endDate", nullable = false)
    private Date endDate;

    @Column(name = "totalSum", nullable = false)
    private double totalSum;

    @Column(name = "note", nullable = true)
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomId", nullable = false)
    private Room room;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservationServiceEmbeddedId.reservation")
    private List<ReservationService> reservationServices;

    public Reservation() {

    }

    public Reservation(long reservationId, Date startDate, Date endDate, double totalSum, String note, User user, Room room, List<ReservationService> reservationServices) {
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalSum = totalSum;
        this.note = note;
        this.user = user;
        this.room = room;
        this.reservationServices = reservationServices;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<ReservationService> getReservationServices() {
        return reservationServices;
    }

    public void setReservationServices(List<ReservationService> reservationServices) {
        this.reservationServices = reservationServices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservationId == that.reservationId;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalSum=" + totalSum +
                ", note='" + note + '\'' +
                ", user=" + user +
                ", room=" + room +
                ", reservationServices=" + reservationServices +
                '}';
    }
}
