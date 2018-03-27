package com.useradgents.uaburger.ui.fragment.cart


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import com.useradgents.domain.viewobject.CartItemVO
import com.useradgents.uaburger.R
import com.useradgents.uaburger.adapter.CartAdapter
import com.useradgents.uaburger.di.Injectable
import kotlinx.android.synthetic.main.fragment_cart.*
import javax.inject.Inject

class CartFragment : Fragment(), CartFragmentContract.View, Injectable {

    @Inject
    lateinit var presenter: CartFragmentContract.Presenter
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartAdapter = CartAdapter()
        cartRecyclerView.adapter = cartAdapter
        val dividerItemDecoration = DividerItemDecoration(cartRecyclerView.context,
                LinearLayout.VERTICAL)
        cartRecyclerView.addItemDecoration(dividerItemDecoration)
        presenter.attach()
    }

    override fun onDestroyView() {
        presenter.detach()
        super.onDestroyView()
    }

    override fun updateCart(cartItems: List<CartItemVO>) {
        if (cartItems.isEmpty()) {
            emptyView.visibility = VISIBLE
        } else {
            emptyView.visibility = GONE
        }
        cartAdapter.update(cartItems)
    }

    override fun updateTotal(formatTotal: String) {
        total?.text = formatTotal
    }

    companion object {
        fun newInstance(): CartFragment = CartFragment()
    }
}
