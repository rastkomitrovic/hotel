package com.fon.hotel.dto;

import java.util.Date;
import java.util.List;

public class ReservationDTO {

    private long reservationId;

    private Date startDate;

    private Date endDate;

    private double totalSum;

    private String note;

    private UserDTO user;

    private UserDTO employee;

    private RoomDTO room;

    private List<ReservationServiceDTO> reservationServices;

    public ReservationDTO() {

    }

    public ReservationDTO(long reservationId, Date startDate, Date endDate, double totalSum, String note, UserDTO user, UserDTO employee, RoomDTO room, List<ReservationServiceDTO> reservationServices) {
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalSum = totalSum;
        this.note = note;
        this.user = user;
        this.employee = employee;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public UserDTO getEmployee() {
        return employee;
    }

    public void setEmployee(UserDTO employee) {
        this.employee = employee;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public List<ReservationServiceDTO> getReservationServices() {
        return reservationServices;
    }

    public void setReservationServices(List<ReservationServiceDTO> reservationServices) {
        this.reservationServices = reservationServices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationDTO that = (ReservationDTO) o;
        return reservationId == that.reservationId;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "reservationId=" + reservationId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalSum=" + totalSum +
                ", note='" + note + '\'' +
                ", user=" + user +
                ", employee=" + employee +
                ", room=" + room +
                ", reservationServices=" + reservationServices +
                '}';
    }
}
