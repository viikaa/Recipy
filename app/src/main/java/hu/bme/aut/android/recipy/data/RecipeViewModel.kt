package hu.bme.aut.android.recipy.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class RecipeViewModel() : ViewModel(){
    private lateinit var repository : RecipeRepository
    private lateinit var recipeList : LiveData<List<Recipe>>

    fun initialise(context: Context){
        repository = RecipeRepository(context)
        recipeList = repository.getLiveData()
    }

    fun getById(id: Long): Recipe{
        return repository.getById(id)
    }

    fun getCountByCategory(category: RecipeCategory): Int{
        return repository.getCountByCategory(mapCategoryToString(category))
    }

    fun insert(recipe: Recipe){
        repository.insert(recipe)
    }

    fun update(recipe: Recipe){
        repository.update(recipe)
    }

    fun delete(recipe: Recipe){
        repository.delete(recipe)
    }

    fun getLiveData() : LiveData<List<Recipe>> {
        return recipeList
    }

    private fun mapCategoryToString(category: RecipeCategory): String{
        return when(category){
            RecipeCategory.BREAKFAST -> "BREAKFAST"
            RecipeCategory.LUNCH -> "LUNCH"
            RecipeCategory.DINNER -> "DINNER"
            RecipeCategory.DESSERT -> "DESSERT"
        }
    }
}