package hu.bme.aut.android.recipy.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecipeDAO {
    @Query("SELECT * FROM recipe")
    fun getAll(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipe WHERE id=:id")
    fun getById(id: Long): Recipe

    @Query("SELECT COUNT(*) FROM recipe WHERE category=:category")
    fun getCountByCategory(category: String): Int

    @Insert
    suspend fun insert(recipe: Recipe): Long

    @Update
    suspend fun update(recipe: Recipe)

    @Delete
    suspend fun deleteItem(recipe: Recipe)
}