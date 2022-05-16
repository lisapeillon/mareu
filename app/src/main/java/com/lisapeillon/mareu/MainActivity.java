package com.lisapeillon.mareu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lisapeillon.mareu.Injections.ViewModelFactory;
import com.lisapeillon.mareu.Model.Meeting;
import com.lisapeillon.mareu.Model.Room;
import com.lisapeillon.mareu.ViewModel.MainViewModel;
import com.lisapeillon.mareu.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MeetingsAdapter.Listener  {
          
          private ActivityMainBinding binding;
          
          private MeetingsAdapter adapter;
          private MainViewModel viewModel;
          
          private Room[] roomArray;
          
          
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
                    getMenuInflater().inflate(R.menu.menu_filter_list, menu);
                    return true;
          }
          
          
          
          // ----------------
          // ----- DATA -----
          // ----------------
          
          private void getMeetingList(){
                    viewModel.getMeetingList().observe(this, meetingList -> adapter.updateMeetingList(meetingList));
          }
          
          private void getMeetingsSortedByDate(){
                    viewModel.getMeetingListSortedByDate().observe(this, meetingList -> adapter.updateMeetingList(meetingList));
          }
          
          private void getMeetingsFilterByDate(Date date){
                    viewModel.getMeetingsFilterByDate(date).observe(this, meetingList -> adapter.updateMeetingList(meetingList));
          }
          
          private void getMeetingsSortedByRoom() {
                    viewModel.getMeetingListSortedByRoom().observe(this, meetingList -> adapter.updateMeetingList(meetingList));
          }
          private void getMeetingsFilterByRoom(int roomId){
                    viewModel.getMeetingsFilterByRoom(roomId).observe(this, meetingList -> adapter.updateMeetingList(meetingList));
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
                                        getMeetingsSortedByDate();
                                        return true;
                              case R.id.menu_sortbyroom:
                                        getMeetingsSortedByRoom();
                                        return true;
                              case R.id.menu_filterbydate:
                                        openFilterDateCalendar();
                                        return true;
                              case R.id.menu_filterbyroom:
                                        openFilterRoomList();
                                        return true;
                              case R.id.menu_removefilters:
                                        getMeetingList();
                                        return true;
                    }
                    return super.onOptionsItemSelected(item);
          }
          
          private void openFilterRoomList(){
                    MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                              .title("Choisissez une salle");
                    viewModel.getRooms().observe(this, new Observer<List<Room>>() {
                              @Override
                              public void onChanged(List<Room> roomList) {
                                        roomArray = roomList.toArray(new Room[0]);
                              }
                    });
                    builder.items(Arrays.asList(roomArray))
                    .itemsCallback(new MaterialDialog.ListCallback() {
                              @Override
                              public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                        getMeetingsFilterByRoom(position);
                              }
                    });
                    
                    MaterialDialog dialog = builder.build();
                              dialog.show();
          }
          
          private void openFilterDateCalendar(){
                    Calendar calendar = Calendar.getInstance();
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    DatePicker datePicker = new DatePicker(this);
                    datePicker.setCalendarViewShown(false);
                    builder.setView(datePicker);
                    
                    builder.setPositiveButton("OK", (dialog, id) -> {
                              int year = datePicker.getYear();
                              int month = datePicker.getMonth();
                              int day = datePicker.getDayOfMonth();
                              calendar.set(year, month, day);
                              Date date = new Date(calendar.getTimeInMillis());
                              getMeetingsFilterByDate(date);
                    });
                    
                    builder.show();
          }
          
          
          
          // ----------------
          // --- CALLBACK ---
          // ----------------
          
          @Override
          public void onClickDeleteButton(Meeting meeting) {
                    viewModel.deleteMeeting(meeting);
                    Toast.makeText(this, "La réunion a bien été supprimée", Toast.LENGTH_SHORT).show();
          }
}