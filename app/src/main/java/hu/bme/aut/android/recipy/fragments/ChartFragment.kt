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
import hu.bme.aut.android.recipy.data.RecipeCategory
import hu.bme.aut.android.recipy.data.RecipeViewModel
import hu.bme.aut.android.recipy.databinding.FragmentChartBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChartFragment : Fragment() {

    private lateinit var binding: FragmentChartBinding
    private val recipeViewModel : RecipeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChartBinding.inflate(inflater, container, false)
        loadRecipes()
        return binding.root
    }

    private fun loadRecipes() {
        var entries: ArrayList<PieEntry> = ArrayList()

        entries.add(PieEntry(recipeViewModel.getCountByCategory(RecipeCategory.BREAKFAST).toFloat(), "Breakfast"))
        entries.add(PieEntry(recipeViewModel.getCountByCategory(RecipeCategory.LUNCH).toFloat(), "Lunch"))
        entries.add(PieEntry(recipeViewModel.getCountByCategory(RecipeCategory.DINNER).toFloat(), "Dinner"))
        entries.add(PieEntry(recipeViewModel.getCountByCategory(RecipeCategory.DESSERT).toFloat(), "Dessert"))


        val dataSet = PieDataSet(entries, "Recipes")
        dataSet.setColors(*ColorTemplate.MATERIAL_COLORS)

        val data = PieData(dataSet)
        binding.chartRecipes.data = data
        binding.chartRecipes.invalidate()
    }

}