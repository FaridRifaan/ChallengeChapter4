package com.binar.challengechapter4.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDAO {
    @Insert
    fun insertNote(noteEntity : NoteEntity)

    @Query("SELECT * FROM NoteEntity ORDER BY id ASC")
    fun getDataNote() : List<NoteEntity>

    @Delete
    fun deleteNote(note: NoteEntity)
    @Update
    fun updateNote(note: NoteEntity)

}