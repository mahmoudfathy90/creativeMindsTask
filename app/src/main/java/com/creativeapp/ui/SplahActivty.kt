package com.creativeapp.ui

import android.content.Intent
import android.os.Bundle
import com.creativeapp.R
import com.creativeapp.ui.base.activity.BaseActivity
import com.creativeapp.ui.designtask.MyListActivty
import com.creativeapp.ui.repoTask.RepoActivity
import kotlinx.android.synthetic.main.activity_splah_activty.*

class SplahActivty : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splah_activty)
        repoTask.setOnClickListener {
            startActivity(Intent(this,RepoActivity::class.java))
        }
        designTask.setOnClickListener {
            startActivity(Intent(this,MyListActivty::class.java))
        }
    }
}
