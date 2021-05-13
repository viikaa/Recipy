package hu.bme.aut.android.recipy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import hu.bme.aut.android.recipy.data.RecipeViewModel
import hu.bme.aut.android.recipy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var recipeViewModel : RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)
        recipeViewModel.initialise(this)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_chart -> {
                val navController = findNavController(R.id.nav_host_fragment)
                val isOnChartFragment = navController.currentDestination?.id != R.id.chartFragment
                if(isOnChartFragment){
                    navController.navigate(R.id.chartFragment)
                }
                isOnChartFragment
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}