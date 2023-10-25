package com.example.pamuts

import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.pamuts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val navigationHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navigationHost.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        binding.navView?.let {
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_home, R.id.nav_profile
                ),
                binding.drawerLayout
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            it.setupWithNavController(navController)
        }
        binding.appBarMain.contentMain.bottomNavView?.let {
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_home, R.id.nav_profile
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            it.setupWithNavController(navController)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}