package com.hechikasoft.jaaculator.data.local


import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.hechikasoft.jaaculator.data.entity.MemberEntity

@Dao
interface MemberDao {

//    @Query("SELECT * FROM content WHERE timestamp >= :timeframe AND feedType = :feedType ORDER BY timestamp DESC")
//    fun getMainFeedRoom(timeframe: Timestamp, feedType: FeedType): DataSource.Factory<Int, Content>
//
//    @Query("SELECT * FROM content WHERE feedType = :feedType ORDER BY timestamp DESC")
//    fun getLabeledFeedRoom(feedType: FeedType): DataSource.Factory<Int, Content>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertFeed(feed: List<Content>?)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun updateFeed(feed: List<Content>?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateContent(content: MemberEntity)
}