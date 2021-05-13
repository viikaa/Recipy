package hu.bme.aut.android.recipy.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.recipy.R
import hu.bme.aut.android.recipy.data.Recipe
import hu.bme.aut.android.recipy.data.RecipeViewModel
import hu.bme.aut.android.recipy.databinding.FragmentDeleteBinding
import hu.bme.aut.android.recipy.databinding.FragmentRatingBinding

class DeleteFragment : DialogFragment() {
    companion object {
        const val TAG = "DeleteFragment"
    }

    private lateinit var binding: FragmentDeleteBinding
    private val recipeViewModel : RecipeViewModel by activityViewModels()
    private lateinit var currentRecipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments?.getLong("id")
        currentRecipe = recipeViewModel.getById(id!!)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDeleteBinding.inflate(LayoutInflater.from(context))

        return AlertDialog.Builder(requireActivity())
                .setTitle("Delete ${currentRecipe.name} by ${currentRecipe.author} ?")
                .setView(binding.root)
                .setPositiveButton("ok") { dialogInterface, i ->
                    recipeViewModel.delete(currentRecipe)
                    if(findNavController().currentDestination?.id != R.id.RecipeListFragment)
                        findNavController().navigate(R.id.RecipeListFragment)
                }
                .setNegativeButton("cancel", null)
                .create()
    }

}