package com.hechikasoft.jaaculator.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MemberEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)