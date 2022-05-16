package com.lisapeillon.mareu;

import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.custom.SimpleCustomValidation;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.lisapeillon.mareu.Injections.ViewModelFactory;
import com.lisapeillon.mareu.Model.Meeting;
import com.lisapeillon.mareu.Model.Room;
import com.lisapeillon.mareu.ViewModel.AddMeetingViewModel;
import com.lisapeillon.mareu.databinding.ActivityAddMeetingBinding;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity {

          private ActivityAddMeetingBinding binding;
          private AddMeetingViewModel viewModel;
          private ChipGroup chipGroup;
          private Room selectedRoom;
          private LocalTime selectedHour;
          private Date selectedDate;

          @Override
          protected void onCreate(Bundle savedInstanceState) {
                    binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
                    super.onCreate(savedInstanceState);
                    setContentView(binding.getRoot());
                    setTitle(R.string.add_meeting);

                    // --- CONFIGURE UI ---
                    configureViewModel();
                    initView();
          }

          @Override
          public boolean onCreateOptionsMenu(Menu menu) {
                    getMenuInflater().inflate(R.menu.menu_add_meeting, menu);
                    return true;
          }

          @Override
          public boolean onOptionsItemSelected(@NonNull MenuItem item) {
                    if (item.getItemId() == R.id.menu_add) {
                              saveNewMeeting();
                              return true;
                    }
                    return super.onOptionsItemSelected(item);
          }

          private void configureViewModel() {
                    viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(AddMeetingViewModel.class);
          }

          private void initView() {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    chipGroup = binding.activityAddmeetingChipgroup;
                    configureDatePicker();
                    configureTimePicker();
                    configureRoomSpinner();
                    configureChips();
          }

          private void configureDatePicker(){
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
                              selectedDate = new Date(calendar.getTimeInMillis());
                              binding.activityAddmeetingEdittextDatepicker.setText(year + "/" + month + "/" + day);
                    });
                    
                    binding.activityAddmeetingEdittextDatepicker.setOnClickListener(v ->builder.show());
          }


          private void configureTimePicker() {
                    // --- TimePicker Builder ---
                    MaterialTimePicker.Builder builder = new MaterialTimePicker.Builder();
                    builder.setTimeFormat(TimeFormat.CLOCK_24H);
                    MaterialTimePicker materialTimePicker = builder.build();

                    // --- Show Time Picker ---
                    binding.activityAddmeetingEdittextTimepicker.setOnClickListener(v -> {
                              materialTimePicker.show(getSupportFragmentManager(), "TIME_PICKER");
                    });

                    // --- Set time in edittext ---
                    materialTimePicker.addOnPositiveButtonClickListener(v -> {
                              binding.activityAddmeetingEdittextTimepicker.setText(materialTimePicker.getHour() + ":" + materialTimePicker.getMinute());
                              selectedHour = LocalTime.of(materialTimePicker.getHour(), materialTimePicker.getMinute());
                    });
          }

          private void configureRoomSpinner() {
                    ArrayAdapter<Room> roomSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
                    roomSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.activityAddmeetingSpinnerRoom.setAdapter(roomSpinnerAdapter);
                    binding.activityAddmeetingSpinnerRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                              @Override
                              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        selectedRoom = (Room) parent.getItemAtPosition(position);
                              }
                    });
                    viewModel.getAvailableRooms().observe(this, new Observer<List<Room>>() {
                              @Override
                              public void onChanged(List<Room> roomList) {
                                        roomSpinnerAdapter.addAll(roomList);
                              }
                    });
          }

          private void configureChips() {
                    TextInputEditText textInputEditText = binding.activityAddmeetingEdittextEmail;

                    textInputEditText.setOnEditorActionListener((textView, actionId, keyEvent) -> {
                              if (actionId == EditorInfo.IME_ACTION_DONE) {
                                        chipifyEmail(textInputEditText);
                                        textInputEditText.setText("");
                                        return true;
                              }
                              return false;
                    });
          }

          private void chipifyEmail(TextInputEditText textInputEditText) {
                    AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
                    if(validationEmail(awesomeValidation)){
                              Chip emailChip = new Chip(this);
                              emailChip.setText(textInputEditText.getText().toString());
                              emailChip.setChipIconResource(R.drawable.ic_mail_outline);
                              emailChip.setCloseIconVisible(true);
                              emailChip.setCloseIconTintResource(R.color.light_blue);
                              emailChip.setOnCloseIconClickListener(v -> chipGroup.removeView(emailChip));
                              chipGroup.addView(emailChip);
                    }
          }

          private boolean validationEmail(AwesomeValidation validation){
                    boolean result = false;
                    validation.addValidation(this, R.id.activity_addmeeting_edittext_email, Patterns.EMAIL_ADDRESS, R.string.err_format_email);
                    if (validation.validate()){
                              result = true;
                    }
                    return result;
          }

          private void saveNewMeeting() {
                    AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

                    if(awesomeValidationAddMeeting(awesomeValidation)){
                              String subject = binding.activityAddmeetingEdittextSubject.getText().toString();
                              ArrayList<String> emails = new ArrayList<>();
                              for (int i = 0; i < chipGroup.getChildCount(); i++) {
                                        String email = ((Chip) chipGroup.getChildAt(i)).getText().toString();
                                        emails.add(email);
                              }
                              Meeting meeting = new Meeting(subject, selectedDate, selectedHour, selectedRoom.getRoomId(), emails);
                              viewModel.createMeeting(meeting);
                              finish();
                    }
          }

          private boolean awesomeValidationAddMeeting(AwesomeValidation validation) {
                    boolean result = false;

                    // --- Sujet ---
                    validation.addValidation(this, R.id.activity_addmeeting_edittext_subject, "^.{2,50}", R.string.err_subject);

                    // --- Hour ---
                    validation.addValidation(this, R.id.activity_addmeeting_edittext_timepicker, "^[0-9]{1,2}:[0-9]{1,2}$", R.string.err_hour);

                    // --- Date ---
                    validation.addValidation(this, R.id.activity_addmeeting_edittext_datepicker, "^[0-9]{4}/[0-9]{1,2}/[0-9]{2}", R.string.err_date);

                    // --- Room ---
                    validation.addValidation(this, R.id.activity_addmeeting_spinner_room, new SimpleCustomValidation() {
                              @Override
                              public boolean compare(String s) {
                                        if(s.isEmpty()) return false;
                                        else return true;
                              }
                    }, R.string.err_room);

                    // --- Guests ---
                    validation.addValidation(this, R.id.activity_addmeeting_edittext_email, new SimpleCustomValidation() {
                              @Override
                              public boolean compare(String s) {
                                        if(chipGroup.getChildCount() > 1) return true;
                                        else return false;
                              }
                    }, R.string.err_emails);

                    if (validation.validate()) {
                              result = true;
                    }
                    return result;
          }

          @Override
          public boolean onSupportNavigateUp() {
                    onBackPressed();
                    return true;
          }
}