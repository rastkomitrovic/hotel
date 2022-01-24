package com.fon.hotel.dto;

public class RoomTypeDTO {

    private long roomTypeId;

    private String roomTypeName;

    private double pricePerDay;

    private String description;

    public RoomTypeDTO() {

    }

    public RoomTypeDTO(long roomTypeId, String roomTypeName, double pricePerDay, String description) {
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

    public String getValueForLabel(){
        return roomTypeName+" - cena po danu: "+pricePerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomTypeDTO roomType = (RoomTypeDTO) o;
        return roomTypeId == roomType.roomTypeId;
    }

    @Override
    public String toString() {
        return roomTypeName;
    }
}
