package com.lisapeillon.mareu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.lisapeillon.mareu.Model.Meeting;
import com.lisapeillon.mareu.Model.Room;
import com.lisapeillon.mareu.ViewModel.MainViewModel;
import com.lisapeillon.mareu.databinding.RecyclerviewRowBinding;
import com.lisapeillon.mareu.databinding.RecyclerviewRowRoomlistdialogBinding;

import java.util.List;

public class RoomDialogAdapter extends RecyclerView.Adapter<RoomDialogAdapter.RoomDialogViewHolder> {
          
          private Context context;
          private List<Room> roomList;
          private MainViewModel viewModel;
          private final Listener callback;
          private RecyclerviewRowRoomlistdialogBinding binding;
          private LifecycleOwner lifecycleOwner;
          
          
          // ----------------
          // --- CALLBACK ---
          // ----------------
          
          public interface Listener{
                    void onRoomSelectedListener(Room room);
          }
          
          
          
          // ---------------
          // --- ADAPTER ---
          // ---------------
          
          public RoomDialogAdapter(Listener callback, MainViewModel viewModel, LifecycleOwner lifecycleOwner){
                    this.callback = callback;
                    this.viewModel = viewModel;
                    this.lifecycleOwner = lifecycleOwner;
          }
          
          
          // -------------------
          // --- VIEW HOLDER ---
          // -------------------
          
          public class RoomDialogViewHolder extends RecyclerView.ViewHolder{
                    
                    private RecyclerviewRowRoomlistdialogBinding binding;
                    
                    public RoomDialogViewHolder(RecyclerviewRowRoomlistdialogBinding binding){
                              super(binding.getRoot());
                              this.binding = binding;
                    }
          }
          
          @NonNull
          @Override
          public RoomDialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
                    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
                    binding = RecyclerviewRowRoomlistdialogBinding.inflate(layoutInflater, parent, false);
                    return new RoomDialogViewHolder(binding);
          }
          
          @Override
          public void onBindViewHolder(RoomDialogViewHolder holder, int position){
                    Room room = roomList.get(position);
                    holder.binding.recyclerviewRowCustomlistdialogTextview.setText(room.getRoomName());
                    holder.binding.recyclerviewRowCustomlistdialogCardview.setOnClickListener(v -> callback.onRoomSelectedListener(room));
          }
          
          @Override
          public int getItemCount(){
                    return roomList.size();
          }
          
          
          // ---------------
          // --- ACTIONS ---
          // ---------------
          
          public void updateRoomList(List<Room> roomList){
                    this.roomList = roomList;
                    notifyDataSetChanged();
          }
}
