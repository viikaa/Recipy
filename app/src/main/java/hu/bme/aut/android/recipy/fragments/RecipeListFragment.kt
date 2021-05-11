package hu.bme.aut.android.recipy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.recipy.R
import hu.bme.aut.android.recipy.adapter.RecipeAdapter
import hu.bme.aut.android.recipy.data.Recipe
import hu.bme.aut.android.recipy.data.RecipeViewModel
import hu.bme.aut.android.recipy.databinding.FragmentRecipeListBinding
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RecipeListFragment :
    Fragment(),
    RecipeAdapter.RecipeClickListener,
    CoroutineScope by MainScope(){

    private lateinit var binding : FragmentRecipeListBinding
    private lateinit var adapter : RecipeAdapter
    private val recipeViewModel : RecipeViewModel by activityViewModels()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        binding.fabAddRecipe.setOnClickListener {
            findNavController().navigate(R.id.action_RecipeListFragment_to_EditRecipeFragment)
        }


        initRecyclerView()

        recipeViewModel.getLiveData().observe(viewLifecycleOwner, Observer{
            adapter.update(it)
        })

        return binding.root
    }

    private fun initRecyclerView() {
        adapter = RecipeAdapter(this)
        binding.rvRecipe.layoutManager = LinearLayoutManager(this.context)
        binding.rvRecipe.adapter = adapter
    }

    override fun onItemEdit(id: Long) {
        val idBundle = bundleOf("id" to id)
        findNavController().navigate(R.id.action_RecipeListFragment_to_EditRecipeFragment, idBundle)
    }

    override fun onItemChanged(item: Recipe) {
        TODO("Not yet implemented")
    }

    override fun onItemRemoved(recipe: Recipe, position: Int) {
        recipeViewModel.delete(recipe)
        adapter.remove(recipe, position)
    }

    override fun onItemClick(id: Long) {
        val idBundle = bundleOf("id" to id)
        findNavController().navigate(R.id.action_RecipeListFragment_to_viewRecipeFragment, idBundle)
    }
}