package com.useradgents.uaburger.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.useradgents.uaburger.di.Injectable
import com.useradgents.uaburger.ui.activity.main.MainActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashActivityContract.View, Injectable {

    @Inject
    lateinit var presenter: SplashActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach()
        goToMainActivity()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
