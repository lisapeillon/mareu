package com.lisapeillon.mareu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.lisapeillon.mareu.Injections.ViewModelFactory;
import com.lisapeillon.mareu.Model.Meeting;
import com.lisapeillon.mareu.ViewModel.MainViewModel;
import com.lisapeillon.mareu.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MeetingsAdapter.Listener {

          private  ActivityMainBinding binding;

          private MeetingsAdapter adapter;
          private MainViewModel viewModel;
          private LiveData<List<Meeting>> meetingList;


          @Override
          protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    binding = ActivityMainBinding.inflate(getLayoutInflater());
                    setContentView(binding.getRoot());

                    // --- Configure UI ---
                    configureViewModel();
                    configureRecyclerView();
                    getMeetingList();

                    binding.activityMainFab.setOnClickListener(v -> addMeeting());
          }


          // ---------------
          // ------UI -------
          // ---------------

          private void configureViewModel(){
                    viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(MainViewModel.class);
          }

          private void configureRecyclerView(){
                    adapter = new MeetingsAdapter(this, viewModel, this);
                    binding.activityMainRecyclerview.setLayoutManager(new LinearLayoutManager(this));
                    binding.activityMainRecyclerview.setAdapter(adapter);
          }


          // ----------------
          // ----- DATA -----
          // ----------------

          private void getMeetingList(){
                    viewModel.getMeetingList().observe(this, meetingList -> adapter.updateMeetingList(meetingList));
          }



          // ---------------
          // --- ACTIONS ---
          // ---------------

          private void addMeeting(){
                    startActivity(new Intent(this, AddMeetingActivity.class));
          }

          @Override
          public void onClickDeleteButton(Meeting meeting) {
                    // TODO
                    Toast.makeText(this, "Supprimer la réunion sur " + meeting.getSubject(), Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onClickDetails(Meeting meeting){
                    // TODO
                    Toast.makeText(this, "Voir les détails de la réunion sur " + meeting.getSubject(), Toast.LENGTH_SHORT).show();
          }
}