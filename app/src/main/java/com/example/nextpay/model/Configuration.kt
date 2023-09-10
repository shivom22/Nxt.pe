package com.example.nextpay.model

data class Configuration(
    val error_txt: ErrorTxt,
    val icon: String,
    val name: String,
    val num_cc: String,
    val num_placeholder: String,
    val otp_placeholer: String,
    val resend_txt: String,
    val send_otp_txt: String,
    val send_otp_url: String,
    val sending_static_txt: String,
    val sending_txt: String,
    val title: String,
    val verify_otp_txt: String,
    val verify_otp_url: String,
    val wait_time: Int
)