package com.lisapeillon.mareu.Data;

import com.lisapeillon.mareu.Model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class RoomGenerator {
    public static List<Room> ROOM_GENERATOR = Arrays.asList(
            new Room(1, "Salle A"),
            new Room(2, "Salle B"),
            new Room(3, "Salle C"),
            new Room(4, "Salle D"),
            new Room(5, "Salle E"),
            new Room(6, "Salle F"),
            new Room(7, "Salle G"),
            new Room(8, "Salle H"),
            new Room(9, "Salle I"),
            new Room(10, "Salle J")
    );

    public static List<Room> generateRooms(){ return new ArrayList<>(ROOM_GENERATOR); }
}
