package com.lisapeillon.mareu.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lisapeillon.mareu.Data.RoomGenerator;
import com.lisapeillon.mareu.Model.Room;

import java.util.List;
import java.util.Observer;

public class RoomRepository {

          private final List<Room> rooms = RoomGenerator.generateRooms();

          public LiveData<List<Room>> getRooms(){
                    MutableLiveData<List<Room>> data = new MutableLiveData<>();
                    data.setValue(rooms);
                    return data;
          }

          public LiveData<String> getRoomName(int roomId){
                    MutableLiveData<String> roomName = new MutableLiveData<>();
                    Room room = rooms.get(roomId);
                    String roomNameStr = room.getRoomName();
                    roomName.setValue(roomNameStr);
                    return roomName;
          }
}
