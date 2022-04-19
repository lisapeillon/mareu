package com.lisapeillon.mareu.utils;

import com.lisapeillon.mareu.Model.Meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyMeetingsGenerator {
          private static ArrayList<String> emails = (ArrayList<String>) Arrays.asList(
                    "email1@email.com", "email2@email.com");

          public static List<Meeting> DUMMY_MEETINGS;

          static {
                    try {
                              DUMMY_MEETINGS = Arrays.asList(
                                        new Meeting("Conduite du changement", new SimpleDateFormat("yyyy-MM-dd").parse("2022-04-11"), LocalTime.of(16, 34), 4, emails),
                                        new Meeting("Porte ouvertes Chatbot", new SimpleDateFormat("yyy-MM-dd").parse("2022-05-24"), LocalTime.of(10, 30), 3, emails),
                                        new Meeting("Déploiement", new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-24"), LocalTime.of(8, 15), 3, emails),
                                        new Meeting("Débrieffing phase de tests", new SimpleDateFormat("yyyy-MM-dd").parse("2022-04-25"), LocalTime.of(10, 30), 5, emails),
                                        new Meeting("Formation RGPD", new SimpleDateFormat("yyyy-MM-dd").parse("2022-02-23"), LocalTime.of(14, 00), 1, emails)
                              );
                    } catch (ParseException e) {
                              e.printStackTrace();
                    }
          }

          public DummyMeetingsGenerator() throws ParseException {}

          public static List<Meeting> generateDummyMeetings(){ return new ArrayList<>(DUMMY_MEETINGS); }

          private static Meeting MEETING_TO_INSERT;

          static {
                    try {
                              MEETING_TO_INSERT = new Meeting("CoPil ChatBot", new SimpleDateFormat("yyyy-MM-dd").parse("2022-06-22"), LocalTime.of(13, 30), 6, emails);
                    } catch (ParseException e) {
                              e.printStackTrace();
                    }
          }

          public static Meeting getMeetingToInsert() { return MEETING_TO_INSERT; }
}
