package com.lisapeillon.mareu;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import androidx.lifecycle.LiveData;
import androidx.test.core.app.ApplicationProvider;

import com.lisapeillon.mareu.Model.Meeting;
import com.lisapeillon.mareu.Repositories.MeetingRepository;
import com.lisapeillon.mareu.Repositories.RoomRepository;
import com.lisapeillon.mareu.ViewModel.MainViewModel;
import com.lisapeillon.mareu.utils.DummyMeetingsGenerator;
import com.lisapeillon.mareu.utils.LiveDataTestUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Date;
import java.util.List;

@RunWith(JUnit4.class)
public class MainViewModelTest {

          private MainViewModel mainViewModel;
          private MeetingRepository meetingRepository;
          private RoomRepository roomRepository;


          @Before
          public void setup() {
                    mainViewModel = new MainViewModel(meetingRepository, roomRepository, ApplicationProvider.getApplicationContext().getMainExecutor());
                //    mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this, ViewModelFactory.getInstance(context.getApplicationContext())).get(MainViewModel.class);
          }

          @Test
          public void getMeetingsWithSuccess() throws InterruptedException {
                    LiveData<List<Meeting>> meetings = mainViewModel.getMeetingList();
                    LiveDataTestUtils.getValue(meetings);
                    // On veut que la liste soit vide
                    assertTrue(meetings.getValue().isEmpty());
                    // On ajoute la liste de réunions
                    mainViewModel.getMeetingRepository().meetings = DummyMeetingsGenerator.generateDummyMeetings();
                    // On veut que la liste soit égale à 5
                    LiveDataTestUtils.getValue(meetings);
                    assertTrue(meetings.getValue().size() == 5);
          }

          @Test
          public void insertMeetingWithSuccess() throws InterruptedException{
                    // On veut que la liste soit vide
                    LiveData<List<Meeting>> meetings = mainViewModel.getMeetingList();
                    LiveDataTestUtils.getValue(meetings);
                    assertTrue(meetings.getValue().isEmpty());
                    // On ajoute la  réunion
                    mainViewModel.getMeetingRepository().meetings = DummyMeetingsGenerator.getMeetingToInsert();
                    // On veut que la liste soit égale à 1
                    LiveDataTestUtils.getValue(meetings);
                    assertTrue(meetings.getValue().size() == 1);
          }

          @Test
          public void deleteMeetingWithSuccess() throws InterruptedException {
                    //On ajoute la liste de réunions
                    mainViewModel.getMeetingRepository().meetings = DummyMeetingsGenerator.generateDummyMeetings();
                    LiveData<List<Meeting>> meetings = mainViewModel.getMeetingList();
                    LiveDataTestUtils.getValue(meetings);
                    //On supprime une réunion
                    Meeting meetingToDelete = meetings.getValue().get(1);
                    mainViewModel.deleteMeeting(meetingToDelete);
                    //On veut que la liste la réunion supprimée ne soit plus dans la liste
                    LiveDataTestUtils.getValue(meetings);
                    assertFalse(meetings.getValue().contains(meetingToDelete));
          }

          @Test
          public void sortMeetingByDateWithSuccess() throws InterruptedException {
                    //On ajoute la liste des réunions
                    mainViewModel.getMeetingRepository().meetings = DummyMeetingsGenerator.generateDummyMeetings();
                    LiveData<List<Meeting>> meetings = mainViewModel.getMeetingList();
                    LiveDataTestUtils.getValue(meetings);
                    //On vérifie que la première date est ...
                    assertTrue(meetings.getValue().get(0).getDate() == Date.valueOf("2022-04-11"));
                    //On trie la liste
                    meetings = mainViewModel.getMeetingListSortedByDate();
                    LiveDataTestUtils.getValue(meetings);
                    //On vérifie que la première date est...
                    assertTrue(meetings.getValue().get(0).getDate() == Date.valueOf("2022-01-24"));
          }

          @Test
          public void sortMeetingByRoomWithSuccess() throws InterruptedException {
                    //On ajoute la liste des réunions
                    mainViewModel.getMeetingRepository().meetings = DummyMeetingsGenerator.generateDummyMeetings();
                    LiveData<List<Meeting>> meetings = mainViewModel.getMeetingList();
                    LiveDataTestUtils.getValue(meetings);
                    //On vérifie que la première salle a l'ID 5
                    assertTrue(meetings.getValue().get(0).getRoomId() == 4);
                    //On trie la liste
                    meetings = mainViewModel.getMeetingListSortedByRoom();
                    LiveDataTestUtils.getValue(meetings);
                    //On vérifie que la première salle a l'ID 1
                    assertTrue(meetings.getValue().get(0).getRoomId() == 1);
          }
}