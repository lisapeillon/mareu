package com.lisapeillon.mareu.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lisapeillon.mareu.Model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MeetingRepository {

          private static List<Meeting> meetings = new ArrayList<>();
          MutableLiveData<List<Meeting>> data = new  MutableLiveData<>();

          public void createMeeting(Meeting meeting){
                    meetings.add(meeting);
                    data.postValue(meetings);
          }

          public void deleteMeeting(Meeting meeting){
                    meetings.remove(meeting);
                    data.postValue(meetings);
          }

          public LiveData<List<Meeting>> getMeetingList(){
                    data.setValue(meetings);
                    return data;
          }
}
