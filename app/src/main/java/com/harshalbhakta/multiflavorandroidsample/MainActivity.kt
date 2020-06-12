package com.harshalbhakta.multiflavorandroidsample

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val packageInfo = packageManager.getPackageInfo(packageName,0)
        if (packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_ALLOW_BACKUP != 0) {
            // enabled
            Log.d("MainActivityDebug", "allowBackup Enabled")
        } else {
            // disabled
            Log.d("MainActivityDebug", "allowBackup Disabled")
        }

        try {
            val ai = packageManager.getApplicationInfo(
                packageName,
                PackageManager.GET_META_DATA
            )
            val bundle = ai.metaData
            val myApiKey = bundle.getString("APIKEY")
            Log.d("MainActivityDebug", "APIKEY ${myApiKey}")
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(
                "MainActivityDebug",
                "Failed to load meta-data, NameNotFound: " + e.message
            )
        } catch (e: NullPointerException) {
            Log.e(
                "MainActivityDebug",
                "Failed to load meta-data, NullPointer: " + e.message
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}