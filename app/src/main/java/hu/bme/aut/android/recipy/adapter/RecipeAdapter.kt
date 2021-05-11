package hu.bme.aut.android.recipy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.recipy.data.Recipe
import hu.bme.aut.android.recipy.data.RecipeViewModel
import hu.bme.aut.android.recipy.databinding.ItemRecipeListBinding

class  RecipeAdapter(private val listener: RecipeClickListener) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(){
    private val items = mutableListOf<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecipeViewHolder(
        ItemRecipeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = items[position]

//        holder.binding.tvCategory.text = recipe.category.name
//        holder.binding.ivIcon.setImageResource(getImageResource(shoppingItem.category))
        holder.binding.tvName.text = recipe.name
        holder.binding.tvAuthor.text = recipe.author
        holder.binding.tvTime.text = "${recipe.reqiredTime} minutes"

        holder.binding.root.setOnClickListener {
            listener.onItemClick(recipe.id)
        }
        holder.binding.ibRemove.setOnClickListener {
            listener.onItemRemoved(recipe, holder.adapterPosition)
        }
        holder.binding.ibEdit.setOnClickListener {
            listener.onItemEdit(recipe.id)
        }

    }

    override fun getItemCount(): Int = items.size

    fun addItem(item: Recipe) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun update(shoppingItems: List<Recipe>) {
        items.clear()
        items.addAll(shoppingItems)
        notifyDataSetChanged()
    }

    fun remove(item: Recipe, position: Int){
        items.remove(item)
        notifyItemRemoved(position)
    }

    interface RecipeClickListener {
        fun onItemClick(id: Long)
        fun onItemChanged(item: Recipe)
        fun onItemRemoved(item: Recipe, position: Int)
        fun onItemEdit(id: Long)
    }

    inner class RecipeViewHolder(val binding: ItemRecipeListBinding) :
        RecyclerView.ViewHolder(binding.root) {}
}