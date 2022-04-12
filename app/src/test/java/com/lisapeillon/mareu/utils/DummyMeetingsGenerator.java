package com.lisapeillon.mareu.utils;

import com.lisapeillon.mareu.Model.Meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DummyMeetingsGenerator {
          private static ArrayList<String> emails = (ArrayList<String>) Arrays.asList(
                    "email1@email.com", "email2@email.com");

          private static Date DATE;
          static {
                    try {
                              DATE = new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-28");
                    } catch (ParseException e) {
                              e.printStackTrace();
                    }
          }

          private static LocalTime HOUR = LocalTime.of(14, 30);

          public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
                    new Meeting("Sujet réunion 1", DATE, HOUR, 1, emails),
                    new Meeting("Sujet réunion 2", DATE, HOUR, 2, emails),
                    new Meeting("Sujet réunion 3", DATE, HOUR, 3, emails),
                    new Meeting("Sujet réunion 4", DATE, HOUR, 4, emails),
                    new Meeting("Sujet réunion 5", DATE, HOUR, 5, emails)
          );

          public static List<Meeting> generateDummyMeetings(){ return new ArrayList<>(DUMMY_MEETINGS); }

          private static Meeting MEETING_TO_INSERT = new Meeting("Sujet", DATE, HOUR, 6, emails);
          static Meeting getMeetingToInsert() { return MEETING_TO_INSERT; }
}
