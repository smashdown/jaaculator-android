package com.hechikasoft.jaaculator.data.local

import androidx.room.TypeConverter
import java.sql.Date
import java.sql.Timestamp

class RoomConverters {
    @TypeConverter
    fun toTimestamp(value: Long) = Date(value)

    @TypeConverter
    fun toTimeLong(timestamp: Date) = timestamp.time

//    @TypeConverter
//    fun toContentType(contentType: Int) = when (contentType) {
//        ARTICLE.code -> ARTICLE
//        YOUTUBE.code -> YOUTUBE
//        else -> throw IllegalArgumentException("Could not recognize contentType")
//    }
//
//    @TypeConverter
//    fun toInteger(contentType: ContentType) = contentType.code
//
//    @TypeConverter
//    fun toFeedType(feedType: Int) = when (feedType) {
//        MAIN.code -> MAIN
//        SAVED.code -> SAVED
//        DISMISSED.code -> DISMISSED
//        else -> throw IllegalArgumentException("Could not recognize feedType")
//    }
//
//    @TypeConverter
//    fun toInteger(feedType: FeedType) = feedType.code
}