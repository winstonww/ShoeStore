package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

enum class SaveState {
    SAVE,
    NOOP
}
class ShoeViewModel : ViewModel() {
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

    // data class Shoe(var name: String, var size: Double, var company: String, var description: String,
    //                val images: List<String> = mutableListOf()) : Parcelable

    fun addShoe(name: String, size: Double, company: String, description: String) {
        Timber.i("Adding shoe")
        _shoeList.value?.add(Shoe(name, size, company, description))
        Timber.i(_shoeList.value?.joinToString())
    }

    fun onEventSave(name: String, size: Double, company: String, description: String) {
        addShoe(
            name,
            size,
            company,
            description
        )
        _saveState.value = SaveState.SAVE
    }
    fun onEventSaveComplete() {
        _saveState.value = SaveState.NOOP
    }



}