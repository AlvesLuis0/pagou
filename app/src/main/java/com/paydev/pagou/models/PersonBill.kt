package com.paydev.pagou.models

import java.util.Date

data class PersonBill(
    var id: Long,
    var name: String,
    var lastPayment: Date?,
    var total: Float,
)
