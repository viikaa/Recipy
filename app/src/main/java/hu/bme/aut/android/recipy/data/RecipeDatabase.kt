package hu.bme.aut.android.recipy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Recipe::class], version = 2)
//@TypeConverters(value = [ShoppingItemCategory::class])
abstract class RecipeDatabase : RoomDatabase() {
    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE recipe ADD COLUMN description TEXT NOT NULL DEFAULT ''")
            }
        }

        fun getDatabase(applicationContext: Context): RecipeDatabase {
            return Room.databaseBuilder(
                applicationContext,
                RecipeDatabase::class.java,
                "recipe"
            ).addMigrations(MIGRATION_1_2)
                    .build();
        }
    }

    abstract fun recipeDao(): RecipeDAO
}