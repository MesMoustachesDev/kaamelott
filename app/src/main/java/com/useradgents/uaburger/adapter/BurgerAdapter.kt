package com.useradgents.uaburger.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.useradgents.uaburger.R
import com.useradgents.domain.viewobject.BurgerVO
import kotlinx.android.synthetic.main.item_burger.view.*

class BurgerAdapter(val plus: (String) -> Unit, val minus: (String) -> Unit) : RecyclerView.Adapter<BurgerAdapter.GenericViewHolder>() {

    private var items = mutableListOf<BurgerVO>()

    override fun onBindViewHolder(holder: GenericViewHolder?, position: Int) {
        holder?.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GenericViewHolder? {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_burger, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    inner class ExpenseViewHolder(itemView: View) : GenericViewHolder(itemView) {
        override fun <T> bind(t: T) {
            val item = t as BurgerVO
            itemView.title.text = item.name
            itemView.description.text = item.description
            itemView.price.text = item.price.toString()
            itemView.buttonPlus.setOnClickListener { item.id?.let { id -> plus.invoke(id) } }
            itemView.buttonMinus.setOnClickListener { item.id?.let { id -> minus.invoke(id) } }
            itemView.buttonMinus.isEnabled = item.quantityInCart?.let { it > 0 } ?: false
            itemView.cartQuantity.text = item.quantityInCart.toString()

            val options = RequestOptions().placeholder(R.mipmap.ic_launcher)
            Glide.with(itemView.context)
                    .load(item.thumb)
                    .apply(options)
                    .into(itemView.imageView)
        }
    }

    fun update(burgers: MutableList<BurgerVO>) {
        val diffResult = DiffUtil.calculateDiff(BurgerDiffCallback(items, burgers))
        items = burgers
        diffResult.dispatchUpdatesTo(this)
    }

    fun update(id: String, quantity: Int) {
        val index = items.indexOfFirst { it.id == id }
        if (index != -1) {
            items[index].quantityInCart = quantity
            notifyItemChanged(index)
        }
    }

    abstract class GenericViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /**
         * Bind the data
         *
         * @param data Data to display
         */
        abstract fun <T> bind(t: T)
    }

    class BurgerDiffCallback(private val oldBurgers: List<BurgerVO>,
                             private val newBurgers: List<BurgerVO>) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldBurgers[oldItemPosition].id == newBurgers[newItemPosition].id

        override fun getOldListSize(): Int = oldBurgers.size

        override fun getNewListSize(): Int = newBurgers.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldBurgers[oldItemPosition] == newBurgers[newItemPosition]
    }
}