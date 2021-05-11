package hu.bme.aut.android.recipy.data

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class RecipeRepository(context: Context) : CoroutineScope by MainScope() {

    private val recipeDao : RecipeDAO = RecipeDatabase.getDatabase(context).recipeDao()
    private val recipeList : LiveData<List<Recipe>>

    init {
        recipeList = recipeDao.getAll()
    }

    fun getById(id: Long) : Recipe = runBlocking{
        withContext(Dispatchers.IO){
            recipeDao.getById(id)
        }
    }

    fun update(recipe: Recipe) = launch{
        withContext(Dispatchers.IO) {
            recipeDao.update(recipe)
        }
    }

    fun insert(recipe: Recipe) = launch{
        withContext(Dispatchers.IO) {
            recipeDao.insert(recipe)
        }
    }

    fun delete(recipe: Recipe) = launch{
        withContext(Dispatchers.IO) {
            recipeDao.deleteItem(recipe)
        }
    }

    fun getLiveData(): LiveData<List<Recipe>> {
        return recipeList
    }
}