package com.fon.hotel.dto;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ReservationDTO {

    private long reservationId;

    private Date startDate;

    private Date endDate;

    private Date dateCreated;

    private double totalSum;

    private String note;

    private UserDTO user;

    private UserDTO employee;

    private List<RoomDTO> rooms;

    private List<ReservationServiceDTO> reservationServices;

    public ReservationDTO() {

    }

    public ReservationDTO(long reservationId, Date startDate, Date endDate, Date dateCreated, double totalSum, String note, UserDTO user, UserDTO employee, List<RoomDTO> rooms, List<ReservationServiceDTO> reservationServices) {
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dateCreated = dateCreated;
        this.totalSum = totalSum;
        this.note = note;
        this.user = user;
        this.employee = employee;
        this.rooms = rooms;
        if(this.rooms == null)
            this.rooms = new LinkedList<>();
        this.reservationServices = reservationServices;
        if(this.reservationServices == null)
            this.reservationServices = new LinkedList<>();
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

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
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
                ", rooms=" + rooms +
                ", reservationServices=" + reservationServices +
                '}';
    }
}
