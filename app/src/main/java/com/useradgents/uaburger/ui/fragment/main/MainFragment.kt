package com.useradgents.uaburger.ui.fragment.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.useradgents.domain.viewobject.SoundVO
import com.useradgents.uaburger.R
import com.useradgents.uaburger.adapter.SoundAdapter
import com.useradgents.uaburger.di.Injectable
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : Fragment(), MainFragmentContract.View, Injectable {

    @Inject
    lateinit var presenter: MainFragmentContract.Presenter
    private lateinit var adapter: SoundAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SoundAdapter { presenter.play(it) }
        recyclerView.adapter = adapter
        val manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = manager
        presenter.attach()
    }

    override fun onDestroyView() {
        presenter.detach()
        super.onDestroyView()
    }

    override fun showProgress(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun updateSounds(sounds: MutableList<SoundVO>) {
        if (sounds.isEmpty()) {
            recyclerView?.visibility = View.GONE
            emptyView?.visibility = View.VISIBLE
        } else {
            recyclerView?.visibility = View.VISIBLE
            emptyView?.visibility = View.GONE
        }
        adapter.update(sounds)
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun updateBurgerQuantity(id: String, quantity: Int) {
        adapter.update(id, quantity)
    }

    override fun onStart(title: String) {
        adapter.onStart(title)
    }

    override fun onStop(title: String) {
        adapter.onStop(title)
    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }
}
