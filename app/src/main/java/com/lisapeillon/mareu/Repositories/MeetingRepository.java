package com.lisapeillon.mareu.Repositories;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lisapeillon.mareu.Data.DummyMeetingsGenerator;
import com.lisapeillon.mareu.Model.Meeting;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MeetingRepository {

          public List<Meeting> meetings = DummyMeetingsGenerator.generateMeetings();
          MutableLiveData<List<Meeting>> data = new MutableLiveData<>();

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
          
          public LiveData<List<Meeting>> getMeetingsFilterByDate(Date date){
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedSelectedDate = format.format(date);
                    List<Meeting> meetingsFilterByDate = new ArrayList<>();
                    for(Meeting meeting : meetings){
                              String formattedDate = format.format(meeting.getDate());
                              if(formattedDate.equals(formattedSelectedDate)){
                                        meetingsFilterByDate.add(meeting);
                              }
                    }
                    data.setValue(meetingsFilterByDate);
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
          
          public LiveData<List<Meeting>> getMeetingsFilterByRoom(int roomId){
                    List<Meeting> meetingsFilterByRoom = new ArrayList<>();
                    for(Meeting meeting : meetings){
                              if(meeting.getRoomId() == roomId){
                                        meetingsFilterByRoom.add(meeting);
                              }
                    }
                    data.setValue(meetingsFilterByRoom);
                    return data;
          }
}
