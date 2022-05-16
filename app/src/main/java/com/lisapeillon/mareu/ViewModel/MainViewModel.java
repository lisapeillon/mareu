package com.lisapeillon.mareu.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.lisapeillon.mareu.Model.Meeting;
import com.lisapeillon.mareu.Model.Room;
import com.lisapeillon.mareu.Repositories.MeetingRepository;
import com.lisapeillon.mareu.Repositories.RoomRepository;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

public class MainViewModel extends ViewModel {

          private final MeetingRepository meetingRepository;
          private final RoomRepository roomRepository;
          private final Executor executor;
          
          public MainViewModel(MeetingRepository meetingRepository, RoomRepository roomRepository, Executor executor){
                    this.meetingRepository = meetingRepository;
                    this.roomRepository = roomRepository;
                    this.executor = executor;
          }

          public LiveData<List<Meeting>> getMeetingList() {
                    return meetingRepository.getMeetingList();
          }

          public LiveData<String> getRoomName(int roomId){
                    return roomRepository.getRoomName(roomId);
          }
          
          public LiveData<List<Room>> getRooms(){
                    return roomRepository.getRooms();
          }

          public void deleteMeeting(Meeting meeting) { executor.execute(() -> meetingRepository.deleteMeeting(meeting));}

          public LiveData<List<Meeting>> getMeetingListSortedByDate() { return meetingRepository.getMeetingListSortedByDate();}
          
          public LiveData<List<Meeting>> getMeetingsFilterByDate(Date date) {
                    return meetingRepository.getMeetingsFilterByDate(date);
          }

          public LiveData<List<Meeting>> getMeetingListSortedByRoom() { return  meetingRepository.getMeetingListSortedByRoom();}
          
          public LiveData<List<Meeting>> getMeetingsFilterByRoom(int roomId) {
                    return meetingRepository.getMeetingsFilterByRoom(roomId);
          }
}
