package hu.bme.aut.android.recipy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.recipy.R
import hu.bme.aut.android.recipy.data.Recipe
import hu.bme.aut.android.recipy.data.RecipeCategory
import hu.bme.aut.android.recipy.data.RecipeViewModel
import hu.bme.aut.android.recipy.databinding.FragmentEditRecipeBinding
import kotlinx.coroutines.*

class EditRecipeFragment :
        Fragment(),
        CoroutineScope by MainScope() {

    private lateinit var binding: FragmentEditRecipeBinding
    private var currentRecipe : Recipe? = null
    private val recipeViewModel : RecipeViewModel by activityViewModels()

    override fun onResume() {
        super.onResume()
        configureArrayAdapter()
    }

    private fun configureArrayAdapter() {
        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, categories)
        binding.categoryDropdown.setAdapter(arrayAdapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditRecipeBinding.inflate(inflater, container, false)

        binding.btnAddRecipe.setOnClickListener {
            if(isValid()){
                handleSave()
                findNavController().navigate(R.id.action_EditRecipeFragment_to_RecipeListFragment)
            }
        }

        val id = arguments?.getLong("id")
        if(id != null) fillRecipeData(id)

        return binding.root
    }

    private fun isValid(): Boolean{
        return  binding.etName.text.isNotEmpty() &&
                binding.etAuthor.text.isNotEmpty() &&
                binding.etTime.text.isNotEmpty()
    }

    private fun handleSave(){
        if(currentRecipe == null)
            recipeViewModel.insert(getRecipe())
        else{
            val recipeToUpdate = getRecipe()
            recipeToUpdate.id = currentRecipe!!.id
            recipeToUpdate.rating = currentRecipe!!.rating
            recipeViewModel.update(recipeToUpdate)
        }
    }

    private fun fillRecipeData(id: Long){
        currentRecipe = recipeViewModel.getById(id)
        val categories = resources.getStringArray(R.array.categories)

        binding.etName.setText(currentRecipe!!.name)
        binding.etAuthor.setText(currentRecipe!!.author)
        binding.etTime.setText(currentRecipe!!.reqiredTime.toString())
        binding.categoryDropdown.setText(
                when(currentRecipe!!.category){
                    RecipeCategory.BREAKFAST -> categories[0]
                    RecipeCategory.LUNCH -> categories[1]
                    RecipeCategory.DINNER -> categories[2]
                    RecipeCategory.DESSERT -> categories[3]
                })
    }

    private fun getRecipe(): Recipe {
        val categories = resources.getStringArray(R.array.categories)
        return Recipe(
            name = binding.etName.text.toString(),
            category = when (binding.categoryDropdown.text.toString()){
                categories[0] -> RecipeCategory.BREAKFAST
                categories[1] -> RecipeCategory.LUNCH
                categories[2] -> RecipeCategory.DINNER
                categories[3] -> RecipeCategory.DESSERT
                else -> RecipeCategory.LUNCH
            },
            author = binding.etAuthor.text.toString(),
            reqiredTime = binding.etTime.text.toString().toInt(),
            description = binding.etDescription.text.toString()
        )
    }
}