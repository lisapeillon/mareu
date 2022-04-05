package com.lisapeillon.mareu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.lisapeillon.mareu.Model.Meeting;
import com.lisapeillon.mareu.ViewModel.MainViewModel;
import com.lisapeillon.mareu.databinding.RecyclerviewRowBinding;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class MeetingsAdapter extends RecyclerView.Adapter<MeetingsAdapter.MeetingsViewHolder> {

          private Context context;
          private List<Meeting> meetingList;
          private final Listener callback;
          private RecyclerviewRowBinding binding;
          private MainViewModel viewModel;
          private LifecycleOwner lifecycleOwner;



          // ----------------
          // --- CALLBACK ---
          // ----------------

          public interface Listener{
                    void onClickDeleteButton(Meeting meeting);
          }



          // ---------------
          // --- ADAPTER ---
          // ---------------

          public MeetingsAdapter(Listener callback, MainViewModel viewModel, LifecycleOwner lifecycleOwner){
                    this.callback = callback;
                    this.lifecycleOwner = lifecycleOwner;
                    this.viewModel = viewModel;
          }



          // -------------------
          // --- VIEW HOLDER ---
          // -------------------

          public class MeetingsViewHolder extends  RecyclerView.ViewHolder{

                    private RecyclerviewRowBinding binding;

                    public MeetingsViewHolder(RecyclerviewRowBinding binding){
                              super(binding.getRoot());
                              this.binding = binding;
                    }
          }

          @NonNull
          @Override
          public MeetingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    context = parent.getContext();
                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                    binding = RecyclerviewRowBinding.inflate(layoutInflater, parent, false);
                    return new MeetingsViewHolder(binding);
          }

          @Override
          public void onBindViewHolder(@NonNull MeetingsViewHolder holder, int position) {
                    Meeting meeting = meetingList.get(position);
                    // --- Room ---
                    viewModel.getRoomName(meeting.getRoomId()).observe(lifecycleOwner,
                              roomLetter -> holder.binding.recyclerviewRowTextviewRoomletter.setText(String.valueOf(roomLetter.charAt(roomLetter.length() - 1))));
                    // --- Hour & Date---
                    LocalTime hour = meeting.getHour();
                    Date date = meeting.getDate();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String formattedDate = simpleDateFormat.format(date);
                    holder.binding.recyclerviewRowTextviewDatehour.setText(formattedDate + " - " + hour);
                    // --- Subject ---
                    holder.binding.recyclerviewRowTextviewSubject.setText(meeting.getSubject());
                    // --- Guests ---
                    List<String> guestList = meeting.getEmails();
                    StringBuilder stringBuilder = new StringBuilder();
                    for(int i = 0 ; i < guestList.size() ; i++){
                              stringBuilder.append(guestList.get(i)).append(", ");
                    }
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1).deleteCharAt(stringBuilder.length() - 1);
                    holder.binding.recyclerviewRowTextviewMails.setText(stringBuilder);
                    holder.binding.recyclerviewRowTextviewMails.setSelected(true);
                    // --- Actions ---
                    holder.binding.recyclerviewRowImageviewDelete.setOnClickListener(v -> callback.onClickDeleteButton(meeting));
          }

          @Override
          public int getItemCount() {
                    return meetingList.size();
          }



          // ---------------
          // --- ACTIONS ---
          // ---------------

          public void updateMeetingList(List<Meeting>meetingList){
                    this.meetingList = meetingList;
                    notifyDataSetChanged();
          }
}
