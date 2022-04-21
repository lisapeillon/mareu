package com.lisapeillon.mareu.ViewModels;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.lisapeillon.mareu.Repositories.MeetingRepository;
import com.lisapeillon.mareu.Repositories.RoomRepository;
import com.lisapeillon.mareu.ViewModel.MainViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.mockito.Mockito;

import java.util.concurrent.Executors;

public class MainViewModelTest {
          
          @Rule
          public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
          
          private MeetingRepository meetingRepository;
          private RoomRepository roomRepository;
          
          private MainViewModel mainViewModel;
          
          @Before
          public void setup(){
                    meetingRepository = Mockito.mock(MeetingRepository.class);
                    mainViewModel = new MainViewModel(meetingRepository, roomRepository, Executors.newSingleThreadExecutor());
          }
}
