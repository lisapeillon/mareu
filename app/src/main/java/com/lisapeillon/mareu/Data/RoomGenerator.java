package com.lisapeillon.mareu.Data;

import com.lisapeillon.mareu.Model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class RoomGenerator {
    public static final List<Room> ROOM_GENERATOR = Arrays.asList(
            new Room(0, "Salle A"),
            new Room(1, "Salle B"),
            new Room(2, "Salle C"),
            new Room(3, "Salle D"),
            new Room(4, "Salle E"),
            new Room(5, "Salle F"),
            new Room(6, "Salle G"),
            new Room(7, "Salle H"),
            new Room(8, "Salle I"),
            new Room(9, "Salle J")
    );

    public static List<Room> generateRooms(){ return new ArrayList<>(ROOM_GENERATOR); }
}
