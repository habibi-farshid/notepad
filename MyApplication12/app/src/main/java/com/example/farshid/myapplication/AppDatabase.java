package com.example.farshid.myapplication;

import android.content.Context;

import com.example.farshid.myapplication.DataBase.Note;
import com.example.farshid.myapplication.DataBase.NoteDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1 ,exportSchema = false)
abstract class AppDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();

    public static  AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if (instance == null){

            instance = Room.databaseBuilder(context,AppDatabase.class,"note_db").allowMainThreadQueries().build();
        }

        return instance;
    }

}