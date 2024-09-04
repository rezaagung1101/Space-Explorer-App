package com.spaceexplorer.data.source.local.db

//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.room.migration.Migration
//import androidx.sqlite.db.SupportSQLiteDatabase
//import com.prodia.test.spaceexplorer.domain.model.RecentSearch
//import com.spaceexplorer.data.source.local.db.ArticleDao
//
//val MIGRATION_1_2 = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        // example: adding new column to "recent_searach" table
//        database.execSQL("ALTER TABLE recent_search ADD COLUMN category TEXT NOT NULL DEFAULT ''")
//    }
//}
//
//@Database(
//    entities = [RecentSearch::class],
//    version = 1, //adjust the version of database
//    exportSchema = false,
//)
//abstract class ArticleDatabase : RoomDatabase() {
//    abstract fun articleDao(): ArticleDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: ArticleDatabase? = null
//
//        @JvmStatic
//        fun getDatabase(context: Context): ArticleDatabase {
//            return INSTANCE ?: synchronized(this) {
//                INSTANCE ?: Room.databaseBuilder(
//                    context.applicationContext,
//                    ArticleDatabase::class.java, "article_database"
//                )
////                    .fallbackToDestructiveMigration()
////                    .addMigrations(MIGRATION_1_2)
//                    .build()
//                    .also { INSTANCE = it }
//            }
//        }
//    }
//}