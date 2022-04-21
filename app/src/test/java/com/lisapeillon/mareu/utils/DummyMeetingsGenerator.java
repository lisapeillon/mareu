package com.lisapeillon.mareu.utils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lisapeillon.mareu.Model.Meeting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyMeetingsGenerator {
          private static List<String> emails = Arrays.asList(
                    "email1@email.com", "email2@email.com");

          public static Meeting meeting1;
          public static Meeting meeting2;
          public static Meeting meeting3;
          public static Meeting meeting4;
          public static Meeting meeting5;

          static {
                    try {
                              meeting1 = new Meeting("Conduite du changement", new SimpleDateFormat("yyyy-MM-dd").parse("2022-04-11"), LocalTime.of(16, 34), 4, new ArrayList<>(emails));
                    } catch (ParseException e) {
                              e.printStackTrace();
                    }
          }
          public static Meeting getMeeting1(){ return meeting1; };

          static {
                    try {
                              meeting2 = new Meeting("Porte ouvertes Chatbot", new SimpleDateFormat("yyy-MM-dd").parse("2022-05-24"), LocalTime.of(10, 30), 3, new ArrayList<>(emails));
                    } catch (ParseException e) {
                              e.printStackTrace();
                    }
          }
          public static Meeting getMeeting2(){ return meeting2; }

          static {
                    try {
                              meeting3 = new Meeting("Déploiement", new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-24"), LocalTime.of(8, 15), 3, new ArrayList<>(emails));
                    } catch (ParseException e) {
                              e.printStackTrace();
                    }
          }
          public static Meeting getMeeting3(){ return meeting3;}

          static {
                    try {
                              meeting4 = new Meeting("Débrieffing phase de tests", new SimpleDateFormat("yyyy-MM-dd").parse("2022-04-25"), LocalTime.of(10, 30), 5, new ArrayList<>(emails));
                    } catch (ParseException e) {
                              e.printStackTrace();
                    }
          }
          public static Meeting getMeeting4(){ return meeting4;}

          static {
                    try {
                              meeting5 = new Meeting("Formation RGPD", new SimpleDateFormat("yyyy-MM-dd").parse("2022-02-23"), LocalTime.of(14, 00), 1, new ArrayList<>(emails));
                    } catch (ParseException e) {
                              e.printStackTrace();
                    }
          }
          public static Meeting getMeeting5(){ return meeting5;}
          
}
