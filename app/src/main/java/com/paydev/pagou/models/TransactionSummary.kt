package com.paydev.pagou.models

import java.util.Date

data class TransactionSummary(
var id: Long,
var value: Float,
var description: String?,
var expiredAt: Date?,
var registeredAt: Date,
var isActive: Boolean,
)

