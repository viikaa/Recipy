package hu.bme.aut.android.recipy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.recipy.R
import hu.bme.aut.android.recipy.data.Recipe
import hu.bme.aut.android.recipy.data.RecipeCategory
import hu.bme.aut.android.recipy.databinding.ItemRecipeListBinding

class  RecipeAdapter(
        private val listener: RecipeClickListener,
        private val context: Context) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(){
    private val items = mutableListOf<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecipeViewHolder(
        ItemRecipeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = items[position]
        val colors = mapCategoryToColors(recipe.category)

        holder.binding.root.background.setTint(colors.first)
        holder.binding.tvCategory.text = recipe.category.name
        holder.binding.cvCategory.background.setTint(colors.second)
        holder.binding.tvName.text = recipe.name
        holder.binding.tvAuthor.text = recipe.author
        holder.binding.tvTime.text = context.resources.getString(R.string.minutes, recipe.reqiredTime)
        holder.binding.tvRating.text = recipe.rating.toString()

        holder.binding.root.setOnClickListener {
            listener.onItemClick(recipe.id)
        }
        holder.binding.ibRemove.setOnClickListener {
            listener.onItemRemoved(recipe.id)
        }
        holder.binding.ibEdit.setOnClickListener {
            listener.onItemEdit(recipe.id)
        }
        holder.binding.ibRate.setOnClickListener {
            listener.onItemRate(recipe.id)
        }

    }

    private fun mapCategoryToColors(category: RecipeCategory): Pair<Int, Int>{
        return when(category){
            RecipeCategory.BREAKFAST -> Pair(context.resources.getColor(R.color.breakfast), context.resources.getColor(R.color.breakfast_category))
            RecipeCategory.LUNCH -> Pair(context.resources.getColor(R.color.lunch), context.resources.getColor(R.color.lunch_category))
            RecipeCategory.DINNER -> Pair(context.resources.getColor(R.color.dinner), context.resources.getColor(R.color.dinner_category))
            RecipeCategory.DESSERT -> Pair(context.resources.getColor(R.color.dessert), context.resources.getColor(R.color.dessert_category))
        }
    }

    override fun getItemCount(): Int = items.size

    fun update(shoppingItems: List<Recipe>) {
        items.clear()
        items.addAll(shoppingItems)
        notifyDataSetChanged()
    }

    interface RecipeClickListener {
        fun onItemClick(id: Long)
        fun onItemRemoved(id: Long)
        fun onItemEdit(id: Long)
        fun onItemRate(id: Long)
    }

    inner class RecipeViewHolder(val binding: ItemRecipeListBinding) :
        RecyclerView.ViewHolder(binding.root) {}
}