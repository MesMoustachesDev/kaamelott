package com.useradgents.uaburger.ui.activity.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.useradgents.uaburger.R
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityContract.View, HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var presenter: MainActivityContract.Presenter

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attach()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.clear_cart_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.clearCart -> presenter.clearCart()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateCartView(updateCart: MainActivityContract.CartCountPrice) {
        cartResume?.visibility = if (updateCart.shouldBeDisplayed) View.VISIBLE else View.GONE
        nbProducts?.text = updateCart.totalQuantity
        totalPrice?.text = updateCart.totalPrice
    }
}
