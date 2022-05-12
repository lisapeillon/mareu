package com.lisapeillon.mareu.Data;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.lisapeillon.mareu.Model.Meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DummyMeetingsGenerator {
          private static List<String> emails = Arrays.asList(
                    "email1@email.com", "email2@email.com");
          
          private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
          
          public static List<Meeting> DUMMY_MEETINGS;
          
           static {
                    try {
                              DUMMY_MEETINGS = Arrays.asList(
                                        new Meeting("Conduite du changement", format.parse("2022-04-11"),
                                                  LocalTime.of(16, 34), 4, new ArrayList<>(emails)),
                                        new Meeting("Porte ouvertes Chatbot", format.parse("2022-05-24"),
                                                  LocalTime.of(10, 30), 1, new ArrayList<>(emails)),
                                        new Meeting("Déploiement", format.parse("2022-01-24"),
                                                  LocalTime.of(8, 15), 3, new ArrayList<>(emails)),
                                        new Meeting("Débrieffing phase de tests", format.parse("2022-04-25"),
                                                  LocalTime.of(10, 30), 5, new ArrayList<>(emails))
                              );
                    } catch (ParseException e) {
                              e.printStackTrace();
                    }
          }
          
          public static List<Meeting> generateMeetings(){ return new ArrayList<>(DUMMY_MEETINGS);}
          
          private static Meeting MEETING_TO_INSERT;
          
          {
                    try {
                              MEETING_TO_INSERT = new Meeting("Formation RGPD", format.parse("2022-02-23"),
                                        LocalTime.of(14, 00), 3, new ArrayList<>(emails));
                    } catch (ParseException e) {
                              e.printStackTrace();
                    }
          }
          
          public static Meeting getMeetingToInsert(){
                    return MEETING_TO_INSERT;
          }
}
