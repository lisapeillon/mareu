package com.lisapeillon.mareu.Injections;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.lisapeillon.mareu.Model.Meeting;
import com.lisapeillon.mareu.Repositories.MeetingRepository;
import com.lisapeillon.mareu.Repositories.RoomRepository;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DI {
          
          private static MeetingRepository sMeetingRepository = new MeetingRepository();
          public static MeetingRepository getMeetingRepository(){
                    return sMeetingRepository;
          }
          public static MeetingRepository getNewInstanceMeetingRepository() { return new MeetingRepository();}
          
          private static RoomRepository sRoomRepository = new RoomRepository();
          public static RoomRepository getRoomRepository(){
                    return sRoomRepository;
          }
          public static RoomRepository getNewInstanceRoomRepository() { return new RoomRepository(); }
}
