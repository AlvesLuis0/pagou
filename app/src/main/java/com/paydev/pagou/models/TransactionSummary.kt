package com.paydev.pagou.models

import java.util.Date

data class TransactionSummary(
var id: Long,
var value: Currency,
var description: String?,
var registeredAt: Date,
var isActive: Boolean,
)

