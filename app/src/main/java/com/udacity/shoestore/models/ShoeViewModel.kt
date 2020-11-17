package com.udacity.shoestore.models

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

enum class SaveState {
    SAVE,
    NOOP
}

class ShoeViewModel :  ViewModel() {
    private var _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList : LiveData<MutableList<Shoe>>
        get() = _shoeList

    private var _saveState = MutableLiveData<SaveState>()
    val saveState : LiveData<SaveState>
        get() = _saveState

    init {
        Timber.i("in init")
        _shoeList.value = mutableListOf()
        addShoe("Shoe10", 8.0, "Company10", "desc10")
        _saveState.value = SaveState.NOOP
    }

    fun addShoe(name: String, size: Double, company: String, description: String) {
        Timber.i("Adding shoe")
        _shoeList.value?.add(Shoe(name, size, company, description))
        Timber.i(_shoeList.value?.joinToString())
    }

    fun onEventSave(name: String, size: String, company: String, description: String) {
        Log.i("ShoeViewModel", "onEventSave name $name company $company")
        var sizeDouble : Double = 0.0
        try {
            sizeDouble = size.toDouble()
        } catch (e: NumberFormatException) {
            Timber.i("Invalid size entered")
        }
        addShoe(name, sizeDouble, company, description)
        _saveState.value = SaveState.SAVE
    }
    fun onEventSaveComplete() {
        _saveState.value = SaveState.NOOP
    }



}