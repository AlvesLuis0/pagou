package com.paydev.pagou.models

import androidx.room.DatabaseView
import java.util.Date

@DatabaseView ("SELECT p.id, p.name, (SELECT MIN(expiredAt) FROM `transaction` WHERE t.personId = p.id AND t.isActive = 1) AS expiredAt, TOTAL((SELECT value FROM `transaction` WHERE isActive = 1)) AS total FROM person p LEFT JOIN `transaction` t ON p.id = t.personId GROUP BY p.id")
data class PersonReport(
    var id: Long,
    var name: String,
    var expiredAt: Date?,
    var total: Float,
)





