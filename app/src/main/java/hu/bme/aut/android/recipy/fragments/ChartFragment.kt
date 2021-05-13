package hu.bme.aut.android.recipy.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import hu.bme.aut.android.recipy.R
import hu.bme.aut.android.recipy.data.RecipeCategory
import hu.bme.aut.android.recipy.data.RecipeViewModel
import hu.bme.aut.android.recipy.databinding.FragmentChartBinding

class ChartFragment : Fragment() {

    private lateinit var binding: FragmentChartBinding
    private val recipeViewModel : RecipeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChartBinding.inflate(inflater, container, false)
        binding.chartRecipes.description.isEnabled = false
        binding.chartRecipes.setEntryLabelTextSize(20f)
        binding.chartRecipes.legend.textSize = 15f
        loadRecipes()
        return binding.root
    }

    private fun loadRecipes() {
        val breakfastCount = recipeViewModel.getCountByCategory(RecipeCategory.BREAKFAST).toFloat()
        val lunchCount = recipeViewModel.getCountByCategory(RecipeCategory.LUNCH).toFloat()
        val dinnerCount = recipeViewModel.getCountByCategory(RecipeCategory.DINNER).toFloat()
        val dessertCount = recipeViewModel.getCountByCategory(RecipeCategory.DESSERT).toFloat()


        val breakfastColor = requireContext().resources.getColor(R.color.breakfast)
        val lunchColor = requireContext().resources.getColor(R.color.lunch)
        val dinnerColor = requireContext().resources.getColor(R.color.dinner)
        val dessertColor = requireContext().resources.getColor(R.color.dessert)

        var entries: ArrayList<PieEntry> = ArrayList()
        val recipeColors = mutableListOf<Int>()

        if(breakfastCount != 0f){
            entries.add(PieEntry(breakfastCount, "Breakfast"))
            recipeColors.add(breakfastColor)
        }
        if(lunchCount != 0f){
            entries.add(PieEntry(lunchCount, "Lunch"))
            recipeColors.add(lunchColor)
        }
        if(dinnerCount != 0f){
            entries.add(PieEntry(dinnerCount, "Dinner"))
            recipeColors.add(dinnerColor)
        }
        if(dessertCount != 0f){
            entries.add(PieEntry(dessertCount, "Dessert"))
            recipeColors.add(dessertColor)
        }

        val dataSet = PieDataSet(entries, "Recipes")
        dataSet.colors = recipeColors

        val data = PieData(dataSet)
        binding.chartRecipes.data = data
        binding.chartRecipes.invalidate()
    }

}