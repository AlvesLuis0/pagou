package com.paydev.pagou.models

import java.util.Date

data class PersonBill(
    var id: Long,
    var name: String,
    var contact: String,
    var others: String?,
    var lastPayment: Date?,
    var total: Currency,
)
