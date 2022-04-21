package com.lisapeillon.mareu.Repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import com.lisapeillon.mareu.Model.Room;
import com.lisapeillon.mareu.Repositories.RoomRepository;
import com.lisapeillon.mareu.utils.LiveDataTestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class RoomRepositoryTest {
          
          @Rule
          public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
          
          private RoomRepository repository;
          private LiveData<List<Room>> rooms;
          private List<Room> result;
          
          @Before
          public void setup(){
                    repository = new RoomRepository();
          }
          
          @Test
          public void getRoomsWithSuccess() throws InterruptedException {
                    rooms = repository.getRooms();
                    result = LiveDataTestUtils.getValue(rooms);
                    assertEquals(10, result.size());
          }
          
          @Test
          public void getRoomNamesWithSuccess() throws InterruptedException{
                    rooms = repository.getRooms();
                    result = LiveDataTestUtils.getValue(rooms);
                    LiveData<String> roomName = repository.getRoomName(result.get(0).getRoomId());
                    assertEquals(roomName.getValue(), "Salle A");
          }
}
