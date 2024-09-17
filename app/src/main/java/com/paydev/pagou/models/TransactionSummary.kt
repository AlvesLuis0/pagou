package com.paydev.pagou.models

import java.util.Date

data class TransactionSummary(
var id: Long,
var value: Float,
var description: String?,
var classification: Classification,
var expiration: Date?,
var registration: Date,
var status: Status,
)

