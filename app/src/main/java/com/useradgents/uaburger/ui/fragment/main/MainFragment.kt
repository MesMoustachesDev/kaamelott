package com.useradgents.uaburger.ui.fragment.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.useradgents.domain.viewobject.BurgerVO
import com.useradgents.uaburger.R
import com.useradgents.uaburger.adapter.BurgerAdapter
import com.useradgents.uaburger.di.Injectable
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


class MainFragment : Fragment(), MainFragmentContract.View, Injectable {

    @Inject
    lateinit var presenter: MainFragmentContract.Presenter
    private lateinit var adapter: BurgerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh.setOnRefreshListener { presenter.refresh() }
        adapter = BurgerAdapter(
                minus = { presenter.changeCartItemQuantity(it, -1) },
                plus = { presenter.changeCartItemQuantity(it, 1) })
        recyclerView.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
                LinearLayout.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
        presenter.attach()
    }

    override fun onDestroyView() {
        presenter.detach()
        super.onDestroyView()
    }

    override fun showProgress(show: Boolean) {
        swipeRefresh?.isRefreshing = show
    }

    override fun updateBurgers(burgers: MutableList<BurgerVO>) {
        if (burgers.isEmpty()) {
            recyclerView?.visibility = View.GONE
            emptyView?.visibility = View.VISIBLE
        } else {
            recyclerView?.visibility = View.VISIBLE
            emptyView?.visibility = View.GONE
        }
        adapter.update(burgers)
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun updateBurgerQuantity(id: String, quantity: Int) {
        adapter.update(id, quantity)
    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }
}
