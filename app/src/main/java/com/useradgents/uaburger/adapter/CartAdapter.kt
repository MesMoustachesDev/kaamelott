package com.useradgents.uaburger.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.useradgents.domain.viewobject.CartItemVO
import com.useradgents.uaburger.R
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter : RecyclerView.Adapter<CartAdapter.GenericViewHolder>() {

    private var items = mutableListOf<CartItemVO>()

    override fun onBindViewHolder(holder: GenericViewHolder?, position: Int) {
        holder?.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GenericViewHolder? {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_cart, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    inner class ExpenseViewHolder(itemView: View) : GenericViewHolder(itemView) {
        override fun <T> bind(t: T) {
            val item = t as CartItemVO
            itemView.cartTitle.text = item.title
            itemView.cartQuantity.text = item.quantity
            itemView.cartPrice.text = item.totalPrice
        }
    }

    fun update(burgers: List<CartItemVO>) {
        val diffResult = DiffUtil.calculateDiff(BurgerDiffCallback(items, burgers))
        items = burgers.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }

    abstract class GenericViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /**
         * Bind the data
         *
         * @param data Data to display
         */
        abstract fun <T> bind(t: T)
    }

    class BurgerDiffCallback(private val oldItems: List<CartItemVO>,
                             private val newItems: List<CartItemVO>) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldItems[oldItemPosition].title == newItems[newItemPosition].title

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}