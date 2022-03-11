package com.lisapeillon.mareu.Model;

public class Room {

    private int roomId;
    private String roomName;
    private boolean isAvailable;


    // --- CONSTRUCTOR ---

    public Room(int roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.isAvailable = true;
    }


    // --- GETTERS ---

    public int getRoomId() { return roomId; }

    public String getRoomName() { return roomName; }

    public boolean getRoomAvailability() { return isAvailable; }


    // --- SETTERS ---

    public void setRoomId(int roomId) { this.roomId = roomId; }

    public void setRoomName(String roomName) { this.roomName = roomName; }

    public void setRoomAvailability(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return  roomName;
    }
}
