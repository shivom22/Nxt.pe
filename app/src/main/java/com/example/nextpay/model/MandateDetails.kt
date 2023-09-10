package com.example.nextpay.model

data class MandateDetails(
    val cancel_text: String,
    val page_items: List<PageItem>,
    val page_title: String,
    val proceed_txt: String,
    val status_check: StatusCheck
)