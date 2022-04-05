package com.lisapeillon.mareu;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.lisapeillon.mareu.Injections.ViewModelFactory;
import com.lisapeillon.mareu.Model.Meeting;
import com.lisapeillon.mareu.ViewModel.MainViewModel;
import com.lisapeillon.mareu.utils.LiveDataTestUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class MainViewModelTest {

          private MainViewModel mainViewModel;
          private Context context;


          @Before
          public void setup() {
                    context = context.getApplicationContext();
                    mainViewModel = new ViewModelProvider((ViewModelStoreOwner) this, ViewModelFactory.getInstance(context)).get(MainViewModel.class);
          }

          @Test
          public void getMeetingsWithSuccess() throws InterruptedException {
                    LiveData<List<Meeting>> meetings = mainViewModel.getMeetingList();
                    LiveDataTestUtils.getValue(meetings);
                    // On veut que la liste soit vide
                    assertTrue(meetings.getValue().isEmpty());
                    // On ajoute la liste de réunions

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

                    // On veut que la liste soit égale à 1
                    LiveDataTestUtils.getValue(meetings);
                    assertTrue(meetings.getValue().size() == 1);
          }

          @Test
          public void deleteMeetingWithSuccess() throws InterruptedException {
                    //On ajoute la liste de réunions

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
          public void sortMeetingByDateWithSuccess(){

          }

          @Test
          public void sortMeetingByRoomWithSuccess(){

          }
}