package com.lisapeillon.mareu.Model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Meeting {

          private String subject;
          private LocalTime hour;
          private Date date;
          private int roomId;
          private ArrayList<String> emails;

          // --- CONSTRUCTOR ---
          public Meeting(String subject, Date date, LocalTime hour, int roomId, ArrayList<String> emails) {
                    this.subject = subject;
                    this.date = date;
                    this.hour = hour;
                    this.roomId = roomId;
                    this.emails = emails;
          }


          // --- GETTERS ---

          public String getSubject() { return subject; }

          public LocalTime getHour() { return hour; }

          public Date getDate() { return date; }

          public int getRoomId() { return roomId; }

          public ArrayList<String> getEmails() { return emails; }
}
