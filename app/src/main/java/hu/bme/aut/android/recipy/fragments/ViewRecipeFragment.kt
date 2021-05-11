package hu.bme.aut.android.recipy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import hu.bme.aut.android.recipy.R
import hu.bme.aut.android.recipy.data.RecipeCategory
import hu.bme.aut.android.recipy.data.RecipeViewModel
import hu.bme.aut.android.recipy.databinding.FragmentViewRecipeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewRecipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewRecipeFragment : Fragment() {

    private lateinit var binding : FragmentViewRecipeBinding
    private val recipeViewModel : RecipeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentViewRecipeBinding.inflate(inflater, container, false)

        val id = arguments?.getLong("id")
        if(id != null) showRecipeData(id)

        return binding.root
    }

    private fun showRecipeData(id: Long) {
        val recipe = recipeViewModel.getById(id)
        val categories = resources.getStringArray(R.array.categories)

        binding.tvName.text = recipe.name
        binding.tvAuthor.text = recipe.author
        binding.tvTime.text = recipe.reqiredTime.toString()
        binding.tvCategory.text = when(recipe.category){
            RecipeCategory.BREAKFAST -> categories[0]
            RecipeCategory.LUNCH -> categories[1]
            RecipeCategory.DINNER -> categories[2]
            RecipeCategory.DESSERT -> categories[3]
        }
        binding.tvRecipe.text = recipe.description

    }

}