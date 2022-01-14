package com.fon.hotel.dao;

import javax.persistence.*;

@Entity
@Table(name = "roomtype")
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_id")
    private long roomTypeId;

    @Column(name = "room_type_name", nullable = false, unique = true)
    private String roomTypeName;

    @Column(name = "price_per_day", nullable = false)
    private double pricePerDay;

    @Column(name = "description", nullable = false)
    private String description;

    public RoomType() {

    }

    public RoomType(long roomTypeId, String roomTypeName, double pricePerDay, String description) {
        this.roomTypeId = roomTypeId;
        this.roomTypeName = roomTypeName;
        this.pricePerDay = pricePerDay;
        this.description = description;
    }

    public long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomType roomType = (RoomType) o;
        return roomTypeId == roomType.roomTypeId;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "roomTypeId=" + roomTypeId +
                ", roomTypeName='" + roomTypeName + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", description='" + description + '\'' +
                '}';
    }
}
