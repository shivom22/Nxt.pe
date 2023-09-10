package com.example.nextpay.model

data class PageItem(
    val customer_linked_account: CustomerLinkedAccount,
    val mandate_details: MandateDetailsX,
    val paymentoptions: List<Paymentoption>,
    val title: String,
    val type: String
)