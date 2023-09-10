package com.example.nextpay.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nextpay.model.MandateDetails
import com.example.nextpay.repository.MandateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MandateViewModel @Inject constructor(repository: MandateRepository) :ViewModel(){
    val mandatedetails : StateFlow<MandateDetails?> =  repository.mandateDetails
    init {
        viewModelScope.launch {
            repository.GetMandateDetails()
        }
    }

}