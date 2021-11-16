package com.parveendala.composeapp.test.basic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BasicTestViewModel : ViewModel() {
    val nameList = MutableLiveData<List<String>>()
    val nameText = MutableLiveData<String>()

    fun addNewName(newName: String?) {
        newName?.let { name ->
            nameList.postValue(nameList.value?.toMutableList()?.plus(name) ?: mutableListOf(name))
        }
    }

    fun onTextChanged(newText: String) {
        nameText.postValue(newText)
    }
}