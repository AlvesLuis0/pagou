package com.paydev.pagou.models

import androidx.room.DatabaseView
import java.util.Date

@DatabaseView ("SELECT p.id, p.name, p.contact, MAX((SELECT t.registeredAt FROM `transaction` t WHERE t.personId = p.id AND t.isActive = 1)) AS lastPayment, TOTAL((SELECT t.value FROM `transaction` t WHERE t.personId = p.id AND t.isActive = 1)) AS total FROM person p LEFT JOIN `transaction` t ON p.id = t.personId GROUP BY p.id")
data class PersonReport(
    var id: Long,
    var name: String,
    var contact: String,
    var lastPayment: Date?,
    var total: Float,
)





