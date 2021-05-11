package hu.bme.aut.android.recipy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], version = 1)
//@TypeConverters(value = [ShoppingItemCategory::class])
abstract class RecipeDatabase : RoomDatabase() {
    companion object {
        fun getDatabase(applicationContext: Context): RecipeDatabase {
            return Room.databaseBuilder(
                applicationContext,
                RecipeDatabase::class.java,
                "recipe"
            ).build();
        }
    }

    abstract fun recipeDao(): RecipeDAO
}