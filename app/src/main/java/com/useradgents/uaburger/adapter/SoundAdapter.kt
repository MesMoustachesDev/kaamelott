package com.useradgents.uaburger.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.useradgents.domain.viewobject.SoundVO
import com.useradgents.uaburger.R
import kotlinx.android.synthetic.main.item_sound.view.*

class SoundAdapter(val play: (String) -> Unit) : RecyclerView.Adapter<SoundAdapter.GenericViewHolder>() {

    private var items = mutableListOf<SoundVO>()

    override fun onBindViewHolder(holder: GenericViewHolder?, position: Int) {
        holder?.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GenericViewHolder? {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_sound, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    inner class ExpenseViewHolder(itemView: View) : GenericViewHolder(itemView) {
        override fun <T> bind(t: T) {
            val item = t as SoundVO
            itemView.title.text = item.title
            itemView.character.text = item.character
            itemView.description.text = item.episode

            itemView.play.setOnClickListener { item.file?.let { play.invoke(it) } }

            val character = item.character?.split("-")?.get(0)?.replace(" ", "")

            itemView.imageView.setImageResource(when (character?.toLowerCase()) {
                "arthur" -> R.drawable.arthur
                "merlin" -> R.drawable.merlin
                "breccan" -> R.drawable.breccan
                "guenièvre" -> R.drawable.guenievre
                "bohort" -> R.drawable.bohort
                "lemaîtred'armes" -> R.drawable.maitre
                "léodagan" -> R.drawable.leodagan
                "attila" -> R.drawable.attila
                "lerépurgateur" -> R.drawable.repurgateur
                "venec" -> R.drawable.venec
                "letavernier" -> R.drawable.tavernier
                "l'interprète" -> R.drawable.interprete
                "perceval" -> R.drawable.perceval
                "dagonet" -> R.drawable.dagonet
                "lancelot" -> R.drawable.lancelot
                "karadoc" -> R.drawable.karadoc
                "angharad" -> R.drawable.angharad
                "demetra" -> R.drawable.demetra
                "l'évêqueboniface" -> R.drawable.boniface
                "ladamedulac" -> R.drawable.lac
                "yvain" -> R.drawable.yvain
                "roparzh" -> R.drawable.roparzh
                "dameseli",
                "dameséli" -> R.drawable.seli
                "kadoc" -> R.drawable.kadoc
                "élias" -> R.drawable.elias
                "caius" -> R.drawable.caius
                "guethenoc" -> R.drawable.guethenoc
                "leroiburgonde" -> R.drawable.burgonde
                else -> R.drawable.kaamelott
            })
        }
    }

    fun update(sounds: MutableList<SoundVO>) {
        val diffResult = DiffUtil.calculateDiff(BurgerDiffCallback(items, sounds))
        items = sounds
        diffResult.dispatchUpdatesTo(this)
    }

    fun update(id: String, quantity: Int) {
        val index = items.indexOfFirst { it.title == id }
        if (index != -1) {
            notifyItemChanged(index)
        }
    }

    fun onStart(title: String) {
    }

    fun onStop(title: String) {
    }

    abstract class GenericViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /**
         * Bind the data
         *
         * @param data Data to display
         */
        abstract fun <T> bind(t: T)
    }

    class BurgerDiffCallback(private val oldSounds: List<SoundVO>,
                             private val newSounds: List<SoundVO>) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldSounds[oldItemPosition].title == newSounds[newItemPosition].title

        override fun getOldListSize(): Int = oldSounds.size

        override fun getNewListSize(): Int = newSounds.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldSounds[oldItemPosition] == newSounds[newItemPosition]
    }
}