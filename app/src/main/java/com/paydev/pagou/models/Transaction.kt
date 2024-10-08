package com.paydev.pagou.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    ignoredColumns = ["errors"],
    foreignKeys = [
        ForeignKey(entity = Person::class, parentColumns = ["id"], childColumns = ["personId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var personId: Long,
    var value: Float,
    var description: String?,
    var registeredAt: Date,
    var isActive: Boolean
): CustomEntity()