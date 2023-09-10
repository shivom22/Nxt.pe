package com.example.nextpay.model

data class StatusCheck(
    val approve_request_txt: String,
    val approve_success_txt: String,
    val failure_icon: String,
    val success_icon: String,
    val url: String,
    val wait_time: Int
)