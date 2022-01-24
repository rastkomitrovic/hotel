package com.fon.hotel.dto;

public class RoomDTO {

    private long roomId;

    private int roomNumber;

    private int floor;

    private RoomTypeDTO roomType;

    public RoomDTO() {

    }

    public RoomDTO(long roomId, int roomNumber, int floor, RoomTypeDTO roomType) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.roomType = roomType;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public RoomTypeDTO getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypeDTO roomType) {
        this.roomType = roomType;
    }

    public String getValueForLabel(){
        return ""+floor+""+roomNumber+" - "+roomType.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomDTO room = (RoomDTO) o;
        return roomId == room.roomId;
    }

    @Override
    public String toString() {
        return "Sprat=" + floor + ", broj=" + roomNumber + ", tip=" + roomType.toString();
    }
}
