package com.paydev.pagou.models

import java.util.Date

data class PersonSummary(
    var id: Long,
    var name: String,
    var total: Float,
    var expiredAt: Date?,
)
