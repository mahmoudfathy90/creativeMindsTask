package com.creativeapp.ui.repoTask

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import com.creativeapp.R
import com.creativeapp.ui.base.activity.BaseActivity

class ReposityTaskActivty : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reposity_task_activty)
        var container=findViewById<ConstraintLayout>(R.id.container)
        replaceFramgment(RepoFragment(),container.id)
    }
}
