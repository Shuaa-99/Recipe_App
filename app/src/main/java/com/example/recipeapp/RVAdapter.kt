package com.example.recipeapp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.databinding.ItemRowBinding

class RVAdapter(private var users: Users): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = users[position]
        holder.binding.apply {
            val isExpandeble:Boolean = user.expandeble
            tvTitle.text = user.title
            tvAuthor.text = user.author
            tvIngredients.text = user.ingredients
            tvInstructions.text = user.instructions
            myExpandeble.visibility = if(isExpandeble)View.VISIBLE else View.GONE
            myLayout.setOnClickListener {
             val title = users[position]
             title.expandeble = !title.expandeble
             notifyItemChanged((position))
            }
        }
    }

    override fun getItemCount() = users.size

    fun update(users: Users){
        this.users = users
        notifyDataSetChanged()
    }
}