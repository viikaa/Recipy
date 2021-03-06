package hu.bme.aut.android.recipy.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import hu.bme.aut.android.recipy.data.Recipe
import hu.bme.aut.android.recipy.data.RecipeViewModel
import hu.bme.aut.android.recipy.databinding.FragmentRatingBinding


class RatingFragment : DialogFragment() {
    companion object {
        const val TAG = "RatingFragment"
    }


    private lateinit var binding: FragmentRatingBinding
    private val recipeViewModel : RecipeViewModel by activityViewModels()
    private lateinit var currentRecipe: Recipe
    private var recipeRating: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments?.getLong("id")
        currentRecipe = recipeViewModel.getById(id!!)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentRatingBinding.inflate(LayoutInflater.from(context))
        binding.rbRecipeRating.rating = currentRecipe.rating.toFloat()

            binding.rbRecipeRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            recipeRating = rating.toInt()
        }

        return AlertDialog.Builder(requireActivity())
                .setTitle("Rate ${currentRecipe.name} by ${currentRecipe.author}")
                .setView(binding.root)
                .setPositiveButton("ok") { dialogInterface, i ->
                    recipeRating?.let {
                        currentRecipe.rating = it
                        recipeViewModel.update(currentRecipe)
                    }
                }
                .setNegativeButton("cancel", null)
                .create()
    }

}