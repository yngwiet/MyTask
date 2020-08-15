package com.wxh.mytask.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.wxh.mytask.R
import com.wxh.mytask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        drawerLayout = binding.drawerLayout

        // specify top level destinations so drawer icon will display when they are displayed
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.tasksFragment,
                R.id.memoFragment
            ),
            drawerLayout
        )

        val navController = this.findNavController(R.id.myNavHostFragment)

        // hook up navigation controller with drawer
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        // hook up navigation controller with navigation view
        NavigationUI.setupWithNavController(binding.navView, navController)

        // hook up navigation controller with bottom navigation menu
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

}
