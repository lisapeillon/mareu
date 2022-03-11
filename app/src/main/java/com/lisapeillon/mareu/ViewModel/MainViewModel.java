package com.lisapeillon.mareu.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.lisapeillon.mareu.Model.Meeting;
import com.lisapeillon.mareu.Repositories.MeetingRepository;
import com.lisapeillon.mareu.Repositories.RoomRepository;

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
}
