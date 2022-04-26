package com.lisapeillon.mareu.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lisapeillon.mareu.Model.Meeting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MeetingRepository {

          public List<Meeting> meetings = new ArrayList<>();
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
                    data.postValue(meetings);
                    return data;
          }

          public LiveData<List<Meeting>> getMeetingListSortedByDate(){
                    Collections.sort(meetings, new Comparator<Meeting>() {
                              @Override
                              public int compare(Meeting o1, Meeting o2) {
                                        return o1.getDate().compareTo(o2.getDate());
                              }
                    });
                    data.setValue(meetings);
                    return data;
          }

          public LiveData<List<Meeting>> getMeetingListSortedByRoom(){
                    Collections.sort(meetings, new Comparator<Meeting>() {
                              @Override
                              public int compare(Meeting o1, Meeting o2) {
                                        return String.valueOf(o1.getRoomId()).compareTo(String.valueOf(o2.getRoomId()));
                              }
                    });
                    data.setValue(meetings);
                    return data;
          }
}
