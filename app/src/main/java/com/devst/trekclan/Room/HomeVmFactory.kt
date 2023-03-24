package com.devst.trekclan.Room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class HomeVmFactory(private val repository: HomeRepository) : ViewModelProvider.Factory {

    //inbuilt
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }

}