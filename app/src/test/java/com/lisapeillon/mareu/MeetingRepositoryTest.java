package com.lisapeillon.mareu;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import com.lisapeillon.mareu.Model.Meeting;
import com.lisapeillon.mareu.Repositories.MeetingRepository;
import com.lisapeillon.mareu.utils.DummyMeetingsGenerator;
import com.lisapeillon.mareu.utils.LiveDataTestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Date;
import java.util.List;

@RunWith(JUnit4.class)
public class MeetingRepositoryTest {

          private MeetingRepository repository;
          private static LiveData<List<Meeting>> meetings;

          @Rule
          public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

          @Before
          public void setup(){
                    repository = new MeetingRepository();
          }

          @Test
          public void getMeetingsWithSuccess() throws InterruptedException {
                    meetings = repository.getMeetingList();
                    LiveDataTestUtils.getValue(meetings);
                    // On veut que la liste soit vide
                    assertTrue(meetings.getValue().isEmpty());
                    // On ajoute la liste de réunions
                    repository.meetings = DummyMeetingsGenerator.generateDummyMeetings();
                    // On veut que la liste soit égale à 5
                    LiveDataTestUtils.getValue(meetings);
                    assertTrue(meetings.getValue().size() == 5);
          }

          @Test
          public void insertMeetingWithSuccess() throws InterruptedException{
                    // On veut que la liste soit vide
                    LiveData<List<Meeting>> meetings = repository.getMeetingList();
                    LiveDataTestUtils.getValue(meetings);
                    assertTrue(meetings.getValue().isEmpty());
                    // On ajoute la  réunion
                    Meeting meetingToInsert = DummyMeetingsGenerator.getMeetingToInsert();
                    repository.createMeeting(meetingToInsert);
                    // On veut que la liste soit égale à 1
                    LiveDataTestUtils.getValue(meetings);
                    assertTrue(meetings.getValue().size() == 1);
          }

          @Test
          public void deleteMeetingWithSuccess() throws InterruptedException {
                    //On ajoute la liste de réunions
                    repository.meetings = DummyMeetingsGenerator.generateDummyMeetings();
                    LiveData<List<Meeting>> meetings = repository.getMeetingList();
                    LiveDataTestUtils.getValue(meetings);
                    //On supprime une réunion
                    Meeting meetingToDelete = meetings.getValue().get(1);
                    repository.deleteMeeting(meetingToDelete);
                    //On veut que la liste la réunion supprimée ne soit plus dans la liste
                    LiveDataTestUtils.getValue(meetings);
                    assertFalse(meetings.getValue().contains(meetingToDelete));
          }

          @Test
          public void sortMeetingByDateWithSuccess() throws InterruptedException {
                    //On ajoute la liste des réunions
                    repository.meetings = DummyMeetingsGenerator.generateDummyMeetings();
                    LiveData<List<Meeting>> meetings = repository.getMeetingList();
                    LiveDataTestUtils.getValue(meetings);
                    //On vérifie que la première date est ...
                    assertTrue(meetings.getValue().get(0).getDate() == Date.valueOf("2022-04-11"));
                    //On trie la liste
                    meetings = repository.getMeetingListSortedByDate();
                    LiveDataTestUtils.getValue(meetings);
                    //On vérifie que la première date est...
                    assertTrue(meetings.getValue().get(0).getDate() == Date.valueOf("2022-01-24"));
          }

          @Test
          public void sortMeetingByRoomWithSuccess() throws InterruptedException {
                    //On ajoute la liste des réunions
                    repository.meetings = DummyMeetingsGenerator.generateDummyMeetings();
                    LiveData<List<Meeting>> meetings = repository.getMeetingList();
                    LiveDataTestUtils.getValue(meetings);
                    //On vérifie que la première salle a l'ID 5
                    assertTrue(meetings.getValue().get(0).getRoomId() == 4);
                    //On trie la liste
                    meetings = repository.getMeetingListSortedByRoom();
                    LiveDataTestUtils.getValue(meetings);
                    //On vérifie que la première salle a l'ID 1
                    assertTrue(meetings.getValue().get(0).getRoomId() == 1);
          }
}
