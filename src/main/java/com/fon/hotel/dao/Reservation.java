package com.fon.hotel.dao;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private long reservationId;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @Column(name = "total_sum", nullable = false)
    private double totalSum;

    @Column(name = "note", nullable = true)
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private User employee;

    @ManyToMany
    @JoinTable(name = "reservationroom", joinColumns = @JoinColumn(name = "reservation_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "room_id", nullable = false))
    private Set<Room> rooms;

    @OneToMany(mappedBy = "reservationServiceEmbeddedId.reservation")
    private Set<ReservationService> reservationServices;

    public Reservation() {

    }

    public Reservation(long reservationId, Date startDate, Date endDate, Date dateCreated, double totalSum, String note, User user, User employee, Set<Room> rooms, Set<ReservationService> reservationServices) {
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dateCreated = dateCreated;
        this.totalSum = totalSum;
        this.note = note;
        this.user = user;
        this.employee = employee;
        this.rooms = rooms;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Set<ReservationService> getReservationServices() {
        return reservationServices;
    }

    public void setReservationServices(Set<ReservationService> reservationServices) {
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
                ", employee=" + employee +
                ", rooms=" + rooms +
                ", reservationServices=" + reservationServices +
                '}';
    }
}
