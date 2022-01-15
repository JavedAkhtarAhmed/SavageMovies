package com.javed.savagemovies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.javed.savagemovies.R
import com.javed.savagemovies.databinding.ActivityHomeBinding
import com.javed.savagemovies.interfaces.HomeFragToActivityInterface
import com.javed.savagemovies.viewmodels.HomeActivityViewModel
import com.javed.savagemovies.viewmodels.HomeActivityViewModelFactory

class HomeActivity : AppCompatActivity(), HomeFragToActivityInterface {

    private lateinit var binding: ActivityHomeBinding
    private val homeFragment: HomeFragment = HomeFragment()
    lateinit var homeActivityViewModel: HomeActivityViewModel
    private val searchFragment: SearchFragment = SearchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this

        val repository = (application as MovieApplication).movieRepository

        homeActivityViewModel = ViewModelProvider(
            this,
            HomeActivityViewModelFactory(repository)
        )[HomeActivityViewModel::class.java]

        replaceFragment(homeFragment)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    replaceFragment(homeFragment)
                    true
                }
                R.id.action_search -> {
                    replaceFragment(searchFragment)
                    true
                }
                else -> false
            }
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.flFragment, fragment)
            ft.commit()
        }
    }

    override fun onCLickSearchBar() {
        replaceFragment(searchFragment)
    }
}