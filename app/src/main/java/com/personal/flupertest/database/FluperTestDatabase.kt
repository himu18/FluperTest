package com.personal.flupertest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.personal.flupertest.database.FluperTestDatabase.Companion.DB_VERSION
import com.personal.flupertest.database.convertor.ColorsConverter
import com.personal.flupertest.database.convertor.StoresConverters
import com.personal.flupertest.database.dao.ProductsDao
import com.personal.flupertest.database.entitiy.Products

@Database(entities = [Products::class], version = DB_VERSION, exportSchema = false)
@TypeConverters(StoresConverters::class, ColorsConverter::class)
abstract class FluperTestDatabase : RoomDatabase(){
    abstract fun getProductsDao(): ProductsDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "fluper_assignment.db"
        @Volatile
        private var INSTANCE: FluperTestDatabase? = null

        fun getInstance(context: Context?): FluperTestDatabase{
            synchronized(this){
                var instance:FluperTestDatabase?= INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context!!.applicationContext,
                        FluperTestDatabase ::class.java,
                        "fluper_assignment_database"
                    ).build()
                }
                return instance
            }

        }



        /*private fun build(context: Context?) =
            Room.databaseBuilder(context.applicationContext, FluperAssignmentDatabase::class.java, DB_NAME)
                .addMigrations(MIGRATION_1_TO_2)
                .build()

        private val MIGRATION_1_TO_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }*/
    }

}