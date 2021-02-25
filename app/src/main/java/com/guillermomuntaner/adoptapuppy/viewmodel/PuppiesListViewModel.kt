package com.guillermomuntaner.adoptapuppy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guillermomuntaner.adoptapuppy.model.Puppy
import com.guillermomuntaner.adoptapuppy.model.samplePuppies

class PuppiesListViewModel : ViewModel() {
    private val _puppies = MutableLiveData(samplePuppies)
    val puppies: LiveData<List<Puppy>> = _puppies
}