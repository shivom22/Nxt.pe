package com.example.nextpay.repository

import com.example.nextpay.api.Mandateapi
import com.example.nextpay.model.MandateDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MandateRepository @Inject constructor(private val madateapi: Mandateapi) {
    private val _manadatedetails = MutableStateFlow<MandateDetails?>(null)
    val mandateDetails: StateFlow<MandateDetails?>
        get() = _manadatedetails


    suspend fun GetMandateDetails(){
        val response =  madateapi.GetMandateDetails()
        if (response.isSuccessful && response.body() != null){
           _manadatedetails.emit(response.body()!!)
        }
    }
}