package com.paydev.pagou.models

import androidx.room.DatabaseView
import java.util.Date

@DatabaseView("SELECT p.id, p.name, p.contact, MAX(t.registeredAt) AS lastPayment, TOTAL(t.value) AS total FROM Person p LEFT JOIN `Transaction` t ON t.personId = p.id AND t.isActive GROUP BY p.id")
data class PersonReport(
    var id: Long,
    var name: String,
    var contact: String,
    var lastPayment: Date?,
    var total: Float,
)





