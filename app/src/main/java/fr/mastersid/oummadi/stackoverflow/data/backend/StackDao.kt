package fr.mastersid.oummadi.stackoverflow.data.backend

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.mastersid.oummadi.stackoverflow.data.Item
import fr.mastersid.oummadi.stackoverflow.data.QuestionX
import javax.inject.Singleton

@Dao
interface StackDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list : List<Item>)

    @Query("SELECT * FROM question_table")
    fun getQuestionsList(): LiveData<List<Item>>
}

@Database(
    entities = [Item::class],
    version = 1,
    exportSchema = false
)
abstract class QuestionRoomDatabase: RoomDatabase(){
        abstract fun questionDao(): StackDao
}

@InstallIn(SingletonComponent::class)
@Module
object QuestionRoomDatabaseModule{

    @Provides
    fun provideQuestionDao(mQuestionRoomDataBase : QuestionRoomDatabase): StackDao{
        return mQuestionRoomDataBase.questionDao()
    }

    @Provides
    @Singleton
    fun provideWeatherRoomDatabase(@ApplicationContext appContext : Context): QuestionRoomDatabase{
        return Room.databaseBuilder(
            appContext.applicationContext,
            QuestionRoomDatabase::class.java,
            "question_table"
        ).build()
    }
}