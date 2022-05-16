package com.lisapeillon.mareu.Injections;

import com.lisapeillon.mareu.Repositories.MeetingRepository;
import com.lisapeillon.mareu.Repositories.RoomRepository;

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
