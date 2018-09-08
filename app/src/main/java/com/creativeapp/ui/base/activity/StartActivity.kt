package com.creativeapp.ui.base.activity

import android.os.Bundle
import com.creativeapp.R

class StartActivity : BaseActivity() {
    override fun getScreenTitle(): String {
        return "name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_main)

//        var button=findViewById<Button>(R.id.test)
//        button.setOnClickListener{
//            startActivity(Intent(this, DrugTypeListFragment::class.java))
//        }
//
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
      //  val fragment = MealEntryFragment()
      //  fragmentTransaction.add(R.id.container, fragment,"")
        fragmentTransaction.commit()
    }
}