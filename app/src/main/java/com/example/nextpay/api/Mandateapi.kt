package com.example.nextpay.api

import com.example.nextpay.model.MandateDetails
import com.example.nextpay.model.MandateDetailsX
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface Mandateapi {
    @GET("api/mandate/res.json")
    suspend  fun GetMandateDetails():Response<MandateDetails>
}