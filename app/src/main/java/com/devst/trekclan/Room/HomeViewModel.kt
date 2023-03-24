package com.devst.trekclan.Room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devst.trekclan.dataapi.GenreDataClasses.GenereDataParent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.reflect.Array.get


class HomeViewModel(private val repository: HomeRepository) : ViewModel() {


    init {


        viewModelScope.launch(Dispatchers.IO) {

            repository.getTags()

        }


        val tagsvm: LiveData<GenereDataParent> = repository.quotes


    }
}