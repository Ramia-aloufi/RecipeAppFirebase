package com.example.recipeappfirebase1
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipes.view.*


class MyAdapter( var item:MutableList<Receipes>):RecyclerView.Adapter<MyAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recipes,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var item1 = item[position].title
        var item2 = item[position].author
        var item3 = item[position].ingredients
        var item4 = item[position].instructions
        val items = "$item1\n$item2\n$item3\n$item4"
        holder.itemView.apply {
            tttt.text = items
        }
    }

    override fun getItemCount() = item.size
}