package com.example.farshid.myapplication.DataBase;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note ")
    List<Note> getAllNotes();

    @Insert
    void insertAll(Note... notes);

    @Delete
    void delete(Note note);


}
