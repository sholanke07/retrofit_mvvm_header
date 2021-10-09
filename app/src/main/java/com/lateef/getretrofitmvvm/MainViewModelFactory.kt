package com.lateef.getretrofitmvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lateef.getretrofitmvvm.Repository.Repositoty
import com.lateef.getretrofitmvvm.viewModel.MainViewModel

class MainViewModelFactory(private val repositoty: Repositoty): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return MainViewModel(repositoty) as T
    }
}