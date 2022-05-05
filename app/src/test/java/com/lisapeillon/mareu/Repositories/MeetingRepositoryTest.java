package com.lisapeillon.mareu.Repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import com.lisapeillon.mareu.Model.Meeting;
import com.lisapeillon.mareu.Data.DummyMeetingsGenerator;
import com.lisapeillon.mareu.utils.LiveDataTestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MeetingRepositoryTest {
          
          @Rule
          public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
          
          private MeetingRepository repository;
          private LiveData<List<Meeting>> meetings;
          private List<Meeting> result;
          
          @Before
          public void setup() {
                    repository = new MeetingRepository();
                    //Initialisation de la liste
                    repository.createMeeting(DummyMeetingsGenerator.getMeeting1());
                    repository.createMeeting(DummyMeetingsGenerator.getMeeting2());
                    repository.createMeeting(DummyMeetingsGenerator.getMeeting3());
                    repository.createMeeting(DummyMeetingsGenerator.getMeeting4());
                    repository.createMeeting(DummyMeetingsGenerator.getMeeting5());
          }
          
          @Test
          public void getMeetingsWithSuccess() throws InterruptedException {
                    //On récupère la liste
                    meetings = repository.getMeetingList();
                    result = LiveDataTestUtils.getValue(meetings);
                    // On veut que la liste soit égale à 5
                    assertTrue(result.size() == 5);
          }
          
          @Test
          public void insertMeetingWithSuccess() throws InterruptedException{
                    // On veut que la liste soit égale à 5
                    meetings = repository.getMeetingList();
                    result = LiveDataTestUtils.getValue(meetings);
                    assertEquals(5, result.size());
                    // On ajoute la  réunion
                    Meeting meetingToInsert = DummyMeetingsGenerator.getMeeting1();
                    repository.createMeeting(meetingToInsert);
                    // On veut que la liste soit égale à 6
                    result = LiveDataTestUtils.getValue(meetings);
                    assertEquals(6, result.size());
          }
          
          @Test
          public void deleteMeetingWithSuccess() throws InterruptedException {
                    //On récupère la liste de réunions
                    meetings = repository.getMeetingList();
                    result = LiveDataTestUtils.getValue(meetings);
                    //On supprime une réunion
                    Meeting meetingToDelete = result.get(1);
                    repository.deleteMeeting(meetingToDelete);
                    //On veut que la liste la réunion supprimée ne soit plus dans la liste
                    result = LiveDataTestUtils.getValue(meetings);
                    assertFalse(result.contains(meetingToDelete));
          }
          
          @Test
          public void sortMeetingsByDateWithSuccess() throws InterruptedException, ParseException {
                    //On récupère la liste des réunions
                    meetings = repository.getMeetingList();
                    result = LiveDataTestUtils.getValue(meetings);
                    //On vérifie que la première date est ...
                    Date expectedDateBeforeSort = new SimpleDateFormat("yyyy-MM-dd").parse("2022-04-11");
                    assertEquals(result.get(0).getDate(), expectedDateBeforeSort);
                    //On trie la liste
                    meetings = repository.getMeetingListSortedByDate();
                    result = LiveDataTestUtils.getValue(meetings);
                    //On vérifie que la première date est...
                    Date expectedDateAfterSort = new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-24");
                    assertEquals(result.get(0).getDate(), expectedDateAfterSort);
          }
          
          @Test
          public void sortMeetingsByRoomWithSuccess() throws InterruptedException {
                    //On récupère la liste des réunions
                    meetings = repository.getMeetingList();
                    result = LiveDataTestUtils.getValue(meetings);
                    //On vérifie que la première réunion a lieu dans une salle avec l'ID 4
                    assertEquals(4, result.get(0).getRoomId());
                    //On trie la liste
                    meetings = repository.getMeetingListSortedByRoom();
                    result = LiveDataTestUtils.getValue(meetings);
                    //On vérifie que la première salle a l'ID 1
                    assertEquals(1, result.get(0).getRoomId());
          }
          
          @Test
          public void filterMeetingsByRoomWithSuccess() throws InterruptedException{
                    //On récupère la liste des réunions
                    meetings = repository.getMeetingList();
                    result = LiveDataTestUtils.getValue(meetings);
                    //On vérifie que la première réunion a lieu dans une salle avec l'ID 4
                    assertEquals(4, result.get(0).getRoomId());
                    //On filtre la liste par ID 3
                    meetings = repository.getMeetingsFilterByRoom(3);
                    result = LiveDataTestUtils.getValue(meetings);
                    //On vérifie que la première et deuxième salles ont l'ID 3
                    assertEquals(3, result.get(0).getRoomId());
                    assertEquals(3, result.get(1).getRoomId());
          }
          
          @Test
          public void filterMeetingsByDateWithSuccess() throws InterruptedException, ParseException {
                    //On récupère la liste des réunions
                    meetings = repository.getMeetingList();
                    result = LiveDataTestUtils.getValue(meetings);
                    //On vérifie que la première réunion a lieu le 11/04/2022
                    Date dateBeforeFilter = result.get(0).getDate();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDateBeforeFilter = format.format(dateBeforeFilter);
                    assertEquals(formattedDateBeforeFilter, "2022-04-11");
                    //On filtre la liste sur la date 24/05/2022
                    meetings = repository.getMeetingsFilterByDate(format.parse("2022-05-24"));
                    result = LiveDataTestUtils.getValue(meetings);
                    //On vérifie que la première réunion a lieu le 24/05/2022
                    Date dateAfterFilter = result.get(0).getDate();
                    String formattedDateAfterFilter = format.format(dateAfterFilter);
                    assertEquals(formattedDateAfterFilter, "2022-05-24");
          }
}
