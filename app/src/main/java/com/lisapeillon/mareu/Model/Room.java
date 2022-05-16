package com.lisapeillon.mareu.Model;

public class Room {

    private int roomId;
    private String roomName;


    // --- CONSTRUCTOR ---

    public Room(int roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName;
    }


    // --- GETTERS ---

    public int getRoomId() { return roomId; }

    public String getRoomName() { return roomName; }
    
    
    @Override
    public String toString() {
        return  roomName;
    }
}
