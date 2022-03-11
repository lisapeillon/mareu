package com.lisapeillon.mareu.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Meeting {

          private int meetingId;
          private String subject;
          private LocalTime hour;
          private LocalDate date;
          private int roomId;
          private ArrayList<String> emails;

          // --- CONSTRUCTOR ---
          public Meeting(String subject, LocalDate date, LocalTime hour, int roomId, ArrayList<String> emails) {
                    this.subject = subject;
                    this.date = date;
                    this.hour = hour;
                    this.roomId = roomId;
                    this.emails = emails;
          }


          // --- GETTERS ---

          public String getSubject() { return subject; }

          public LocalTime getHour() { return hour; }

          public LocalDate getDate() { return date; }

          public int getRoomId() { return roomId; }

          public ArrayList<String> getEmails() { return emails; }



          // --- SETTERS ---

          public void setSubject(String subject) { this.subject = subject; }

          public void setHour(LocalTime hour) { this.hour = hour; }

          public void setDate(LocalDate date) { this.date = date; }

          public void setRoomId(int roomId) { this.roomId = roomId; }

          public void setEmails(ArrayList<String> emails) { this.emails = emails; }
}
