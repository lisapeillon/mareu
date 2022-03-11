package com.lisapeillon.mareu.Injections;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.lisapeillon.mareu.Repositories.MeetingRepository;
import com.lisapeillon.mareu.Repositories.RoomRepository;
import com.lisapeillon.mareu.ViewModel.AddMeetingViewModel;
import com.lisapeillon.mareu.ViewModel.MainViewModel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final MeetingRepository meetingRepository;
    private final RoomRepository roomRepository;
    private final Executor executor;
    private static ViewModelFactory factory;

    public static ViewModelFactory getInstance(Context context){
        if(factory == null){
            synchronized (ViewModelFactory.class){
                if(factory == null){
                    factory = new ViewModelFactory(context);
                }
            }
        }
        return factory;
    }

    private ViewModelFactory(Context context){
        this.meetingRepository = new MeetingRepository();
        this.roomRepository = new RoomRepository();
        this.executor = Executors.newSingleThreadExecutor();
    }

    @Override
    @NonNull
    public <T extends ViewModel>  T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(meetingRepository, roomRepository, executor);
        } else if(modelClass.isAssignableFrom(AddMeetingViewModel.class)){
            return (T) new AddMeetingViewModel(meetingRepository, roomRepository, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
