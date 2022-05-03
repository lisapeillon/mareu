package com.lisapeillon.mareu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.lisapeillon.mareu.Injections.ViewModelFactory;
import com.lisapeillon.mareu.Model.Meeting;
import com.lisapeillon.mareu.ViewModel.MainViewModel;
import com.lisapeillon.mareu.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MeetingsAdapter.Listener  {

          private  ActivityMainBinding binding;

          private MeetingsAdapter adapter;
          private MainViewModel viewModel;


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
                    viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MainViewModel.class);
          }

          private void configureRecyclerView(){
                    adapter = new MeetingsAdapter(this, viewModel, this);
                    binding.activityMainRecyclerview.setLayoutManager(new LinearLayoutManager(this));
                    binding.activityMainRecyclerview.setAdapter(adapter);
          }

          @Override
          public boolean onCreateOptionsMenu(Menu menu) {
                    getMenuInflater().inflate(R.menu.menu_sort_list, menu);
                    return true;
          }



          // ----------------
          // ----- DATA -----
          // ----------------

          private void getMeetingList(){
                    viewModel.getMeetingList().observe(this, meetingList -> adapter.updateMeetingList(meetingList));
          }

          private void getMeetingListSortedByDate(){
                    viewModel.getMeetingListSortedByDate().observe(this, meetingList -> adapter.updateMeetingList(meetingList));
          }

          private void getMeetingListSortedByRoom(){
                    viewModel.getMeetingListSortedByRoom().observe(this, meetingList -> adapter.updateMeetingList(meetingList));
          }



          // ---------------
          // --- ACTIONS ---
          // ---------------

          private void addMeeting(){
                    startActivity(new Intent(this, AddMeetingActivity.class));
          }

          @Override
          public boolean onOptionsItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                              case R.id.menu_sortbydate:
                                        getMeetingListSortedByDate();
                                        return true;
                              case R.id.menu_sortbyroom:
                                        getMeetingListSortedByRoom();
                                        return true;
                    }
                    return super.onOptionsItemSelected(item);
          }

          @Override
          public void onClickDeleteButton(Meeting meeting) {
                    viewModel.deleteMeeting(meeting);
                    Toast.makeText(this, "La réunion a bien été supprimée", Toast.LENGTH_SHORT).show();
          }
}