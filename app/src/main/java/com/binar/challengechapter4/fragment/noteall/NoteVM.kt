package com.binar.challengechapter4.fragment.noteall

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.binar.challengechapter4.database.DataBaseNote
import com.binar.challengechapter4.database.NoteEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteVM(app : Application) : AndroidViewModel(app) {
    lateinit var allNote : MutableLiveData<List<NoteEntity>>

    init{
        allNote = MutableLiveData()
        getAllNote()
    }
    fun getAllNoteObservers(): MutableLiveData<List<NoteEntity>> {
        return allNote
    }

    fun getAllNote() {
        GlobalScope.launch {
            val userDao = DataBaseNote.getInstance(getApplication())!!.noteDao()
            val listnote = userDao.getDataNote()
            allNote.postValue(listnote)
        }
    }

    fun deleteNote(entity: NoteEntity){
        val userDao = DataBaseNote.getInstance(getApplication())!!.noteDao()
        userDao?.deleteNote(entity)
        getAllNote()
    }

    fun updateNote(entity: NoteEntity){
        val userDao = DataBaseNote.getInstance(getApplication())!!.noteDao()
        userDao?.updateNote(entity)
        getAllNote()
    }

}