package com.HotelManagementSystem.App;

public class Room {
    private String roomType;
    private int price;

    private int room_id;

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }



    public Room(String roomType, int price, int room_id) {
        this.roomType = roomType;
        this.price = price;
        this.room_id = room_id;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Room Number: "+room_id+", Room Type: " + roomType + ", Price: " + price;
    }
}
